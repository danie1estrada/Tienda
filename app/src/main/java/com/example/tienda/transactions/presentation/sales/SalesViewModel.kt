package com.example.tienda.transactions.presentation.sales

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tienda.framework.database.room.transactions.entities.Sale
import com.example.tienda.transactions.data.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SalesViewModel @Inject constructor(
    repository: TransactionRepository
) : ViewModel() {
    val sales: LiveData<List<Sale>> = repository.sales
}