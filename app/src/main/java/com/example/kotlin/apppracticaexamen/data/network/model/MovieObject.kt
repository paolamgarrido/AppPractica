package com.example.kotlin.apppracticaexamen.data.network.model

data class MovieObject(
    val page: Int,
    val results: List<MovieBase>,
    val total_results: Int,
    val total_pages: Int
)
