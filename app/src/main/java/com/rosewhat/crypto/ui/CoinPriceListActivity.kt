package com.rosewhat.crypto.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rosewhat.crypto.R
import com.rosewhat.crypto.databinding.ActivityCoinPriceListBinding
import com.rosewhat.crypto.ui.adapters.CoinInfoAdapter
import com.rosewhat.crypto.ui.viewModel.CoinViewModel
import com.rosewhat.crypto.ui.viewModel.ViewModelFactory
import javax.inject.Inject

class CoinPriceListActivity : AppCompatActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
    }

    private val component by lazy {
        (application as CoinApp).component
    }



    private lateinit var coinAdapter: CoinInfoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        coinAdapter = CoinInfoAdapter(this)
        with(coinAdapter) {
            binding.rvCoinPriceList.adapter = this
            binding.rvCoinPriceList.itemAnimator = null

            onInfoItemCoinClickListener = {
                if (isOnePaneMode()) {
                    launchDetailActivity(it.fromSymbol)
                } else {
                    launchDetailFragment(it.fromSymbol)
                }
            }
        }
        observeViewModel()

    }

    private fun isOnePaneMode() = binding.fragmentContainerLandscape == null

    private fun observeViewModel() {
        viewModel.coinInfoList.observe(this) {
            coinAdapter.submitList(it)
        }
    }

    private fun launchDetailActivity(fromSymbol: String) {
        startActivity(
            CoinDetailActivity.newIntent(
                context = this@CoinPriceListActivity,
                fromSymbol = fromSymbol
            )
        )
    }

    private fun launchDetailFragment(fronSymbol: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CoinDetailFragment.newInstance(fronSymbol))
            .addToBackStack(null)
            .commit()
    }

}