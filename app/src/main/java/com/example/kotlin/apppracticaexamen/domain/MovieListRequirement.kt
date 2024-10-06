package com.example.kotlin.apppracticaexamen.domain

import com.example.kotlin.apppracticaexamen.data.MovieRepository
import com.example.kotlin.apppracticaexamen.data.network.model.MovieObject

class MovieListRequirement {
    private val repository = MovieRepository()

    suspend operator fun invoke(): MovieObject? = repository.getMovieList()
}