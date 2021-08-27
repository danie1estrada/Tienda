package com.example.tienda.providers.data.repositories

import androidx.lifecycle.LiveData
import com.example.tienda.framework.database.room.AppDatabase
import com.example.tienda.framework.database.room.providers.entities.Provider
import com.example.tienda.providers.domain.datasources.ProviderLocalDataSource
import javax.inject.Inject

class ProviderRepository @Inject constructor(
    private val localDataSource: ProviderLocalDataSource
) {

    val all: LiveData<List<Provider>>
        get() = localDataSource.getAll()

    fun getById(id: Int): LiveData<Provider?> {
        return localDataSource.getById(id)
    }

    fun insert(provider: Provider) {
        AppDatabase.databaseWriteExecutor.execute { localDataSource.insert(provider) }
    }

    fun update(provider: Provider) {
        AppDatabase.databaseWriteExecutor.execute { localDataSource.update(provider) }
    }

    fun delete(provider: Provider) {
        AppDatabase.databaseWriteExecutor.execute { localDataSource.delete(provider) }
    }

}