package com.example.catalagofilmes.webclient.model

import com.example.catalagofilmes.model.Filme

class FilmeResposta(
    val page: Int?,
    val results: List<Filme>?,
    val total_pages: Int?,
    val total_results: Int?
) {

    val filme: FilmeResposta get() = FilmeResposta(
        page?:0,
        results?: listOf(),
        total_pages?:0,
        total_results?:0
    )

}