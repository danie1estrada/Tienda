package com.example.tienda.providers.presentation.providerslist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tienda.framework.database.room.providers.entities.Provider;
import com.example.tienda.providers.data.ProviderRepository;

import java.util.List;

public class ProvidersListViewModel extends AndroidViewModel {

    private final LiveData<List<Provider>> providers;

    public ProvidersListViewModel(@NonNull Application application) {
        super(application);
        providers = new ProviderRepository(application).getAll();
    }

    public LiveData<List<Provider>> getProviders() {
        return providers;
    }
}
