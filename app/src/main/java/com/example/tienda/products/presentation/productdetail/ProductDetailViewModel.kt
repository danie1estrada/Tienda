package com.example.tienda.products.presentation.productdetail;

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

public class ProductDetailViewModel extends AndroidViewModel {

    private final TransactionRepository transactionRepository;
    private final ProductRepository repository;
    private final LiveData<List<String>> providers;

    private Product product = new Product(0, "", 0f, 0, "");

    private MutableLiveData<String> name = new MutableLiveData<>("");
    private MutableLiveData<String> price = new MutableLiveData<>("");
    private MutableLiveData<String> quantity = new MutableLiveData<>("");
    private MutableLiveData<String> provider = new MutableLiveData<>("");

    private MutableLiveData<String> purchasePrice = new MutableLiveData<>("");
    private MutableLiveData<Boolean> isPurchase = new MutableLiveData<>(false);

    public ProductDetailViewModel(@NonNull Application application) {
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
            resetState(updateAsPurchase(updateProduct()));
            Toast.makeText(getApplication(), R.string.purchase_info_saved, Toast.LENGTH_LONG).show();
        } else {
            resetState(updateProduct());
            Toast.makeText(getApplication(), R.string.product_info_updated, Toast.LENGTH_SHORT).show();
        }
    }

    private Product updateProduct() {
        Product updatedProduct = new Product(
            product.getId(),
            name.getValue(),
            Float.parseFloat(price.getValue().replace(",", "")),
            Integer.parseInt(quantity.getValue()),
            provider.getValue()
        );

        if (isPurchase.getValue()) {
            updatedProduct.setQuantity(product.getQuantity());
            updatedProduct.add(Integer.parseInt(quantity.getValue()));
        }

        try {
            repository.insert(updatedProduct);
        } catch (Exception e) {
            Toast.makeText(getApplication(), R.string.message_error, Toast.LENGTH_LONG).show();
        }

        return updatedProduct;
    }

    private Product updateAsPurchase(Product product) {
        try {
            transactionRepository.insertPurchase(new Purchase(
                0,
                new SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault()).format(new Date()),
                product.getName(),
                Float.parseFloat(purchasePrice.getValue()),
                product.getProvider(),
                Integer.parseInt(quantity.getValue())
            ));
        } catch (Exception e) {
            Toast.makeText(getApplication(), R.string.message_error, Toast.LENGTH_LONG).show();
        }

        return product;
    }

    public void delete() {
        try {
            repository.delete(product);
            Toast.makeText(getApplication(), R.string.product_info_deleted, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplication(), R.string.message_error, Toast.LENGTH_SHORT).show();
        }
    }

    public void resetState() {
        setProduct(new Product(0, "", 0f, 0, ""));
        isPurchase.setValue(false);
        purchasePrice.setValue("");
    }

    private void resetState(Product product) {
        setProduct(product);
        isPurchase.setValue(false);
        purchasePrice.setValue("");
    }

    public void setProduct(Product product) {
        this.product = product;
        name.setValue(product.getName());
        price.setValue(String.format(Locale.getDefault(), "%,.2f", product.getPrice()));
        quantity.setValue(String.valueOf(product.getQuantity()));
        provider.setValue(product.getProvider());
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
