package com.example.tienda.transactions.presentation.sales

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.tienda.framework.database.room.transactions.entities.Sale
import com.example.tienda.transactions.data.TransactionRepository

class SalesViewModel(application: Application) : AndroidViewModel(application) {
    val sales: LiveData<List<Sale>> = TransactionRepository(application).sales

}