package com.example.tienda.framework.database.room.providers.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "providers")
data class Provider(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var name: String,
    var company: String,
    var phone: String
)