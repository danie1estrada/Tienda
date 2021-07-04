package com.example.tienda.framework.database.room.transactions.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.tienda.framework.database.room.transactions.entities.Purchase;

import java.util.List;

@Dao
public interface PurchaseDao {
    @Query("SELECT * FROM purchases ORDER BY id DESC")
    LiveData<List<Purchase>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Purchase purchase);
}
