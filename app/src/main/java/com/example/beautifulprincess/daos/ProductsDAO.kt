package com.example.beautifulprincess.daos

import androidx.room.*
import com.example.beautifulprincess.models.Product
import com.example.beautifulprincess.models.ProductRowModel
import com.example.beautifulprincess.models.User

@Dao
interface ProductsDAO {
    @Insert
    fun insertProduct(product: List<Product>)

    @Update
    fun updateProduct(product:Product)

    @Delete
    fun deleteProduct(product:Product)

    @Query("SELECT * FROM Product WHERE name = :name")
    fun getProductsByName(name: String): List<Product>

    @Query("SELECT * FROM Product")
    fun getAllProducts(): List<Product>

    @Query("SELECT * FROM Product WHERE category = :category")
    fun getProductsByCategory(category: String):List<Product>

    @Query("SELECT * FROM Product WHERE name = :name AND category = :category")
    fun getProductsByCategoryAndName(name: String, category: String):List<Product>

    @Query("SELECT * FROM Product WHERE id_product = :productId")
    fun getProductById(productId: Int?): Product
}