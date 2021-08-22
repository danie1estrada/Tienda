package com.example.tienda.transactions.presentation.purchases;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tienda.databinding.FragmentPurchasesBinding;

public class PurchasesFragment extends Fragment {

    private FragmentPurchasesBinding binding;
    private PurchasesViewModel viewModel;
    private PurchasesAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPurchasesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
        setup();
    }

    private void init() {
        viewModel = new ViewModelProvider(this).get(PurchasesViewModel.class);
        adapter = new PurchasesAdapter();
    }

    private void setup() {
        setupBinding();
        setupRecyclerview();
    }

    private void setupBinding() {
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
    }

    private void setupRecyclerview() {
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(adapter);

        viewModel.getPurchases().observe(getViewLifecycleOwner(), purchases ->
            adapter.submitList(purchases)
        );
    }
}