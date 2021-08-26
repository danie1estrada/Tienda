package com.example.tienda.providers.presentation.providerslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tienda.framework.database.room.providers.entities.Provider
import com.example.tienda.providers.domain.usecases.GetProvidersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProvidersListViewModel @Inject constructor(
    getProvidersListUseCase: GetProvidersListUseCase
) : ViewModel() {
    val providers: LiveData<List<Provider>> = getProvidersListUseCase()
}