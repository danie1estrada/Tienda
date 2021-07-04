package com.example.tienda.transactions.presentation.transactions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tienda.R;
import com.example.tienda.databinding.FragmentTransactionsBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class TransactionsFragment extends Fragment {

    private FragmentTransactionsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTransactionsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setup();
    }

    private void setup() {
        setupTabLayout();
    }

    private void setupTabLayout() {
        binding.viewPager2.setAdapter(new TransactionFragmentStateAdapter(this));
        new TabLayoutMediator(binding.tabLayout, binding.viewPager2, (tab, position) -> {
            if (position == 0) {
                tab.setText(R.string.transaction_tab_title_sales);
                tab.setIcon(R.drawable.icon_trending_up);
            } else {
                tab.setText(R.string.transaction_tab_title_purchases);
                tab.setIcon(R.drawable.icon_purchase);
            }
        }).attach();
    }

}