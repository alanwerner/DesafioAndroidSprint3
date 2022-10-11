package com.example.catalagofilmes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.example.catalagofilmes.database.dao.FilmeDao
import com.example.catalagofilmes.model.Filme


class AppDaatabase {

    @Database(
        version = 1,
        entities = [Filme::class],
        exportSchema = false
    )

    abstract class AppDatabase : RoomDatabase() {

        abstract fun filmeDao(): FilmeDao

        companion object factory {

            private lateinit var db: AppDatabase

            fun instancia(context: Context): AppDatabase {
                if (::db.isInitialized) return db
                db = databaseBuilder(context, AppDatabase::class.java, "AppDatabase.db")
                    .addMigrations(Migration(1, 2) {
                        it.execSQL("DROP TABLE IF EXISTS 'Filme'")
                        it.execSQL(
                            "CREATE TABLE IF NOT EXISTS Filme (" +
                                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                    "title TEXT NOT NULL, " +
                                    "original_language TEXT NOT NULL, " +
                                    "poster_path TEXT NOT NULL, " +
                                    "overview TEXT NOT NULL, " +
                                    "release_date TEXT NOT NULL, " +
                                    "backdrop_path TEXT NOT NULL)"
                        );
                    })
                    .allowMainThreadQueries()
                    .build()
                return db
            }
        }
    }
}
