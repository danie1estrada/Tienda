package com.example.tienda.framework.database.room.transactions.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.tienda.framework.database.room.transactions.entities.Sale;

import java.util.List;

@Dao
public interface SaleDao {
    @Query("SELECT * FROM sales ORDER BY id DESC")
    LiveData<List<Sale>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Sale sale);
}
