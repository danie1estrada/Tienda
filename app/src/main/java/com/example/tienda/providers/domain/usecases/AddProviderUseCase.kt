package com.example.tienda.providers.domain.usecases

import com.example.tienda.framework.database.room.providers.entities.Provider
import com.example.tienda.providers.data.ProviderRepository
import javax.inject.Inject

class AddProviderUseCase @Inject constructor(private val repository: ProviderRepository) {
    operator fun invoke(provider: Provider) {
        repository.insert(provider)
    }
}