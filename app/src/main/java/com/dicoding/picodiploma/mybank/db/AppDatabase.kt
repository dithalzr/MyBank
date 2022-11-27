package com.dicoding.picodiploma.mybank.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.picodiploma.mybank.model.ModelDatabase

@Database(entities = [ModelDatabase::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun daoDatabase(): DaoDatabase?
}
