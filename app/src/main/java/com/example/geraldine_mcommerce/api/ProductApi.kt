package com.example.geraldine_mcommerce.api


import com.example.geraldine_mcommerce.model.Product
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

/*This represents the API where are defined the methods to make the requests.
 Here is used GET, to retrieve all the product information */

interface ProductApi {

    @GET("products.json")
    suspend fun getProductList(): Response<List<Product>>




}