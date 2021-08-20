package com.example.tienda.providers.presentation.providerdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.tienda.providers.data.ProviderRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import android.widget.Toast
import com.example.tienda.R
import java.lang.Exception

class ProviderDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProviderRepository = ProviderRepository(application)
    private val userId = MutableLiveData(0)

    var provider = Transformations.switchMap(userId) {
        repository.getById(it)
    }

    fun setUserId(userId: Int) {
        this.userId.value = userId
    }

    fun save() {
        try {
            repository.update(provider.value)
            Toast.makeText(getApplication(), R.string.provider_info_updated, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(getApplication(), R.string.message_error, Toast.LENGTH_SHORT).show()
        }
    }

    fun delete() {
        try {
            repository.delete(provider.value)
            Toast.makeText(getApplication(), R.string.provider_info_deleted, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(getApplication(), R.string.message_error, Toast.LENGTH_SHORT).show()
        }
    }

}