package com.example.tienda.providers.presentation.addprovider;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.tienda.R;
import com.example.tienda.framework.database.room.providers.entities.Provider;
import com.example.tienda.providers.data.ProviderRepository;

public class AddProviderViewModel extends AndroidViewModel {

    private final ProviderRepository repository;
    private MutableLiveData<Provider> provider = new MutableLiveData<>(new Provider(0, "", "", ""));

    public AddProviderViewModel(@NonNull Application application) {
        super(application);
        repository = new ProviderRepository(application);
    }

    public void save() {
        try {
            repository.insert(provider.getValue());
            resetState();
            Toast.makeText(getApplication(), R.string.provider_info_saved, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplication(), R.string.message_error, Toast.LENGTH_LONG).show();
        }
    }

    private void resetState() {
        provider.setValue(new Provider(0, "", "", ""));
    }

    public MutableLiveData<Provider> getProvider() {
        return provider;
    }

    public void setProvider(MutableLiveData<Provider> provider) {
        this.provider = provider;
    }
}
