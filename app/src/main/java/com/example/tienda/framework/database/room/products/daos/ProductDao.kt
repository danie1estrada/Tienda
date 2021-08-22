package com.example.tienda.framework.database.room.products.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tienda.framework.database.room.products.entities.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM products")
    LiveData<List<Product>> getAll();

    @Query("SELECT * FROM products WHERE id = :id LIMIT 1")
    LiveData<Product> getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product product);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);
}
