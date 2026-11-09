package com.example.skateshop.data.repository

import com.example.skateshop.domain.model.Usuario

interface AuthRepository {
    suspend fun login(email: String, password: String): Usuario?
    suspend fun register(nombre: String, email: String, password: String): Usuario
}