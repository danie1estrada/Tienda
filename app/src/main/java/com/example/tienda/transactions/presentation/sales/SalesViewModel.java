package com.example.tienda.transactions.presentation.sales;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tienda.framework.database.room.transactions.entities.Sale;
import com.example.tienda.transactions.data.TransactionRepository;

import java.util.List;

public class SalesViewModel extends AndroidViewModel {

    private final LiveData<List<Sale>> sales;

    public SalesViewModel(@NonNull Application application) {
        super(application);
        sales = new TransactionRepository(application).getSales();
    }

    public LiveData<List<Sale>> getSales() {
        return sales;
    }
}
