package com.example.tienda.transactions.presentation.purchases

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.tienda.framework.database.room.transactions.entities.Purchase
import com.example.tienda.transactions.data.TransactionRepository

class PurchasesViewModel(application: Application) : AndroidViewModel(application) {
    val purchases: LiveData<List<Purchase>> = TransactionRepository(application).purchases
}