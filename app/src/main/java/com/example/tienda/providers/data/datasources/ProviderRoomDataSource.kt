package com.example.tienda.providers.data.datasources

import androidx.lifecycle.LiveData
import com.example.tienda.framework.database.room.providers.daos.ProviderDao
import com.example.tienda.framework.database.room.providers.entities.Provider
import com.example.tienda.providers.domain.datasources.ProviderLocalDataSource
import javax.inject.Inject

class ProviderRoomDataSource @Inject constructor(
    private val dao: ProviderDao
) : ProviderLocalDataSource {

    override fun getAll(): LiveData<List<Provider>> {
        return dao.all
    }

    override fun getById(id: Int): LiveData<Provider?> {
        return dao.getById(id)
    }

    override fun insert(provider: Provider) {
        dao.insert(provider)
    }

    override fun update(provider: Provider) {
        dao.update(provider)
    }

    override fun delete(provider: Provider) {
        dao.delete(provider)
    }
}