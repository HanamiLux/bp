package com.example.beautifulprincess.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.beautifulprincess.models.User

@Dao
interface UsersDAO {
    @Insert
    fun insertUser(user:User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM  Users WHERE login = :login")
    fun getUser(login: String): User

    @Query("SELECT * FROM Users")
    fun getAllUsers(): List<User>
}