package com.example.tienda.products.presentation.productslist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.tienda.products.data.ProductRepository
import androidx.lifecycle.LiveData
import com.example.tienda.framework.database.room.products.entities.Product

class ProductsListViewModel(application: Application) : AndroidViewModel(application) {
    val products: LiveData<List<Product>> = ProductRepository(application).all
}