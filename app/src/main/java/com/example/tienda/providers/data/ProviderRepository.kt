package com.example.tienda.providers.data

import android.app.Application
import com.example.tienda.framework.database.room.providers.daos.ProviderDao
import androidx.lifecycle.LiveData
import com.example.tienda.framework.database.room.AppDatabase
import com.example.tienda.framework.database.room.providers.entities.Provider

class ProviderRepository(application: Application) {

    private val dao: ProviderDao = AppDatabase.getInstance(application).providerDao()

    val all: LiveData<List<Provider>>
        get() = dao.all

    fun getById(id: Int): LiveData<Provider?> {
        return dao.getById(id)
    }

    fun insert(provider: Provider) {
        AppDatabase.databaseWriteExecutor.execute { dao.insert(provider) }
    }

    fun update(provider: Provider) {
        AppDatabase.databaseWriteExecutor.execute { dao.update(provider) }
    }

    fun delete(provider: Provider) {
        AppDatabase.databaseWriteExecutor.execute { dao.delete(provider) }
    }

}