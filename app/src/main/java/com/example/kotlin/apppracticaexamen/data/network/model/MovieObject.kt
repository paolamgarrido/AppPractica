package com.example.kotlin.apppracticaexamen.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieObject(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: ArrayList<MovieBase>,
)
