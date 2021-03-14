package com.example.geraldine_mcommerce.dao
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.geraldine_mcommerce.model.Product

/*This is the Data access object where are defined the methods to manage the ProductDatabase, like insert products, read products or delete products */

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertAllProducts(productList : List<Product>)

    @Query("SELECT * FROM product_table")
    fun getAllData(): LiveData<List<Product>>

    @Query("DELETE FROM product_table")
    fun deleteAllProducts()


}