package com.example.catalagofilmes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.example.catalagofilmes.database.dao.FilmeDao
import com.example.catalagofilmes.model.Filme


@Database(
    version = 1,
    entities = [Filme::class],
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun filmeDao(): FilmeDao

    companion object factory {

        @Volatile
        private var INSTANCE: AppDatabase? = null
        private const val database = "database.db"
        fun getInstance(context: Context): AppDatabase {
            return if (INSTANCE != null) { INSTANCE!! } else {
                synchronized(lock = this) {
                    val instance = databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        database
                    ).allowMainThreadQueries().build()

                    INSTANCE = instance
                    return instance
                }
            }
        }
    }
}

