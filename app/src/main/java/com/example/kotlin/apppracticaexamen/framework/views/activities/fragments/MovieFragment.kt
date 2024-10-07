package com.example.kotlin.apppracticaexamen.framework.views.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.apppracticaexamen.R
import com.example.kotlin.apppracticaexamen.data.network.model.MovieBase
import com.example.kotlin.apppracticaexamen.databinding.FragmentMoviesBinding
import com.example.kotlin.apppracticaexamen.framework.adapters.MovieAdapter
import com.example.kotlin.apppracticaexamen.framework.viewmodel.MovieViewModel

class MovieFragment: Fragment() {

    private var _binding: FragmentMoviesBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: MovieViewModel

    private lateinit var recyclerView: RecyclerView

    private val adapter : MovieAdapter = MovieAdapter()

    private lateinit var data: ArrayList<MovieBase>

    private var movieList: ArrayList<MovieBase> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View {
        // Inicializa el ViewModel para gestionar los datos de la UI
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        // Infla el layout del fragmento y lo asigna al binding
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)

        // Obtiene la raíz del layout inflado
        val root: View = binding.root

        // Inicializa la lista de Pokémon vacía
        data = ArrayList()

        // Llama a los métodos para inicializar los componentes y observadores
        initializeComponents(root)
        initializeObservers()

        // Llama al método del ViewModel para obtener la lista de Pokémon
        viewModel.getMovieList()

        // Add text change listener for search filter
        binding.etFilter.addTextChangedListener { query ->
            val filteredList = filterMovies(query.toString())
            adapter.updateList(filteredList)
        }

        // Devuelve la vista raíz del fragmento
        return root
    }

    // Se llama cuando la vista se destruye. Aquí se elimina el binding para evitar fugas de memoria.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Libera el binding cuando la vista es destruida
    }

    // Método para inicializar los componentes del fragmento, en este caso el RecyclerView
    private fun initializeComponents(root: View) {
        recyclerView = root.findViewById(R.id.RVMovies)
    }

    private fun initializeObservers() {
        viewModel.movieObjectLiveData.observe(viewLifecycleOwner) { movieObject ->
            if (movieObject != null) {
                setUpRecyclerView(movieObject.results)
            }
        }
    }

    private fun setUpRecyclerView(dataForList: ArrayList<MovieBase>) {
        // Cambio
        movieList = dataForList

        recyclerView.setHasFixedSize(true)

        val linearLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.RVMovies.layoutManager = linearLayoutManager

        // Configura el adaptador con la lista de Pokémon y el contexto actual
        adapter.MovieAdapter(dataForList, requireContext())

        // Asigna el adaptador al RecyclerView
        recyclerView.adapter = adapter
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
