package com.example.geraldine_mcommerce.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/*This is the product class. The entity is added to design the room table.
Each attribute represents one column, and the column id is anotated as Primary Key*/

@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey val id: Int,
    val image: String?,
    val name: String,
    val price: Double
)