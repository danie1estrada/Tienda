package com.example.tienda.providers.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.tienda.framework.database.room.AppDatabase;
import com.example.tienda.framework.database.room.providers.daos.ProviderDao;
import com.example.tienda.framework.database.room.providers.entities.Provider;

import java.util.List;

public class ProviderRepository {
    private final ProviderDao dao;

    public ProviderRepository(Application application) {
        dao = AppDatabase.getInstance(application).providerDao();
    }

    public LiveData<List<Provider>> getAll() {
        return dao.getAll();
    }

    public LiveData<Provider> getById(int id) {
        return dao.getById(id);
    }

    public void insert(Provider provider) {
        AppDatabase.databaseWriteExecutor.execute(() ->
            dao.insert(provider)
        );
    }

    public void update(Provider provider) {
        AppDatabase.databaseWriteExecutor.execute(() ->
            dao.update(provider)
        );
    }

    public void delete(Provider provider) {
        AppDatabase.databaseWriteExecutor.execute(() ->
            dao.delete(provider)
        );
    }
}
