package com.example.kotlin.apppracticaexamen.domain

import com.example.kotlin.apppracticaexamen.data.MovieRepository
import com.example.kotlin.apppracticaexamen.data.network.model.MovieBase

class MovieInfoRequirement {
    private val repository = MovieRepository()

    suspend operator fun invoke(
        movieID: Int
    ): MovieBase? =
        repository.getMovieInfo(movieID)
}