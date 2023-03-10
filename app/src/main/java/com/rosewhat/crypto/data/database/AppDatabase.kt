package com.rosewhat.crypto.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CoinInfoDbModel::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "crypto_item.db"

        fun getInstance(context: Context): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
            }
            val db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DB_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE = db
            return db
        }
    }

    abstract fun coinPriceInfoDao(): CoinInfoDao
}
