package com.example.catalagofilmes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.catalagofilmes.database.dao.FilmeDao
import com.example.catalagofilmes.model.Filme

@Database(
    version = 1,
    entities = [Filme::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun filmeDao(): FilmeDao

    companion object {
        @Volatile
        private var db: AppDatabase? = null

        fun instancia(context: Context): AppDatabase {
            return db ?: Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "ceep.db"
            ).build()
        }
    }

}