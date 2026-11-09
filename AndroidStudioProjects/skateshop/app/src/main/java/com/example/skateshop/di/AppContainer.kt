package com.example.skateshop.di

import com.example.skateshop.data.datasource.SkateApiService
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
}

class DefaultAppContainer : AppContainer {
    // URL para conectarse al json-server desde el emulador de Android
    private val BASE_URL = "http://192.168.0.200:3000/"

    // Para ver en el Logcat (consola) lo que mandamos y recibimos
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        // OJO: Aquí meteremos el Interceptor del Token cuando hagamos el Login
        .build()

    // Configuramos Kotlinx Serialization (el que pidió el profe)
// Configuramos Kotlinx Serialization con lenient para evitar errores de tipo
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()
    private val retrofitService: SkateApiService by lazy {
        retrofit.create(SkateApiService::class.java)
    }

    // ¡Aquí está la instancia única de tu repositorio!
    override val skateRepository: SkateRepository by lazy {
        SkateRepositoryImpl(retrofitService)
    }
}