package com.example.tienda.products.presentation.addproduct

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.tienda.transactions.data.TransactionRepository
import com.example.tienda.products.data.ProductRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.widget.Toast
import com.example.tienda.R
import com.example.tienda.framework.database.room.products.entities.Product
import com.example.tienda.framework.database.room.transactions.entities.Purchase
import androidx.lifecycle.Transformations
import com.example.tienda.providers.data.ProviderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    application: Application,
    providerRepository: ProviderRepository,
    private val repository: ProductRepository,
    private val transactionRepository: TransactionRepository
) : AndroidViewModel(application) {

    val providers: LiveData<List<String>> = Transformations.map(providerRepository.all) {
        it.map { provider -> provider.company }
    }

    var name = MutableLiveData("")
    var price = MutableLiveData("")
    var quantity = MutableLiveData("")
    var provider = MutableLiveData("")
    var purchasePrice = MutableLiveData("")
    var isPurchase = MutableLiveData(false)

    fun save() {
        if (isPurchase.value!!) {
            saveAsPurchase(saveProduct())
            Toast.makeText(getApplication(), R.string.purchase_info_saved, Toast.LENGTH_LONG).show()
        } else {
            saveProduct()
            Toast.makeText(getApplication(), R.string.product_info_saved, Toast.LENGTH_LONG).show()
        }
        resetState()
    }

    private fun saveProduct(): Product {
        val product = Product(
            0,
            name.value!!, price.value!!.toFloat(), quantity.value!!.toInt(),
            provider.value!!
        )

        try {
            repository.insert(product)
        } catch (e: Exception) {
            Toast.makeText(getApplication(), R.string.message_error, Toast.LENGTH_LONG).show()
        }
        return product
    }

    private fun saveAsPurchase(product: Product) {
        try {
            transactionRepository.insertPurchase(
                Purchase(
                    0,
                    SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault()).format(Date()),
                    product.name, purchasePrice.value!!.toFloat(),
                    product.provider,
                    product.quantity
                )
            )
        } catch (e: Exception) {
            Toast.makeText(getApplication(), R.string.message_error, Toast.LENGTH_LONG).show()
        }
    }

    private fun resetState() {
        name.value = ""
        price.value = ""
        quantity.value = ""
        provider.value = ""
        purchasePrice.value = ""
        isPurchase.value = false
    }

}