package com.example.kotlin.apppracticaexamen.data.network

import com.example.kotlin.apppracticaexamen.data.network.model.MovieBase
import com.example.kotlin.apppracticaexamen.data.network.model.MovieObject
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieAPIService {

    // Endpoint: https://api.themoviedb.org/3/movie/popular
    @GET("movie/popular")
    suspend fun getMovieList(): MovieObject

    // Endpoint: https://api.themoviedb.org/3/movie/{movie_id}
    @GET("movie/{movie_id}")
    suspend fun getMovieInfo(
        @Path("movie_id") movieID: Int
    ) : MovieBase

}