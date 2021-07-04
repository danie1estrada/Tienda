package com.example.tienda.providers.presentation.providerdetail;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.tienda.R;
import com.example.tienda.framework.database.room.providers.entities.Provider;
import com.example.tienda.providers.data.ProviderRepository;

public class ProviderDetailViewModel extends AndroidViewModel {

    private ProviderRepository repository;

    private final MutableLiveData<Integer> userId = new MutableLiveData<>(0);

    private LiveData<Provider> provider = Transformations.switchMap(userId, input ->
        repository.getById(input)
    );

    public ProviderDetailViewModel(@NonNull Application application) {
        super(application);
        repository = new ProviderRepository(application);
    }

    public void setUserId(int userId) {
        this.userId.setValue(userId);
    }

    public LiveData<Provider> getProvider() {
        return provider;
    }

    public void setProvider(LiveData<Provider> provider) {
        this.provider = provider;
    }

    public void save() {
        try {
            repository.update(provider.getValue());
            Toast.makeText(getApplication(), R.string.provider_info_updated, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplication(), R.string.message_error, Toast.LENGTH_SHORT).show();
        }
    }

    public void delete() {
        try {
            repository.delete(provider.getValue());
            Toast.makeText(getApplication(), R.string.provider_info_deleted, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplication(), R.string.message_error, Toast.LENGTH_SHORT).show();
        }
    }
}
