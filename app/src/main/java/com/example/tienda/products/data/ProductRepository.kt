package com.example.tienda.products.data

import android.app.Application
import com.example.tienda.framework.database.room.products.daos.ProductDao
import androidx.lifecycle.LiveData
import com.example.tienda.framework.database.room.products.entities.Product
import com.example.tienda.framework.database.room.AppDatabase

class ProductRepository(application: Application) {

    private val dao: ProductDao = AppDatabase.getInstance(application).productDao()

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