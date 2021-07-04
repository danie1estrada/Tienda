package com.example.tienda.framework.database.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tienda.framework.database.room.products.daos.ProductDao;
import com.example.tienda.framework.database.room.products.entities.Product;
import com.example.tienda.framework.database.room.providers.daos.ProviderDao;
import com.example.tienda.framework.database.room.providers.entities.Provider;
import com.example.tienda.framework.database.room.transactions.daos.PurchaseDao;
import com.example.tienda.framework.database.room.transactions.daos.SaleDao;
import com.example.tienda.framework.database.room.transactions.entities.Purchase;
import com.example.tienda.framework.database.room.transactions.entities.Sale;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
    entities = {
        Product.class,
        Provider.class,
        Sale.class,
        Purchase.class
    },
    version = 1,
    exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProductDao productDao();
    public abstract ProviderDao providerDao();
    public abstract PurchaseDao purchaseDao();
    public abstract SaleDao saleDao();

    private static volatile  AppDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDatabase.class,
                        "db"
                    ).build();
                }
            }
        }

        return INSTANCE;
    }
}
