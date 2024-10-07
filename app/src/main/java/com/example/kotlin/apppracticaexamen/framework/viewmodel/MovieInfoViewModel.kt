package com.example.kotlin.apppracticaexamen.framework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.apppracticaexamen.data.network.model.MovieBase
import com.example.kotlin.apppracticaexamen.domain.MovieInfoRequirement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieInfoViewModel : ViewModel() {

    val movieInfoLiveData = MutableLiveData<MovieBase?>()

    // Usamos la clase que maneja la lógica para obtener la info de una película individual
    private val movieInfoRequirement = MovieInfoRequirement()

    // Función para obtener la información de la película individual
    fun getMovieInfo(movieID: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result: MovieBase? = movieInfoRequirement(movieID)
            CoroutineScope(Dispatchers.Main).launch {
                movieInfoLiveData.postValue(result)
            }
        }
    }
}