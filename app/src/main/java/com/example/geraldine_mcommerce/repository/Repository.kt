package com.example.geraldine_mcommerce.repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import com.example.geraldine_mcommerce.api.ProductApi
import com.example.geraldine_mcommerce.api.RetrofitInstance
import com.example.geraldine_mcommerce.model.Product
import com.example.geraldine_mcommerce.model.ProductDatabase
import com.example.geraldine_mcommerce.util.Constants
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {


    //Get Data from API
    suspend fun getProductList(): Response<List<Product>> {
        return RetrofitInstance.api.getProductList()
    }


    // Get data from Database
    class InsertProductData(val list : List<Product>, val application: Application): AsyncTask<Void,Void,Void>(){
        override fun doInBackground(vararg params: Void?): Void? {
            ProductDatabase.getDatabase(application).productDao().insertAllProducts(list)
            return null
        }


    }

    // Put data from API in DB
    suspend fun putApiDataInDatabase(){
        val gson = Gson()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.BASE_URL)
            .build()

        val productApi = retrofit.create<ProductApi>(ProductApi::class.java)
        productApi.getProductList()
        (object : Callback<List<Product>>{
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Log.e("REPOSITORY", t.toString())
            }

            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                Log.e("REPOSITORY", response!!.body().toString())
                val application = Application()
                when (response.code()){
                    200 ->{
                        Thread(Runnable {
                            response.body()?.let { InsertProductData(it,application) }
                        }).start()
                    }
                }
            }

        })
    }
}