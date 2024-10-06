package com.example.kotlin.apppracticaexamen.framework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.apppracticaexamen.data.network.model.MovieObject
import com.example.kotlin.apppracticaexamen.domain.MovieListRequirement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {

    val movieObjectLiveData = MutableLiveData<MovieObject?>()

    private val movieListRequirement = MovieListRequirement()

    fun getMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result: MovieObject? = movieListRequirement()
            CoroutineScope(Dispatchers.Main).launch {
                movieObjectLiveData.postValue(result)
            }
        }
    }
}