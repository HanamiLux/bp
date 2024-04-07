package com.example.beautifulprincess.models

import androidx.room.*

@Entity(tableName = "Users")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_user")
    var id: Int?,
    var login: String,
    var password: String)