package com.example.mvvmdagger.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvmdagger.models.Product

@Dao
interface FakerDAO {

    @Insert
    suspend fun addProducts(product: List<Product>)

    @Query("SELECT * FROM Product")
    suspend fun getProducts() : List<Product>
}