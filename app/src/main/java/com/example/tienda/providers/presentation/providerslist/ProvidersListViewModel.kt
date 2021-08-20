package com.example.tienda.providers.presentation.providerslist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.tienda.framework.database.room.providers.entities.Provider
import com.example.tienda.providers.data.ProviderRepository

class ProvidersListViewModel(application: Application) : AndroidViewModel(application) {
    val providers: LiveData<List<Provider>> = ProviderRepository(application).all
}