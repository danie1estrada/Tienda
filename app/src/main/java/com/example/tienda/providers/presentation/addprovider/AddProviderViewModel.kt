package com.example.tienda.providers.presentation.addprovider

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.tienda.providers.data.ProviderRepository
import androidx.lifecycle.MutableLiveData
import android.widget.Toast
import com.example.tienda.R
import com.example.tienda.framework.database.room.providers.entities.Provider
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AddProviderViewModel @Inject constructor(
    application: Application,
    private val repository: ProviderRepository
) : AndroidViewModel(application) {

    val provider = MutableLiveData(Provider(0, "", "", ""))

    fun save() {
        try {
            repository.insert(provider.value!!)
            resetState()
            Toast.makeText(getApplication(), R.string.provider_info_saved, Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(getApplication(), R.string.message_error, Toast.LENGTH_LONG).show()
        }
    }

    private fun resetState() {
        provider.value = Provider(0, "", "", "")
    }

}