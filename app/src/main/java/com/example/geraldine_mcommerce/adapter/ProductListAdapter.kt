package com.example.geraldine_mcommerce.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.geraldine_mcommerce.R
import com.example.geraldine_mcommerce.databinding.ProductCardBinding
import com.example.geraldine_mcommerce.model.Product
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import okhttp3.Protocol
import java.util.*
import kotlin.collections.ArrayList

/*Here there is the Product List adapter that will manage the way all the elements of the recycler view interact.
* This contains the class that holds de view (viewholder) and inside it has the method to paste (bind) all the view elements
* inside the recyclerview.
*  The recycler view is created and then the elements are binded whit the help of product Viewholder. */

class ProductListAdapter : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    private var productList : List<Product> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ProductListAdapter.ProductViewHolder{
        return ProductViewHolder(ProductCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductListAdapter.ProductViewHolder, position: Int) {
        val product = productList [position]
        holder.onBind(product)
    }

    fun setList (newProductList : List<Product>){
        productList = newProductList
        notifyDataSetChanged()
    }



    inner class ProductViewHolder (val binding : ProductCardBinding) : RecyclerView.ViewHolder(binding.root){


        fun onBind(product: Product ){

            // As the image is not fetching because it is a non secure site, the url will work if we add the 's' to the link
            val newUrl = product.image.toString().replace("http","https")
            binding.tvProductName.text = product.name
            binding.tvPrice.text = product.price.toString()
            Picasso.get().load(newUrl).placeholder(R.drawable.ic_launcher_background).into(binding.ivProduct)



        }
    }
}
