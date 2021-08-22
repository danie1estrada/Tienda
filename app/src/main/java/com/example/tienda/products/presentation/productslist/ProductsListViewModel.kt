package com.example.tienda.products.presentation.productslist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tienda.framework.database.room.products.entities.Product;
import com.example.tienda.products.data.ProductRepository;

import java.util.List;

public class ProductsListViewModel extends AndroidViewModel {

    private final ProductRepository repository;
    private LiveData<List<Product>> products;

    public ProductsListViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductRepository(application);
        products = repository.getAll();
    }

    public LiveData<List<Product>> getProducts() {
        return products;
    }
}
