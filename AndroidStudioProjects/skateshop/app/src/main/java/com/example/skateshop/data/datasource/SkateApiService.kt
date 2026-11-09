package com.example.skateshop.data.datasource

import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface SkateApiService {
    @GET("productos")
    suspend fun getProductos(): ResponseBody // <-- Devolvemos el cuerpo crudo de la red

    @GET("productos/{id}")
    suspend fun getProducto(@Path("id") id: String): ResponseBody

    @POST("productos")
    suspend fun createProducto(@Body producto: String): ResponseBody

    @PUT("productos/{id}")
    suspend fun updateProducto(@Path("id") id: String, @Body producto: String): ResponseBody

    @DELETE("productos/{id}")
    suspend fun deleteProducto(@Path("id") id: String): ResponseBody
}