package com.example.skateshop.di

import com.example.skateshop.data.datasource.SkateApiService
import com.example.skateshop.data.repository.AuthRepository // <-- 1. Importa esto arriba
import com.example.skateshop.data.repository.AuthRepositoryImpl
import com.example.skateshop.data.repository.SkateRepository
import com.example.skateshop.data.repository.SkateRepositoryImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

interface AppContainer {
    val skateRepository: SkateRepository
    val authRepository: AuthRepository // Declaramos la interfaz aquí
}

class DefaultAppContainer : AppContainer {

    private val BASE_URL = "https://api-moviles-cs5j.onrender.com/"

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            json.asConverterFactory("application/json; charset=UTF8".toMediaType())
        )
        .build()

    private val retrofitService: SkateApiService by lazy {
        retrofit.create(SkateApiService::class.java)
    }

    override val skateRepository: SkateRepository by lazy {
        SkateRepositoryImpl(retrofitService)
    }

    // 2. ¡El authRepository va AQUÍ adentro, implementando la interfaz!
    override val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(retrofitService)
    }
}