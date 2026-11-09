package com.example.skateshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.skateshop.presentation.screens.AuthScreen
import com.example.skateshop.presentation.screens.ProductListScreen // Asegúrate de que este sea el nombre de tu pantalla de productos
import com.example.skateshop.presentation.viewmodels.AuthViewModel
import com.example.skateshop.presentation.viewmodels.SkateViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtenemos la app y el contenedor de dependencias
        val app = application as SkateShopApplication
        val container = app.container

        // Creamos los ViewModels usando los repositorios del contenedor
        val authViewModel = AuthViewModel(container.authRepository)
        val skateViewModel = SkateViewModel(container.skateRepository)

        setContent {
            // Estado para controlar si ya inició sesión o sigue en el Login
            var isLoggedIn by remember { mutableStateOf(false) }

            if (!isLoggedIn) {
                // Muestra la pantalla de Login / Registro
                AuthScreen(
                    viewModel = authViewModel,
                    onLoginSuccess = {
                        isLoggedIn = true // Al tener éxito, cambia al catálogo
                    }
                )
            } else {
                // Muestra tu catálogo de patinetas que ya funcionaba
                ProductListScreen(viewModel = skateViewModel)
            }
        }
    }
}
