package com.example.geraldine_mcommerce

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geraldine_mcommerce.adapter.ProductListAdapter
import com.example.geraldine_mcommerce.databinding.ActivityMainBinding
import com.example.geraldine_mcommerce.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/*This is the class that connect the data source with the UI*/

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    lateinit var binding : ActivityMainBinding
    private val productListAdapter by lazy { ProductListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)



        setupRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


        if (isOnline(this)){
            viewModel.getProductList()
            viewModel.myResponse.observe(this, Observer { response ->
                if (response.isSuccessful){
                    response.body()?.let { productListAdapter.setList(it) }
                }else{
                    Log.d("RESPONSE", response.code().toString() )
                }

            })

        }else{
            Toast.makeText(this, "NO INTERNET FOUND, SHOWING CACHED DATA -- TRADUIR EN FRANCAIS", Toast.LENGTH_SHORT).show()

            GlobalScope.launch (Dispatchers.Main) { viewModel.getProductsFromDB() }

            viewModel.myResponse.observe(this, Observer { response ->
                if (response.isSuccessful){
                    response.body()?.let { productListAdapter.setList(it) }
                }else{
                    Log.d("RESPONSE", response.code().toString() )
                }

            })
        }

    }

    private fun isOnline(appContext:Context): Boolean {
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }



    private fun setupRecyclerView(){
        product_recycler.adapter = productListAdapter
        product_recycler.layoutManager = LinearLayoutManager(this)
    }
}

