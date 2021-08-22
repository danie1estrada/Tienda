package com.example.tienda.products.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.tienda.framework.database.room.AppDatabase;
import com.example.tienda.framework.database.room.products.daos.ProductDao;
import com.example.tienda.framework.database.room.products.entities.Product;

import java.util.List;

public class ProductRepository {
    private final ProductDao dao;

    public ProductRepository(Application application) {
        dao = AppDatabase.getInstance(application).productDao();
    }

    public LiveData<List<Product>> getAll() {
        return dao.getAll();
    }

    public LiveData<Product> getById(int id) {
        return dao.getById(id);
    }

    public void insert(Product product) {
        AppDatabase.databaseWriteExecutor.execute(() ->
            dao.insert(product)
        );
    }

    public void update(Product product) {
        AppDatabase.databaseWriteExecutor.execute(() ->
            dao.update(product)
        );
    }

    public void delete(Product product) {
        AppDatabase.databaseWriteExecutor.execute(() ->
            dao.delete(product)
        );
    }
}
