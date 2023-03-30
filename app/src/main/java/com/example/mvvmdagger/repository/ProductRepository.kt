package com.example.mvvmdagger.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmdagger.db.FakerDB
import com.example.mvvmdagger.models.Product
import com.example.mvvmdagger.retrofit.FakerAPI
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val fakerAPI: FakerAPI,
    private val fakerDB: FakerDB
) {

    private val _products = MutableLiveData<List<Product>>()
    public val products: LiveData<List<Product>>
        get() = _products

    suspend fun getProducts() {
        val result = fakerAPI.getProducts()
        fakerDB.getFakerDAO().addProducts(result.body()!!)
        if (result.body() != null && result.isSuccessful) {
            _products.postValue(result.body())
        }
    }

}