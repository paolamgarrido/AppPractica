package com.example.kotlin.apppracticaexamen.data.network.model

data class MovieObject(
    val page: Int,
    val results: List<MovieBase>,
)
