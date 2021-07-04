package com.example.tienda.products.presentation.addproduct;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.tienda.R;
import com.example.tienda.framework.database.room.products.entities.Product;
import com.example.tienda.framework.database.room.providers.entities.Provider;
import com.example.tienda.framework.database.room.transactions.entities.Purchase;
import com.example.tienda.products.data.ProductRepository;
import com.example.tienda.providers.data.ProviderRepository;
import com.example.tienda.transactions.data.TransactionRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddProductViewModel extends AndroidViewModel {

    private final TransactionRepository transactionRepository;
    private final ProductRepository repository;
    private final LiveData<List<String>> providers;

    private MutableLiveData<String> name = new MutableLiveData<>("");
    private MutableLiveData<String> price = new MutableLiveData<>("");
    private MutableLiveData<String> quantity = new MutableLiveData<>("");
    private MutableLiveData<String> provider = new MutableLiveData<>("");

    private MutableLiveData<String> purchasePrice = new MutableLiveData<>("");
    private MutableLiveData<Boolean> isPurchase = new MutableLiveData<>(false);

    public AddProductViewModel(@NonNull Application application) {
        super(application);

        transactionRepository = new TransactionRepository(application);
        repository = new ProductRepository(application);

        providers = Transformations.map(new ProviderRepository(application).getAll(), input -> {
            ArrayList<String> providers = new ArrayList<>();
            for (Provider provider : input)
                providers.add(provider.getCompany());

            return providers;
        });
    }

    public void save() {
        if (isPurchase.getValue()) {
            saveAsPurchase(saveProduct());
            Toast.makeText(getApplication(), R.string.purchase_info_saved, Toast.LENGTH_LONG).show();
        } else {
            saveProduct();
            Toast.makeText(getApplication(), R.string.product_info_saved, Toast.LENGTH_LONG).show();
        }
        resetState();
    }

    private Product saveProduct() {
        Product product = new Product(
            0,
            name.getValue(),
            Float.parseFloat(price.getValue()),
            Integer.parseInt(quantity.getValue()),
            provider.getValue()
        );

        try {
            repository.insert(product);
        } catch (Exception e) {
            Toast.makeText(getApplication(), R.string.message_error, Toast.LENGTH_LONG).show();
        }

        return product;
    }

    private void saveAsPurchase(Product product) {
        try {
            transactionRepository.insertPurchase(new Purchase(
                0,
                new SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault()).format(new Date()),
                product.getName(),
                Float.parseFloat(purchasePrice.getValue()),
                product.getProvider(),
                product.getQuantity()
            ));
        } catch (Exception e) {
            Toast.makeText(getApplication(), R.string.message_error, Toast.LENGTH_LONG).show();
        }
    }

    private void resetState() {
        name.setValue("");
        price.setValue("");
        quantity.setValue("");
        provider.setValue("");
        purchasePrice.setValue("");
        isPurchase.setValue(false);
    }

    public LiveData<List<String>> getProviders() {
        return providers;
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public void setName(MutableLiveData<String> name) {
        this.name = name;
    }

    public MutableLiveData<String> getPrice() {
        return price;
    }

    public void setPrice(MutableLiveData<String> price) {
        this.price = price;
    }

    public MutableLiveData<String> getQuantity() {
        return quantity;
    }

    public void setQuantity(MutableLiveData<String> quantity) {
        this.quantity = quantity;
    }

    public MutableLiveData<String> getProvider() {
        return provider;
    }

    public void setProvider(MutableLiveData<String> provider) {
        this.provider = provider;
    }

    public MutableLiveData<String> getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(MutableLiveData<String> purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public MutableLiveData<Boolean> getIsPurchase() {
        return isPurchase;
    }

    public void setIsPurchase(MutableLiveData<Boolean> isPurchase) {
        this.isPurchase = isPurchase;
    }
}
