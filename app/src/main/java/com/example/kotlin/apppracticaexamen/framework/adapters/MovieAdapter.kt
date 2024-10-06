package com.example.kotlin.apppracticaexamen.framework.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.apppracticaexamen.data.network.model.MovieBase
import com.example.kotlin.apppracticaexamen.databinding.ItemMovieBinding
import com.example.kotlin.apppracticaexamen.framework.adapters.viewholders.MovieViewHolder

class MovieAdapter: RecyclerView.Adapter<MovieViewHolder>() {

    var data: ArrayList<MovieBase> = ArrayList()

    lateinit var context: Context

    fun MovieAdapter(basicData: ArrayList<MovieBase>, context: Context) {
        this.data = basicData
        this.context = context
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
