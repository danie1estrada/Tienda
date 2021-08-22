package com.example.tienda.transactions.presentation.purchases;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tienda.framework.database.room.transactions.entities.Purchase;
import com.example.tienda.transactions.data.TransactionRepository;

import java.util.List;

public class PurchasesViewModel extends AndroidViewModel {

    private final LiveData<List<Purchase>> purchases;

    public PurchasesViewModel(@NonNull Application application) {
        super(application);
        purchases = new TransactionRepository(application).getPurchases();
    }

    public LiveData<List<Purchase>> getPurchases() {
        return purchases;
    }
}
