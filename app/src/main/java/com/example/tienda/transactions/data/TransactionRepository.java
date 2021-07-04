package com.example.tienda.transactions.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.tienda.framework.database.room.AppDatabase;
import com.example.tienda.framework.database.room.transactions.daos.PurchaseDao;
import com.example.tienda.framework.database.room.transactions.daos.SaleDao;
import com.example.tienda.framework.database.room.transactions.entities.Purchase;
import com.example.tienda.framework.database.room.transactions.entities.Sale;

import java.util.List;

public class TransactionRepository {
    private final PurchaseDao purchaseDao;
    private final SaleDao saleDao;

    public TransactionRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        purchaseDao = database.purchaseDao();
        saleDao = database.saleDao();
    }

    public LiveData<List<Sale>> getSales() {
        return saleDao.getAll();
    }

    public void insertSale(Sale sale) {
        AppDatabase.databaseWriteExecutor.execute(() ->
            saleDao.insert(sale)
        );
    }

    public LiveData<List<Purchase>> getPurchases() {
        return purchaseDao.getAll();
    }

    public void insertPurchase(Purchase purchase) {
        AppDatabase.databaseWriteExecutor.execute(() ->
            purchaseDao.insert(purchase)
        );
    }
}
