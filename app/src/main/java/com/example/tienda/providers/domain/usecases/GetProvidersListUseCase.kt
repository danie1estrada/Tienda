package com.example.tienda.providers.domain.usecases

import androidx.lifecycle.LiveData
import com.example.tienda.framework.database.room.providers.entities.Provider
import com.example.tienda.providers.data.repositories.ProviderRepository
import javax.inject.Inject

class GetProvidersListUseCase @Inject constructor(private val repository: ProviderRepository) {
    operator fun invoke(): LiveData<List<Provider>> {
        return repository.all
    }
}