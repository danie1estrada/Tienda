package com.example.tienda.products.presentation.productslist

import com.example.tienda.products.data.ProductRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tienda.framework.database.room.products.entities.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    repository: ProductRepository
) : ViewModel() {
    val products: LiveData<List<Product>> = repository.all
}