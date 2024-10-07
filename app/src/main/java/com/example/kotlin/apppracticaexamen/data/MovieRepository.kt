package com.example.kotlin.apppracticaexamen.data

import android.util.Log
import com.example.kotlin.apppracticaexamen.data.network.MovieAPIClient
import com.example.kotlin.apppracticaexamen.data.network.model.MovieBase
import com.example.kotlin.apppracticaexamen.data.network.model.MovieObject

class MovieRepository{
    private val apiMovie = MovieAPIClient()

    suspend fun getMovieList(): MovieObject? =
        try {
            // Realiza la consulta
            val response = apiMovie.getMovieList()
            Log.d("MovieRepository", "Consulta exitosa: $response")
            response
        } catch (e: Exception) {
            Log.e("Falla en getFAQList", "Error en la consulta de getFAQList: ${e.message}")
            null
        }

    suspend fun getMovieInfo(movieID: Int): MovieBase? =
        apiMovie.getMovieInfo(movieID)
}