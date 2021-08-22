package com.example.tienda.providers.di

import com.example.tienda.framework.database.room.providers.daos.ProviderDao
import com.example.tienda.providers.data.ProviderRepository
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
}