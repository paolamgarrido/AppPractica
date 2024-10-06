package com.example.kotlin.apppracticaexamen.framework.adapters.viewholders

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin.apppracticaexamen.data.network.model.MovieBase
import com.example.kotlin.apppracticaexamen.databinding.ItemMovieBinding

class MovieViewHolder(
    private val binding: ItemMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    // Method to bind the data model to the UI
    fun bind(item: MovieBase, context: Context) {
        binding.TVMovieTitle.text = item.title
        binding.TVVoteAverage.text = item.voteAverage.toString()
        binding.TVOverview.text = item.overview

        // Check if the poster path exists and is not null before loading the image
        if (item.url != null) {
            binding.IVMovie.visibility = View.VISIBLE
            val imageUrl = "https://image.tmdb.org/t/p/w500${item.url}"  // Use a base URL and append the poster path
            getMovieImage(imageUrl, binding.IVMovie, context)
        } else {
            // Hide the ImageView if there is no image available
            binding.IVMovie.visibility = View.GONE
        }
    }

    // Function to load the movie image using Glide
    private fun getMovieImage(url: String, imageView: ImageView, context: Context) {
        val requestOptions = RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .priority(Priority.HIGH)

        // Glide automatically handles threading, so coroutines are not necessary here
        Glide.with(context)
            .load(url)
            .apply(requestOptions)
            .into(imageView)
    }
}
