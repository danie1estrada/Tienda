package com.example.tienda.transactions.di

import com.example.tienda.framework.database.room.transactions.daos.PurchaseDao
import com.example.tienda.framework.database.room.transactions.daos.SaleDao
import com.example.tienda.transactions.data.TransactionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TransactionsModule {
    @Provides
    @Singleton
    fun provideTransactionRepository(saleDao: SaleDao, purchaseDao: PurchaseDao): TransactionRepository {
        return TransactionRepository(saleDao, purchaseDao)
    }
}