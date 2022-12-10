package com.dicoding.picodiploma.mybank.db

import android.content.Context
import androidx.room.Room

class ClientDatabase private constructor(context: Context) {

    var appDatabase: AppDatabase

    companion object {
        private var mInstance: ClientDatabase? = null
        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): ClientDatabase? {
            if (mInstance == null) {
                mInstance = ClientDatabase(context)
            }
            return mInstance
        }
    }

    init {
        appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "banksampah_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}