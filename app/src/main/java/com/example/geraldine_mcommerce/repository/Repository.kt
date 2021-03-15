package com.example.geraldine_mcommerce.repository

import android.app.Application
import android.os.AsyncTask
import com.example.geraldine_mcommerce.api.RetrofitInstance
import com.example.geraldine_mcommerce.model.Product
import com.example.geraldine_mcommerce.model.ProductDatabase
import retrofit2.Response


/*This class extracts the data from different data sources*/
class Repository {


    //Get Data from API
    suspend fun getProductListAPI(): Response<List<Product>> {
        return RetrofitInstance.api.getProductList()
    }


    // Get data from Database -> no used !!

/* class InsertProductData(val list : List<Product>, val application: Application): AsyncTask<Void,Void,Void>(){
 override fun doInBackground(vararg params: Void?): Void? {
 ProductDatabase.getDatabase(application).productDao().insertAllProducts(list)
 return null
 }


 }*/

    // Put data from API in DB -> Method no used !!
    suspend fun putApiDataInDatabase(){
        getProductListAPI()
        //todo:  how can we get the API data, and make a call in order to know if we can insert the response into the dataBase ?
/*        (object : Callback<List<Product>>{
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

        })*/
    }
}