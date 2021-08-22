package com.example.tienda.framework.database.room.di

import android.content.Context
import com.example.tienda.framework.database.room.AppDatabase
import com.example.tienda.framework.database.room.products.daos.ProductDao
import com.example.tienda.framework.database.room.providers.daos.ProviderDao
import com.example.tienda.framework.database.room.transactions.daos.PurchaseDao
import com.example.tienda.framework.database.room.transactions.daos.SaleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideProviderDao(database: AppDatabase): ProviderDao {
        return database.providerDao()
    }

    @Provides
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    fun provideSaleDao(database: AppDatabase): SaleDao {
        return database.saleDao()
    }

    @Provides
    fun providePurchaseDao(database: AppDatabase): PurchaseDao {
        return database.purchaseDao()
    }
}