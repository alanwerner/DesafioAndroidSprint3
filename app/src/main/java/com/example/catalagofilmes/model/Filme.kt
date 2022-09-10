package com.example.catalagofilmes.model

import java.io.Serializable

data class Filme(
    val backdrop_path: String?,
    val adult: Boolean?,
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Number?,
    val vote_count: Int?
) : Serializable {
    val filme: Filme
        get() = Filme(
            backdrop_path ?: "",
            adult ?: false,
            id ?: 0,
            original_language ?: "",
            original_title ?: "",
            overview ?: "",
            popularity ?: "",
            release_date?: "",
            title ?: "",
            video ?: false,
            vote_average ?: 0,
            vote_count ?: 0
        )
}