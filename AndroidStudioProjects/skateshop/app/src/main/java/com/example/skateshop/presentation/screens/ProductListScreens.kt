package com.example.skateshop.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.skateshop.presentation.viewmodels.SkateUiState
import com.example.skateshop.presentation.viewmodels.SkateViewModel

@Composable
fun ProductListScreen(viewModel: SkateViewModel) {
    // Usamos collectAsState nativo de Compose para evitar el error de Lifecycle
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (val state = uiState) {
            // Loading es un object, por lo tanto NO lleva la palabra "is"
            SkateUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            // Success es un data class, SÍ lleva la palabra "is"
            is SkateUiState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.productos) { producto ->
                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = producto.nombre,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(text = "Categoría: ${producto.categoria}")
                                Text(text = "Precio: \$${producto.precio}")
                                Text(text = "Stock: ${producto.stock}")
                            }
                        }
                    }
                }
            }

            // Error es un data class, SÍ lleva la palabra "is"
            is SkateUiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = state.message, color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}