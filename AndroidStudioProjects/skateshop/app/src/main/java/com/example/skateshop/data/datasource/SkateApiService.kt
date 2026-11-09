package com.example.skateshop.data.datasource

import com.example.skateshop.data.dto.ProductoSkateDto
import com.example.skateshop.data.dto.UsuarioDto
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface SkateApiService {
    @GET("productos")
    suspend fun getProductos(): ResponseBody

    @GET("productos/{id}")
    suspend fun getProducto(@Path("id") id: String): ResponseBody

    @POST("productos")
    suspend fun createProducto(@Body producto: ProductoSkateDto): ProductoSkateDto

    @PUT("productos/{id}")
    suspend fun updateProducto(@Path("id") id: String, @Body producto: ProductoSkateDto): ProductoSkateDto

    @DELETE("productos/{id}")
    suspend fun deleteProducto(@Path("id") id: String): ResponseBody

    // --- Endpoints de Autenticación y Registro ---
    @GET("usuarios")
    suspend fun getUsuarios(): List<UsuarioDto>
    @Headers("Content-Type: application/json")
    @POST("usuarios")
    suspend fun registerUsuario(@Body usuario: UsuarioDto): UsuarioDto
}