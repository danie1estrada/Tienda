package com.example.tienda.framework.database.room.providers.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tienda.framework.database.room.providers.entities.Provider;

import java.util.List;

@Dao
public interface ProviderDao {
    @Query("SELECT * FROM providers")
    LiveData<List<Provider>> getAll();

    @Query("SELECT * FROM providers WHERE id = :id LIMIT 1")
    LiveData<Provider> getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Provider provider);

    @Update
    void update(Provider provider);

    @Delete
    void delete(Provider provider);
}
