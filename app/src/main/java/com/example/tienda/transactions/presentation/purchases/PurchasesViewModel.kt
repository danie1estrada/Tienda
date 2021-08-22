package com.example.tienda.transactions.presentation.purchases

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tienda.framework.database.room.transactions.entities.Purchase
import com.example.tienda.transactions.data.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PurchasesViewModel @Inject constructor(
    repository: TransactionRepository
) : ViewModel() {
    val purchases: LiveData<List<Purchase>> = repository.purchases
}