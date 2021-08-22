package com.example.tienda.framework.database.room.transactions.daos

import androidx.room.Dao
import androidx.lifecycle.LiveData
import androidx.room.Insert
import com.example.tienda.framework.database.room.transactions.entities.Purchase
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PurchaseDao {
    @get:Query("SELECT * FROM purchases ORDER BY id DESC")
    val purchases: LiveData<List<Purchase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(purchase: Purchase)
}