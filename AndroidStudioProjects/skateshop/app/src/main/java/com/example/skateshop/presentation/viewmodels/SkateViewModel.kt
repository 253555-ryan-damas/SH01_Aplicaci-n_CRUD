package com.example.skateshop.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skateshop.data.repository.SkateRepository
import com.example.skateshop.presentation.screens.SkateUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SkateViewModel(private val repository: SkateRepository) : ViewModel() {

    // Estado privado y mutable (solo el ViewModel lo puede modificar)
    private val _uiState = MutableStateFlow<SkateUiState>(SkateUiState.Loading)
    // Estado público e inmutable (la UI solo lo puede observar)
    val uiState: StateFlow<SkateUiState> = _uiState.asStateFlow()

    init {
        getProductos()
    }

    fun getProductos() {
        viewModelScope.launch {
            _uiState.value = SkateUiState.Loading
            try {
                // Consumimos el repositorio que configuramos en la capa de datos
                val productos = repository.getProductos()
                _uiState.value = SkateUiState.Success(productos)
            } catch (e: Exception) {
                _uiState.value = SkateUiState.Error("Error de red: ${e.message}")
            }
        }
    }
}