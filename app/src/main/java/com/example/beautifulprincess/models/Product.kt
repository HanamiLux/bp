package com.example.beautifulprincess.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product")
data class Product (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_product")
    var id: Int?,
    var name: String,
    var price: Float,
    var description: String,
    var category: String,
    var image: Int
)