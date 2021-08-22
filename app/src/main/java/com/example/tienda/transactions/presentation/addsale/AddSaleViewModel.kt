package com.example.tienda.transactions.presentation.addsale

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.tienda.products.data.ProductRepository
import androidx.lifecycle.LiveData
import com.example.tienda.framework.database.room.products.entities.Product
import com.example.tienda.transactions.data.TransactionRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.MediatorLiveData
import com.example.tienda.framework.database.room.transactions.entities.Sale
import android.widget.Toast
import com.example.tienda.R
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class AddSaleViewModel(application: Application) : AndroidViewModel(application) {

    private val productRepository: ProductRepository = ProductRepository(application)
    private val repository: TransactionRepository = TransactionRepository(application)
    val products: LiveData<List<Product>> = productRepository.all

    val product = MutableLiveData<Product?>()
    val price = Transformations.map(product) { input: Product? ->
        if (input == null) return@map ""
        String.format(Locale.getDefault(), "%.2f", input.price)
    }
    var quantity = MutableLiveData("")
    val total = MediatorLiveData<Float>()

    fun save() {
        try {
            val productSold = product.value
            repository.insertSale(
                Sale(
                    0,
                    productSold!!.name,
                    productSold.price, quantity.value!!.toInt(),
                    SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault()).format(Date())
                )
            )
            productSold.subtract(quantity.value!!.toInt())
            productRepository.update(productSold)
            resetState()
            Toast.makeText(getApplication(), R.string.sale_info_saved, Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(getApplication(), R.string.message_error, Toast.LENGTH_LONG).show()
        }
    }

    private fun resetState() {
        product.value = null
        quantity.value = ""
    }

    fun setProduct(product: Product) {
        this.product.value = product
    }

    init {
        total.addSource(price) { input: String ->
            if (input.isNotEmpty() && quantity.value!!.isNotEmpty()
            ) total.setValue(input.toFloat() * quantity.value!!.toFloat()) else total.setValue(0f)
        }
        total.addSource(quantity) { input: String ->
            if (input.isNotEmpty() && quantity.value!!.isNotEmpty()
            ) total.setValue(input.toFloat() * price.value!!.toFloat()) else total.setValue(0f)
        }
    }
}