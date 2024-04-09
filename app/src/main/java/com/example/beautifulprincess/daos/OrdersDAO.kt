package com.example.beautifulprincess.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.beautifulprincess.models.Order
@Dao
interface OrdersDAO {
    @Insert
    fun insertOrder(order: Order)

    @Update
    fun updateOrderQuantity(order: Order)

    @Query("SELECT productId, userId, COUNT(*) AS quantity FROM Orders WHERE userId = :userId GROUP BY productId")
    fun getUserOrderedProducts(userId: Int): List<Order>


    @Query("DELETE FROM Orders WHERE userId = :userId")
    fun closeOrder(userId: Int)


}

