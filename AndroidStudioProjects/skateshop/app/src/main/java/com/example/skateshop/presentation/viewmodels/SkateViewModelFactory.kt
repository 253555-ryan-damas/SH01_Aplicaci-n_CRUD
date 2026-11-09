package com.example.skateshop.presentation.viewmodels

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.skateshop.SkateShopApplication // Asegúrate de importar tu clase Application

val SkateViewModelFactory = viewModelFactory {
    initializer {
        // Obtenemos la clase Application
        val application = (this[APPLICATION_KEY] as SkateShopApplication)
        // Sacamos el repositorio de nuestro contenedor manual
        val repository = application.container.skateRepository
        // Inyectamos el repositorio al ViewModel
        SkateViewModel(repository = repository)
    }
}