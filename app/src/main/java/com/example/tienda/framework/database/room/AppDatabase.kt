package com.example.tienda.framework.database.room

import android.content.Context
import androidx.room.Database
import com.example.tienda.framework.database.room.products.entities.Product
import com.example.tienda.framework.database.room.transactions.entities.Sale
import com.example.tienda.framework.database.room.transactions.entities.Purchase
import androidx.room.RoomDatabase
import com.example.tienda.framework.database.room.products.daos.ProductDao
import com.example.tienda.framework.database.room.providers.daos.ProviderDao
import com.example.tienda.framework.database.room.transactions.daos.PurchaseDao
import com.example.tienda.framework.database.room.transactions.daos.SaleDao
import kotlin.jvm.Volatile
import androidx.room.Room
import com.example.tienda.framework.database.room.providers.entities.Provider
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(
    entities = [Product::class, Provider::class, Sale::class, Purchase::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun providerDao(): ProviderDao
    abstract fun purchaseDao(): PurchaseDao
    abstract fun saleDao(): SaleDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getInstance(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "db"
            ).build().also { INSTANCE = it }
        }
    }
}