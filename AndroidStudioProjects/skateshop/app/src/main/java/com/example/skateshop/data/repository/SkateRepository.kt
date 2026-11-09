package com.example.skateshop.data.repository

import com.example.skateshop.data.model.ProductoSkate

interface SkateRepository {
    suspend fun getProductos(): List<ProductoSkate>
    suspend fun getProducto(id: String): ProductoSkate
    suspend fun createProducto(producto: ProductoSkate): ProductoSkate
    suspend fun updateProducto(producto: ProductoSkate): ProductoSkate
    suspend fun deleteProducto(id: String)
}