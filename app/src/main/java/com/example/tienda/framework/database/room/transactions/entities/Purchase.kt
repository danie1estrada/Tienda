package com.example.tienda.framework.database.room.transactions.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchases")
data class Purchase(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var date: String,
    var product: String,
    var price: Float,
    var provider: String,
    var quantity: Int
)