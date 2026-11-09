package com.example.skateshop.data.mapper

import com.example.skateshop.data.dto.ProductoSkateDto
import com.example.skateshop.data.model.ProductoSkate

// De la API hacia la App
fun ProductoSkateDto.toDomain(): ProductoSkate {
    return ProductoSkate(
        id = this.id ?: "", // Si la API no manda ID, ponemos cadena vacía
        nombre = this.nombre,
        categoria = this.categoria,
        precio = this.precio,
        stock = this.stock,
        esImportado = this.esImportado
    )
}

// De la App hacia la API
fun ProductoSkate.toDto(): ProductoSkateDto {
    return ProductoSkateDto(
        id = if (this.id.isEmpty()) null else this.id,
        nombre = this.nombre,
        categoria = this.categoria,
        precio = this.precio,
        stock = this.stock,
        esImportado = this.esImportado
    )
}