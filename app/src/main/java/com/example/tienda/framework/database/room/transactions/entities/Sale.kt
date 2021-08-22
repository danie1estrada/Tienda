package com.example.tienda.framework.database.room.transactions.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sales")
data class Sale(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var productName: String,
    var price: Float,
    var quantity: Int,
    var date: String
)