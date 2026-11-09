package com.example.skateshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skateshop.presentation.screens.HomeScreen
import com.example.skateshop.presentation.viewmodels.SkateViewModel
import com.example.skateshop.presentation.viewmodels.SkateViewModelFactory
import com.example.skateshop.ui.theme.SkateshopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkateshopTheme {
                // Instanciamos el ViewModel usando nuestra Factory inyectora
                val viewModel: SkateViewModel = viewModel(factory = SkateViewModelFactory)

                HomeScreen(viewModel = viewModel)
            }
        }
    }
}