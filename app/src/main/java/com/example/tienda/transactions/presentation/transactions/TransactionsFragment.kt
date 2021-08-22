package com.example.tienda.transactions.presentation.transactions

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.example.tienda.R
import com.example.tienda.databinding.FragmentTransactionsBinding

class TransactionsFragment : Fragment() {
    
    private lateinit var binding: FragmentTransactionsBinding
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setup()
    }

    private fun setup() {
        setupTabLayout()
    }

    private fun setupTabLayout() {
        binding.viewPager2.adapter = TransactionFragmentStateAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            if (position == 0) {
                tab.setText(R.string.transaction_tab_title_sales)
                tab.setIcon(R.drawable.icon_trending_up)
            } else {
                tab.setText(R.string.transaction_tab_title_purchases)
                tab.setIcon(R.drawable.icon_purchase)
            }
        }.attach()
    }
}