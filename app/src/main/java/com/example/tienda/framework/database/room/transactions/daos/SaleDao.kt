package com.example.tienda.framework.database.room.transactions.daos

import androidx.room.Dao
import androidx.lifecycle.LiveData
import androidx.room.Insert
import com.example.tienda.framework.database.room.transactions.entities.Sale
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SaleDao {
    @get:Query("SELECT * FROM sales ORDER BY id DESC")
    val sales: LiveData<List<Sale>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sale: Sale)
}