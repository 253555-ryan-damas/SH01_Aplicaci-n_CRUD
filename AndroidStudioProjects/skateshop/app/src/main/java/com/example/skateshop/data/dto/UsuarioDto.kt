package com.example.skateshop.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UsuarioDto(
    val id: String? = null,
    val email: String,
    val password: String,
    val nombre: String
)