package com.example.kotlin.apppracticaexamen.framework.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.apppracticaexamen.data.network.model.MovieBase
import com.example.kotlin.apppracticaexamen.databinding.ActivityMoviesBinding
import com.example.kotlin.apppracticaexamen.framework.adapters.MovieAdapter
import com.example.kotlin.apppracticaexamen.framework.viewmodel.MovieViewModel

class MovieActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding

    private val adapter : MovieAdapter = MovieAdapter()

    private lateinit var data: ArrayList<MovieBase>

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()

        initializeObservers()

        viewModel.getMovieList()
    }

    private fun initializeBinding() {
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setUpRecyclerView(dataForList: ArrayList<MovieBase>) {
        binding.RVMovies.setHasFixedSize(true)

        val linearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.RVMovies.layoutManager = linearLayoutManager

        adapter.MovieAdapter(dataForList, this)

        binding.RVMovies.adapter = adapter
    }

    private fun initializeObservers() {
        viewModel.movieObjectLiveData.observe(this) { movieObject ->
            if (movieObject != null) {
                setUpRecyclerView(movieObject.results)
            }
        }
    }
}
