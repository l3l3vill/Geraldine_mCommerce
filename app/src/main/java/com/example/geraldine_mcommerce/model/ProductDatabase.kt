package com.example.geraldine_mcommerce.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.geraldine_mcommerce.dao.ProductDao

/*This represents the instance of the ProductDatabase*/

@Database( entities = [Product::class], version = 1)
abstract  class ProductDatabase : RoomDatabase() {

    abstract fun productDao():ProductDao

    // singleton, to avoid having many instances of the database
    companion object {
        @Volatile
        private var INSTANCE: ProductDatabase? = null

        fun getDatabase(context: Context): ProductDatabase {
            //check if instance of database exists
            val instance = INSTANCE
            if (instance != null) {
                return instance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    "product_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }


}


