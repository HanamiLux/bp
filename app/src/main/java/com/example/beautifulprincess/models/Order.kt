package com.example.beautifulprincess.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "Orders",
    foreignKeys = [ForeignKey(
entity = User::class,
        parentColumns = ["id_user"],
        childColumns =["userId"],
        onDelete = CASCADE
    ), ForeignKey(
        entity = Product::class,
        parentColumns = ["id_product"],
        childColumns = ["productId"],
        onDelete = CASCADE
    )])

data class Order(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var productId: Int?,
    var userId: Int?,
    var quantity: Int
)
