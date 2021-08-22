package com.example.tienda.products.presentation.productdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.tienda.transactions.data.TransactionRepository
import com.example.tienda.products.data.ProductRepository
import androidx.lifecycle.LiveData
import com.example.tienda.framework.database.room.products.entities.Product
import androidx.lifecycle.MutableLiveData
import android.widget.Toast
import com.example.tienda.R
import com.example.tienda.framework.database.room.transactions.entities.Purchase
import androidx.lifecycle.Transformations
import com.example.tienda.providers.data.ProviderRepository
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class ProductDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val transactionRepository: TransactionRepository = TransactionRepository(application)
    private val repository: ProductRepository = ProductRepository(application)
    private var product = Product(0, "", 0f, 0, "")

    val providers: LiveData<List<String>> = Transformations.map(ProviderRepository(application).all) {
        it.map { provider -> provider.company }
    }

    val name = MutableLiveData("")
    val price = MutableLiveData("")
    val quantity = MutableLiveData("")
    val provider = MutableLiveData("")
    val purchasePrice = MutableLiveData("")
    val isPurchase = MutableLiveData(false)

    fun save() {
        if (isPurchase.value!!) {
            resetState(updateAsPurchase(updateProduct()))
            Toast.makeText(getApplication(), R.string.purchase_info_saved, Toast.LENGTH_LONG).show()
        } else {
            resetState(updateProduct())
            Toast.makeText(getApplication(), R.string.product_info_updated, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateProduct(): Product {
        val updatedProduct = Product(
            product.id,
            name.value!!, price.value!!.replace(",", "").toFloat(), quantity.value!!.toInt(),
            provider.value!!
        )
        if (isPurchase.value!!) {
            updatedProduct.quantity = product.quantity
            updatedProduct.add(quantity.value!!.toInt())
        }
        try {
            repository.insert(updatedProduct)
        } catch (e: Exception) {
            Toast.makeText(getApplication(), R.string.message_error, Toast.LENGTH_LONG).show()
        }
        return updatedProduct
    }

    private fun updateAsPurchase(product: Product): Product {
        try {
            transactionRepository.insertPurchase(
                Purchase(
                    0,
                    SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault()).format(Date()),
                    product.name, purchasePrice.value!!.toFloat(),
                    product.provider, quantity.value!!.toInt()
                )
            )
        } catch (e: Exception) {
            Toast.makeText(getApplication(), R.string.message_error, Toast.LENGTH_LONG).show()
        }
        return product
    }

    fun delete() {
        try {
            repository.delete(product)
            Toast.makeText(getApplication(), R.string.product_info_deleted, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(getApplication(), R.string.message_error, Toast.LENGTH_SHORT).show()
        }
    }

    fun resetState() {
        setProduct(Product(0, "", 0f, 0, ""))
        isPurchase.value = false
        purchasePrice.value = ""
    }

    private fun resetState(product: Product) {
        setProduct(product)
        isPurchase.value = false
        purchasePrice.value = ""
    }

    fun setProduct(product: Product) {
        this.product = product
        name.value = product.name
        price.value = String.format(Locale.getDefault(), "%,.2f", product.price)
        quantity.value = product.quantity.toString()
        provider.value = product.provider
    }

}