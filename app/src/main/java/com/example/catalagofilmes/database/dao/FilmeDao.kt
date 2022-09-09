package com.example.catalagofilmes.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.catalagofilmes.model.Filme
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmeDao {

    @Query("SELECT * FROM Filme")
    fun buscaTodos() : Flow<List<Filme>>

}