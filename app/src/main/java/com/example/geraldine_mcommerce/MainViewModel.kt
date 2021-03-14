package com.example.geraldine_mcommerce


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geraldine_mcommerce.model.Product
import com.example.geraldine_mcommerce.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

/*Viewmodel is the communication center between the repository and the UI*/

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse : MutableLiveData<Response<List<Product>>> = MutableLiveData()

    fun getProductListFromAPI(){
        viewModelScope.launch {
            val response = repository.getProductListAPI()
            myResponse.value = response
        }
    }


    suspend fun getProductsFromDB(){
        repository.putApiDataInDatabase()
    }

}