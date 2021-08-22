package com.example.tienda.products.data

import com.example.tienda.framework.database.room.products.daos.ProductDao
import androidx.lifecycle.LiveData
import com.example.tienda.framework.database.room.products.entities.Product
import com.example.tienda.framework.database.room.AppDatabase
import javax.inject.Inject

class ProductRepository @Inject constructor(private val dao: ProductDao) {

    val all: LiveData<List<Product>>
        get() = dao.all

    fun insert(product: Product) {
        AppDatabase.databaseWriteExecutor.execute { dao.insert(product) }
    }

    fun update(product: Product) {
        AppDatabase.databaseWriteExecutor.execute { dao.update(product) }
    }

    fun delete(product: Product) {
        AppDatabase.databaseWriteExecutor.execute { dao.delete(product) }
    }

}