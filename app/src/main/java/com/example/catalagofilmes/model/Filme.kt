package com.example.catalagofilmes.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Filme(
    val backdrop_path: String?,
    val adult: Boolean?,
    @PrimaryKey
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Float?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Float?,
    val vote_count: Int?,
    val selected : Boolean
) : Serializable {
    val filme: Filme
        get() = Filme(
            backdrop_path ?: "",
            adult ?: false,
            id ?: 0,
            original_language ?: "",
            original_title ?: "",
            overview ?: "",
            popularity ?: 0f,
            poster_path ?: "",
            release_date?: "",
            title ?: "",
            video ?: false,
            vote_average ?: 0f,
            vote_count ?: 0,
            selected ?: false,
        )
}