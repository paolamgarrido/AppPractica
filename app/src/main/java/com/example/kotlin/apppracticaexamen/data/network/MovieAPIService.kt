package com.example.kotlin.apppracticaexamen.data.network

import com.example.kotlin.apppracticaexamen.data.network.model.MovieObject
import retrofit2.http.GET

interface MovieAPIService {

    // Endpoint: https://api.themoviedb.org/3/movie/popular
    @GET("movie/popular")
    suspend fun getMovieList(): MovieObject

}