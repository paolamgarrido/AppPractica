package com.example.kotlin.apppracticaexamen.framework.views.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kotlin.apppracticaexamen.R
import com.example.kotlin.apppracticaexamen.databinding.ActivityMainBinding
import com.example.kotlin.apppracticaexamen.framework.viewmodel.MainViewModel
import com.example.kotlin.apppracticaexamen.framework.views.activities.fragments.HomeFragment
import com.example.kotlin.apppracticaexamen.framework.views.activities.fragments.MovieFragment
import com.example.kotlin.apppracticaexamen.utilities.Constants

// Clase principal que representa la actividad MainActivity de la aplicación
class MainActivity: AppCompatActivity() {

    // Variable para el enlace con la vista (View Binding)
    private lateinit var binding: ActivityMainBinding

    // Instancia del ViewModel usando el patrón delegado para gestionar la lógica de UI
    private val viewModel: MainViewModel by viewModels()

    // Fragmento actual que está activo en la actividad
    private lateinit var currentFragment: Fragment

    // Opción de menú actual seleccionada
    private var currentMenuOption: String? = null

    // Método que se ejecuta cuando la actividad es creada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa el binding, observadores y listeners
        initializeBinding()
        initializeListeners()

        // Carga el fragmento inicial (PokedexFragment) y la opción de menú correspondiente
        exchangeCurrentFragment(HomeFragment(), Constants.MENU_HOME)
    }

    // Método para inicializar el View Binding
    private fun initializeBinding() {
        // Infla el layout y lo asigna al binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)  // Establece el layout principal de la actividad
    }

    // Método para cambiar el fragmento actual por uno nuevo
    private fun exchangeCurrentFragment(newFragment: Fragment, newMenuOption: String) {
        // Asigna el nuevo fragmento a la variable currentFragment
        currentFragment = newFragment

        // Realiza la transacción para reemplazar el fragmento en el contenedor
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, currentFragment)
            .commit()  // Confirma la transacción

        // Actualiza la opción de menú actual
        currentMenuOption = newMenuOption
    }

    // Método para inicializar los listeners de los botones de menú
    private fun initializeListeners() {
        // Listener para la opción del menú Pokedex
        binding.appBarMain.Home.setOnClickListener {
            selectMenuOption(Constants.MENU_HOME)  // Selecciona la opción del menú Pokedex
        }

        // Listener para la opción del menú Search (Búsqueda)
        binding.appBarMain.Search.setOnClickListener {
            selectMenuOption(Constants.MENU_SEARCH)  // Selecciona la opción del menú Búsqueda
        }
    }

    // Método para seleccionar una opción del menú y cambiar de fragmento si es necesario
    private fun selectMenuOption(menuOption: String) {
        // Si la opción de menú seleccionada es la misma que la actual, no hace nada
        if (menuOption == currentMenuOption) {
            return
        }

        // Cambia de fragmento según la opción de menú seleccionada
        when (menuOption) {
            Constants.MENU_HOME -> exchangeCurrentFragment(HomeFragment(), Constants.MENU_HOME)
            Constants.MENU_SEARCH -> exchangeCurrentFragment(MovieFragment(), Constants.MENU_SEARCH)
        }
    }

}



