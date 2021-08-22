package com.example.tienda.transactions.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.tienda.framework.database.room.transactions.daos.PurchaseDao
import com.example.tienda.framework.database.room.transactions.daos.SaleDao
import com.example.tienda.framework.database.room.transactions.entities.Sale
import com.example.tienda.framework.database.room.AppDatabase
import com.example.tienda.framework.database.room.transactions.entities.Purchase

class TransactionRepository(application: Application) {

    private val purchaseDao: PurchaseDao
    private val saleDao: SaleDao

    val sales: LiveData<List<Sale>>
        get() = saleDao.sales

    val purchases: LiveData<List<Purchase>>
        get() = purchaseDao.purchases

    init {
        val database = AppDatabase.getInstance(application)
        purchaseDao = database.purchaseDao()
        saleDao = database.saleDao()
    }

    fun insertSale(sale: Sale) {
        AppDatabase.databaseWriteExecutor.execute { saleDao.insert(sale) }
    }

    fun insertPurchase(purchase: Purchase) {
        AppDatabase.databaseWriteExecutor.execute { purchaseDao.insert(purchase) }
    }
}