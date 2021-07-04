package com.example.tienda.transactions.presentation.addsale;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.tienda.R;
import com.example.tienda.framework.database.room.products.entities.Product;
import com.example.tienda.framework.database.room.transactions.entities.Sale;
import com.example.tienda.products.data.ProductRepository;
import com.example.tienda.transactions.data.TransactionRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddSaleViewModel extends AndroidViewModel {

    private final ProductRepository productRepository;
    private final LiveData<List<Product>> products;
    private final TransactionRepository repository;

    private final MutableLiveData<Product> product = new MutableLiveData<>();

    private final LiveData<String> price = Transformations.map(product, input -> {
        if (input == null)
            return "";
        return String.format(Locale.getDefault(), "%.2f", input.getPrice());
    });

    private MutableLiveData<String> quantity = new MutableLiveData<>("");

    private final MediatorLiveData<Float> total = new MediatorLiveData<>();


    public AddSaleViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
        products = productRepository.getAll();

        repository = new TransactionRepository(application);

        total.addSource(price, input -> {
            if (!input.isEmpty() && !quantity.getValue().isEmpty())
                total.setValue(Float.parseFloat(input) * Float.parseFloat(quantity.getValue()));
            else
                total.setValue(0f);
        });

        total.addSource(quantity, input -> {
            if (!input.isEmpty() && !quantity.getValue().isEmpty())
                total.setValue(Float.parseFloat(input) * Float.parseFloat(price.getValue()));
            else
                total.setValue(0f);
        });
    }

    public void save() {
        try {
            Product productSold = product.getValue();
            repository.insertSale(new Sale(
               0,
               productSold.getName(),
               productSold.getPrice(),
               Integer.parseInt(quantity.getValue()),
               new SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault()).format(new Date())
            ));
            productSold.subtract(Integer.parseInt(quantity.getValue()));
            productRepository.update(productSold);
            resetState();
            Toast.makeText(getApplication(), R.string.sale_info_saved, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplication(), R.string.message_error, Toast.LENGTH_LONG).show();
        }
    }

    private void resetState() {
        product.setValue(null);
        quantity.setValue("");
    }

    public LiveData<List<Product>> getProducts() {
        return products;
    }

    public void setProduct(Product product) {
        this.product.setValue(product);
    }

    public MutableLiveData<Product> getProduct() {
        return product;
    }

    public LiveData<String> getPrice() {
        return price;
    }

    public MutableLiveData<String> getQuantity() {
        return quantity;
    }

    public void setQuantity(MutableLiveData<String> quantity) {
        this.quantity = quantity;
    }

    public MediatorLiveData<Float> getTotal() {
        return total;
    }

}
