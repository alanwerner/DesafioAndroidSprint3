package com.example.catalagofilmes.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.example.catalagofilmes.model.Filme

@Dao
interface FilmeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salva(filme: Filme)

    @Query("SELECT * FROM Filme")
    fun buscaFilmes(): List<Filme>?

    @Query("DELETE FROM Filme WHERE id = :id")
    fun deletaFilme(id : Int)

//    @Delete
//    fun deleta(vararg filme: Filme)
}