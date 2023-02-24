package com.rosewhat.crypto.ui

import android.app.Application
import com.rosewhat.crypto.di.DaggerApplicationComponent

class CoinApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)

    }

}