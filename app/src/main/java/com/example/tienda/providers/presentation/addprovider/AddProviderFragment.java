package com.example.tienda.providers.presentation.addprovider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tienda.databinding.FragmentAddProviderBinding;

public class AddProviderFragment extends Fragment {

    private FragmentAddProviderBinding binding;
    private AddProviderViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddProviderBinding.inflate(inflater, container, false);
        init();
        setup();
        return binding.getRoot();
    }

    private void init() {
        viewModel = new ViewModelProvider(this).get(AddProviderViewModel.class);
    }

    private void setup() {
        setupBinding();
    }

    private void setupBinding() {
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
    }
}