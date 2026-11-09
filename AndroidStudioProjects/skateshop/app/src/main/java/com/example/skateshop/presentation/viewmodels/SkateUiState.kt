package com.example.skateshop.presentation.viewmodels

import com.example.skateshop.data.model.ProductoSkate

sealed interface SkateUiState {
    object Loading : SkateUiState
    data class Success(val productos: List<ProductoSkate>) : SkateUiState
    data class Error(val message: String) : SkateUiState
}