package com.example.geraldine_mcommerce.api


import com.example.geraldine_mcommerce.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*This represents the RETROFIT instance that will help us to fetch the API information (products in Json format) and to parse it into Product Class */

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ProductApi by lazy {
        retrofit.create(ProductApi::class.java)
    }


}