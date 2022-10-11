package com.example.catalagofilmes.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.catalagofilmes.model.Filme

@Dao
interface FilmeDao {
    @Insert(onConflict = IGNORE)
    fun salva(filme: Filme)

    @Query("SELECT * FROM Filme")
    fun buscaFilmes(): List<Filme>
}