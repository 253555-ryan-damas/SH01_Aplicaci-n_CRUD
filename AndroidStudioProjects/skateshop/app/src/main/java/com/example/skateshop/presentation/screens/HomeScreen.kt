package com.example.skateshop.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.skateshop.presentation.viewmodels.SkateUiState
import com.example.skateshop.presentation.viewmodels.SkateViewModel

@Composable
fun HomeScreen(viewModel: SkateViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Agregamos containerColor para asegurarnos de que el fondo no sea negro puro
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val state = uiState) {
                is SkateUiState.Loading -> {
                    // Si se queda aquí, es porque la app está esperando la respuesta de red
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is SkateUiState.Error -> {
                    Text(
                        text = "Error: ${state.message}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is SkateUiState.Success -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(state.productos) { producto ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(text = producto.nombre, style = MaterialTheme.typography.titleMedium)
                                    Text(text = "Categoría: ${producto.categoria}")
                                    Text(text = "Precio: $${producto.precio}")
                                    Text(text = "Stock: ${producto.stock}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}