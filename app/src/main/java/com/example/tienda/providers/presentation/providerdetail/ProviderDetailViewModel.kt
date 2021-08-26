package com.example.tienda.providers.presentation.providerdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import android.widget.Toast
import com.example.tienda.R
import com.example.tienda.providers.domain.usecases.DeleteProviderUseCase
import com.example.tienda.providers.domain.usecases.FindProviderByIdUseCase
import com.example.tienda.providers.domain.usecases.UpdateProviderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ProviderDetailViewModel @Inject constructor(
    private val findProviderByIdUseCase: FindProviderByIdUseCase,
    private val updateProviderUseCase: UpdateProviderUseCase,
    private val deleteProviderUseCase: DeleteProviderUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val userId = MutableLiveData(0)

    var provider = Transformations.switchMap(userId) {
        findProviderByIdUseCase(it)
    }

    fun setUserId(userId: Int) {
        this.userId.value = userId
    }

    fun save() {
        try {
            updateProviderUseCase(provider.value!!)
            Toast.makeText(getApplication(), R.string.provider_info_updated, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(getApplication(), R.string.message_error, Toast.LENGTH_SHORT).show()
        }
    }

    fun delete() {
        try {
            deleteProviderUseCase(provider.value!!)
            Toast.makeText(getApplication(), R.string.provider_info_deleted, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(getApplication(), R.string.message_error, Toast.LENGTH_SHORT).show()
        }
    }

}