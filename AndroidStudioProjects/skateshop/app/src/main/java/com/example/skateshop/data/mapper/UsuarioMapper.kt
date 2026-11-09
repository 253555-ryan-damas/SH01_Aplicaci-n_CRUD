package com.example.skateshop.data.mapper

import com.example.skateshop.data.dto.UsuarioDto
import com.example.skateshop.domain.model.Usuario

fun UsuarioDto.toDomain(): Usuario {
    return Usuario(
        id = this.id ?: "",
        email = this.email,
        nombre = this.nombre
    )
}