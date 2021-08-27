package com.example.tienda.providers.domain.datasources

import androidx.lifecycle.LiveData
import com.example.tienda.framework.database.room.providers.entities.Provider

interface ProviderLocalDataSource {
    fun getAll(): LiveData<List<Provider>>

    fun getById(id: Int): LiveData<Provider?>

    fun insert(provider: Provider)

    fun update(provider: Provider)

    fun delete(provider: Provider)
}