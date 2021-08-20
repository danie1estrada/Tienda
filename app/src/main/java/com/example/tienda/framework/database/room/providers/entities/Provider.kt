package com.example.tienda.framework.database.room.providers.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "providers")
data class Provider(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val company: String,
    val phone: String
)