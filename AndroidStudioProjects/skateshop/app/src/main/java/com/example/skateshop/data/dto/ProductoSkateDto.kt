package com.example.skateshop.data.dto

import kotlinx.serialization.Serializable

@Serializable // <-- ¡Esta etiqueta es obligatoria!
data class ProductoSkateDto(
    val id: String? = null,
    val nombre: String,
    val categoria: String,
    val precio: Double,
    val stock: Int,
    val esImportado: Boolean
)