package com.example.catalagofilmes.repository

import com.example.catalagofilmes.database.dao.FilmeDao
import com.example.catalagofilmes.model.Filme

class Repository(private val filmeDao: FilmeDao) {
    suspend fun salva(filme: Filme){
        return filmeDao.salva(filme)
    }

    fun buscaFilmes(): List<Filme>? {
        return filmeDao.buscaFilmes()
    }
}