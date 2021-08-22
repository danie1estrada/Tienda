package com.example.tienda.transactions.presentation.transactions

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tienda.transactions.presentation.sales.SalesFragment
import com.example.tienda.transactions.presentation.purchases.PurchasesFragment

class TransactionFragmentStateAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragments = listOf(SalesFragment(), PurchasesFragment())

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

}