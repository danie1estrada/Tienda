package com.example.tienda.transactions.data

import androidx.lifecycle.LiveData
import com.example.tienda.framework.database.room.transactions.daos.PurchaseDao
import com.example.tienda.framework.database.room.transactions.daos.SaleDao
import com.example.tienda.framework.database.room.transactions.entities.Sale
import com.example.tienda.framework.database.room.AppDatabase
import com.example.tienda.framework.database.room.transactions.entities.Purchase
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val saleDao: SaleDao,
    private val purchaseDao: PurchaseDao
) {
    val sales: LiveData<List<Sale>>
        get() = saleDao.sales

    val purchases: LiveData<List<Purchase>>
        get() = purchaseDao.purchases

    fun insertSale(sale: Sale) {
        AppDatabase.databaseWriteExecutor.execute { saleDao.insert(sale) }
    }

    fun insertPurchase(purchase: Purchase) {
        AppDatabase.databaseWriteExecutor.execute { purchaseDao.insert(purchase) }
    }
}