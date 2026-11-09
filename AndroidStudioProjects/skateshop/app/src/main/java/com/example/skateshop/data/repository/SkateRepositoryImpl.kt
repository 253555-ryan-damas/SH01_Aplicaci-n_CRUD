package com.example.skateshop.data.repository

import com.example.skateshop.data.datasource.SkateApiService
import com.example.skateshop.data.dto.ProductoSkateDto
import com.example.skateshop.data.mapper.toDomain
import com.example.skateshop.data.model.ProductoSkate
import kotlinx.serialization.json.Json

class SkateRepositoryImpl(
    private val apiService: SkateApiService
) : SkateRepository {

    // Instancia de Json configurada sin miedo a errores
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    override suspend fun getProductos(): List<ProductoSkate> {
        val responseBody = apiService.getProductos()
        val jsonString = responseBody.string()

        // Parseamos la lista plana directamente con kotlinx.serialization
        val dtos: List<ProductoSkateDto> = json.decodeFromString(jsonString)
        return dtos.map { it.toDomain() }
    }

    override suspend fun getProducto(id: String): ProductoSkate {
        val jsonString = apiService.getProducto(id).string()
        val dto: ProductoSkateDto = json.decodeFromString(jsonString)
        return dto.toDomain()
    }

    override suspend fun createProducto(producto: ProductoSkate): ProductoSkate {
        // Omitimos por ahora si prefieres centrarte en la lista, pero mantiene la misma estructura
        throw NotImplementedError()
    }

    override suspend fun updateProducto(producto: ProductoSkate): ProductoSkate {
        throw NotImplementedError()
    }

    override suspend fun deleteProducto(id: String) {
        apiService.deleteProducto(id)
    }
}