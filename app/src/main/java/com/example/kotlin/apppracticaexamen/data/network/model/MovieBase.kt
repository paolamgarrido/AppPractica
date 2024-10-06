package com.example.kotlin.apppracticaexamen.data.network.model

data class MovieBase(
    val id: Int,
    val title: String,
    val release_date: String,
    val poster_path: String?,
    val vote_average: Float
)

