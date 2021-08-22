package com.example.tienda.framework.database.room.products.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var name: String,
    var price: Float,
    var quantity: Int,
    var provider: String
) {
    fun add(quantity: Int) {
        this.quantity += quantity
    }

    fun subtract(quantity: Int) {
        this.quantity -= quantity
    }

    override fun toString(): String {
        return name
    }
}