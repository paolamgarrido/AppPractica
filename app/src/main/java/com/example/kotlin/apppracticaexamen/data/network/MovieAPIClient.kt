package com.example.kotlin.apppracticaexamen.data.network

import com.example.examenprueba.data.network.NetworkModuleDI
import com.example.kotlin.apppracticaexamen.data.network.model.MovieBase
import com.example.kotlin.apppracticaexamen.data.network.model.MovieObject

class MovieAPIClient {
    private lateinit var api: MovieAPIService

    suspend fun getMovieList(): MovieObject? {
        api = NetworkModuleDI()

        return try {
            api.getMovieList()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getMovieInfo(movieID:Int): MovieBase? {
        api = NetworkModuleDI()

        return try {
            api.getMovieInfo(movieID)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            null
        }
    }
}