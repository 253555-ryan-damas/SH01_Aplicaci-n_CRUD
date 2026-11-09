package com.example.skateshop

import android.app.Application
import com.example.skateshop.di.AppContainer
import com.example.skateshop.di.DefaultAppContainer

class SkateShopApplication : Application() {
    // Esta variable guardará nuestro contenedor para toda la app
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        // Lo instanciamos una sola vez cuando la app arranca
        container = DefaultAppContainer()
    }
}