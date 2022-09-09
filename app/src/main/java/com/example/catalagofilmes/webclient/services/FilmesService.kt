package com.example.catalagofilmes.webclient.services

import com.example.catalagofilmes.webclient.model.FilmeResposta
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmesService {

    @GET("movie/popular")
    suspend fun buscaTodos(@Query("api_key")key:String? = "9106a44c761c36bbb02f24c16958a56a"): Response<FilmeResposta>

//    @GET("movie/popular")
//    suspend fun buscaTodosCoroutines(): Response<List<FilmeResposta>>

}