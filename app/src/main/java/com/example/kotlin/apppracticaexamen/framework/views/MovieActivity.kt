package com.example.kotlin.apppracticaexamen.framework.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.apppracticaexamen.data.network.model.MovieBase
import com.example.kotlin.apppracticaexamen.data.network.model.MovieObject
import com.example.kotlin.apppracticaexamen.databinding.ActivityMoviesBinding
import com.example.kotlin.apppracticaexamen.framework.adapters.MovieAdapter
import com.example.kotlin.apppracticaexamen.framework.viewmodel.MovieViewModel

class MovieActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding

    private val adapter : MovieAdapter = MovieAdapter()

    private val viewModel: MovieViewModel by viewModels()

    private var movieList: ArrayList<MovieBase> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()

        initializeObservers()

        viewModel.getMovieList()

        // Add text change listener for search filter
        binding.etFilter.addTextChangedListener { query ->
            val filteredList = filterMovies(query.toString())
            adapter.updateList(filteredList)
        }
    }

    private fun initializeBinding() {
        binding = ActivityMoviesBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    private fun setUpRecyclerView(dataForList: ArrayList<MovieBase>) {
        // Cambio
        movieList = dataForList

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

    // Function to filter movies based on the search query
    private fun filterMovies(query: String): ArrayList<MovieBase> {
        return if (query.isEmpty()) {
            movieList
        } else {
            val filteredList = ArrayList<MovieBase>()
            for (movie in movieList) {
                if (movie.title.contains(query, ignoreCase = true)) {
                    filteredList.add(movie)
                }
            }
            filteredList
        }
    }
}
