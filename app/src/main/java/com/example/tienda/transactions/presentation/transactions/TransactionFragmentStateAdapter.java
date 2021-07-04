package com.example.tienda.transactions.presentation.transactions;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tienda.transactions.presentation.purchases.PurchasesFragment;
import com.example.tienda.transactions.presentation.sales.SalesFragment;

import java.util.ArrayList;

public class TransactionFragmentStateAdapter extends FragmentStateAdapter {

    private final ArrayList<Fragment> fragments;

    public TransactionFragmentStateAdapter(@NonNull Fragment fragment) {
        super(fragment);
        fragments = new ArrayList<>();
        fragments.add(new SalesFragment());
        fragments.add(new PurchasesFragment());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
