package com.example.catalagofilmes.webclient

import com.example.catalagofilmes.webclient.model.FilmeResposta
import com.example.catalagofilmes.webclient.services.FilmesService
import java.lang.Exception

class filmeWebClient {
    private val filmesService: FilmesService = RetrofitInicializador().filmesService

    suspend fun buscaTodos(page : Int): FilmeResposta? {
        return try {
            val response = filmesService.buscaTodos(page = page)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

}