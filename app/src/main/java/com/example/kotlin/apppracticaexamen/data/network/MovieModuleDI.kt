package com.example.kotlin.apppracticaexamen.data.network

import com.example.kotlin.apppracticaexamen.utilities.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieModuleDI {
    // GsonConverterFactory is used to convert JSON responses from the API into Kotlin objects.
    private val gsonFactory: GsonConverterFactory = GsonConverterFactory.create()

    // OkHttpClient is a basic HTTP client that will be used by Retrofit to manage network requests.
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", Constants.AUTHORIZATION_TOKEN) // Authorization header
                .build()
            chain.proceed(request)
        }
        .build()

    // This is where the Retrofit object is created and configured to make network calls.
    operator fun invoke(): MovieAPIService {
        return Retrofit.Builder()
            // The base URL for the API is retrieved from the Constants class.
            .baseUrl(Constants.BASE_URL)
            // The OkHttpClient is provided for network communication.
            .client(okHttpClient)
            // The GsonConverterFactory is used to convert the API's JSON responses into Kotlin objects.
            .addConverterFactory(gsonFactory)
            // Build the Retrofit instance and create the API service interface (PokemonAPIService).
            .build()
            .create(MovieAPIService::class.java)
    }
}