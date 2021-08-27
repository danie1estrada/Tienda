package com.example.tienda.providers.domain.usecases

import androidx.lifecycle.LiveData
import com.example.tienda.framework.database.room.providers.entities.Provider
import com.example.tienda.providers.data.repositories.ProviderRepository
import javax.inject.Inject

class FindProviderByIdUseCase @Inject constructor(private val repository: ProviderRepository) {
    operator fun invoke(id: Int): LiveData<Provider?> {
        return repository.getById(id)
    }
}