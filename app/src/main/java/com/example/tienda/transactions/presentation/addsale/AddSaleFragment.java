package com.example.tienda.transactions.presentation.addsale;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.tienda.R;
import com.example.tienda.databinding.FragmentAddSaleBinding;
import com.example.tienda.framework.database.room.products.entities.Product;

public class AddSaleFragment extends Fragment {

    private FragmentAddSaleBinding binding;
    private AddSaleViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddSaleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
        setup();
    }

    private void init() {
        viewModel = new ViewModelProvider(this).get(AddSaleViewModel.class);
    }

    private void setup() {
        setupBinding();
        setupDropDown();
    }

    private void setupBinding() {
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
    }

    private void setupDropDown() {
        viewModel.getProducts().observe(getViewLifecycleOwner(), products -> {
            binding.dropdownProduct.setAdapter(new ArrayAdapter<>(
                requireContext(),
                R.layout.list_item,
                products
            ));
        });

        binding.dropdownProduct.setOnItemClickListener((parent, view, position, id) -> {
            viewModel.setProduct((Product) parent.getItemAtPosition(position));
        });
    }
}