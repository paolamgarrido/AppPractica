package com.example.examenprueba.data.network

import com.example.kotlin.apppracticaexamen.data.network.MovieAPIService
import com.example.kotlin.apppracticaexamen.utilities.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModuleDI {
    private val gsonFactory: GsonConverterFactory = GsonConverterFactory.create()

    // Crear un interceptor para agregar encabezados de autorización
    private val authInterceptor = Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ?")
            .addHeader("accept", "application/json")
            .build()
        chain.proceed(newRequest)
    }

    // Configurar el cliente OkHttp con el interceptor
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    operator fun invoke(): MovieAPIService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonFactory)
            .build()
            .create(MovieAPIService::class.java)
    }
}