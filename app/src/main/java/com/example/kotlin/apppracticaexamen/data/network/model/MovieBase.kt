package com.example.kotlin.apppracticaexamen.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieBase(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val url: String,  // Using camelCase for Kotlin property
    @SerializedName("vote_average") val voteAverage: Float  // Using camelCase for Kotlin property
)
