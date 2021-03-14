package com.example.geraldine_mcommerce.api


import com.example.geraldine_mcommerce.model.Product
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

/*This represents the API where are defined the methods to make the requests, in this case GET, to retrieve all the information */

interface ProductApi {

    @GET("products.json")
    suspend fun getProductList(): Response<List<Product>>




}