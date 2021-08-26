package com.example.tienda.providers.di

import com.example.tienda.framework.database.room.providers.daos.ProviderDao
import com.example.tienda.providers.data.ProviderRepository
import com.example.tienda.providers.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvidersModule {
    @Provides
    @Singleton
    fun provideProvidersRepository(dao: ProviderDao): ProviderRepository {
        return ProviderRepository(dao)
    }

    @Provides
    @Singleton
    fun provideGetProvidersListUseCase(repository: ProviderRepository): GetProvidersListUseCase {
        return GetProvidersListUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideFindProviderByIdUseCase(repository: ProviderRepository): FindProviderByIdUseCase {
        return FindProviderByIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAddProviderUseCase(repository: ProviderRepository): AddProviderUseCase {
        return AddProviderUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateProviderUseCase(repository: ProviderRepository): UpdateProviderUseCase {
        return UpdateProviderUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteProviderUseCase(repository: ProviderRepository): DeleteProviderUseCase {
        return DeleteProviderUseCase(repository)
    }
}