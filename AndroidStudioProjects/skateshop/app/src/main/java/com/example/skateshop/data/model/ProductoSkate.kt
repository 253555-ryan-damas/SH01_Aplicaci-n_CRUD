package com.example.skateshop.data.model

data class ProductoSkate(
    val id: String,
    val nombre: String,
    val categoria: String,
    val precio: Double,
    val stock: Int,
    val esImportado: Boolean
)