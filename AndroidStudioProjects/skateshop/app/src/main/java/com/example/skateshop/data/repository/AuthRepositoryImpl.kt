package com.example.skateshop.data.repository

import com.example.skateshop.data.datasource.SkateApiService
import com.example.skateshop.data.dto.UsuarioDto
import com.example.skateshop.data.mapper.toDomain
import com.example.skateshop.domain.model.Usuario

class AuthRepositoryImpl(
    private val apiService: SkateApiService
) : AuthRepository {

    override suspend fun login(email: String, password: String): Usuario? {
        val usuarios = apiService.getUsuarios()
        // Busca si coincide el correo y la contraseña en el json-server
        val match = usuarios.find { it.email == email && it.password == password }
        return match?.toDomain()
    }

    override suspend fun register(nombre: String, email: String, password: String): Usuario {
        val nuevoDto = UsuarioDto(
            id = java.util.UUID.randomUUID().toString(), // <-- Le asignamos un ID único aquí
            nombre = nombre,
            email = email,
            password = password
        )
        val response = apiService.registerUsuario(nuevoDto)
        return response.toDomain()
    }
}