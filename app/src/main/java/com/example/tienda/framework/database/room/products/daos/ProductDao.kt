package com.example.tienda.framework.database.room.products.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tienda.framework.database.room.products.entities.Product

@Dao
interface ProductDao {
    @get:Query("SELECT * FROM products")
    val all: LiveData<List<Product>>

    @Query("SELECT * FROM products WHERE id = :id LIMIT 1")
    fun getById(id: Int): LiveData<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Delete
    fun delete(product: Product)
}