package com.example.beautifulprincess

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.beautifulprincess.daos.ProductsDAO
import com.example.beautifulprincess.daos.UsersDAO
import com.example.beautifulprincess.models.Product
import com.example.beautifulprincess.models.User
import java.util.concurrent.Executors

@Database(entities = [User::class, Product::class], version = 1, exportSchema = false )
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao():UsersDAO
    abstract fun productsDao():ProductsDAO



    companion object {
        fun getInstance(context:Context):AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: getDbInstance(context).also { INSTANCE = it }
            }

        private var INSTANCE:AppDatabase? = null
        fun getDbInstance(context:Context):AppDatabase {
            if (INSTANCE == null) {
                val PREPOPULATE_DATA = listOf(
                    Product(
                        null,
                        "Pomade",
                        5.99f,
                        "This pomade is very beautiful for our princesses. The quality of the pomade is like petrol in a Tesla)",
                        "Pomades",
                        R.drawable.image_product
                    ),
                    Product(
                        null,
                        "Makeup brush",
                        4.99f,
                        "Our brush will transform any princess (yes yes, that's you) into a chic and lovely lady",
                        "Brushes",
                        R.drawable.makeup_brush
                    ),
                    Product(
                        null,
                        "Glitter",
                        2.99f,
                        "Decorate your face with glitter! This beautiful tool will help you become a real princess!",
                        "Decorate",
                        R.drawable.glitter_image
                    ),
                )

                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bp7.db"
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db:SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Executors.newSingleThreadExecutor().execute {
                                INSTANCE?.let {
                                    it.productsDao().insertProduct(PREPOPULATE_DATA)
                                }
                            }
                        }
                    })
                    .allowMainThreadQueries().build()

            }
            return INSTANCE!!
        }
    }
}
