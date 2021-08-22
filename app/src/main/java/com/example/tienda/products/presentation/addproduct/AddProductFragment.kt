package com.example.tienda.products.presentation.addproduct;

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
import com.example.tienda.databinding.FragmentAddProductBinding;

public class AddProductFragment extends Fragment {

    private FragmentAddProductBinding binding;
    private AddProductViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
        setup();
    }

    private void init() {
        viewModel = new ViewModelProvider(this).get(AddProductViewModel.class);
    }

    private void setup() {
        setupBinding();
        setupDropdown();
    }

    private void setupBinding() {
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
    }

    private void setupDropdown() {
        viewModel.getProviders().observe(getViewLifecycleOwner(), providers -> binding.dropdownProvider.setAdapter(new ArrayAdapter<>(
            requireContext(),
            R.layout.list_item,
            providers
        )));
    }
}