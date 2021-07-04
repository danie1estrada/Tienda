package com.example.tienda.products.presentation.productdetail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.tienda.R;
import com.example.tienda.databinding.FragmentProductDetailBinding;

public class ProductDetailFragment extends Fragment {

    private FragmentProductDetailBinding binding;
    private ProductDetailViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
        setup();
    }

    private void init() {
        viewModel = new ViewModelProvider(requireActivity()).get(ProductDetailViewModel.class);
    }

    private void setup() {
        setupViewModel();
        setupOnClick();
        setupDropdown();
    }

    private void setupViewModel() {
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
    }

    private void setupDropdown() {
        viewModel.getProviders().observe(getViewLifecycleOwner(), providers ->
            binding.dropdownProvider.setAdapter(new ArrayAdapter<>(
                requireContext(),
                R.layout.list_item,
                providers
        )));
    }

    private void setupOnClick() {
        binding.btnDelete.setOnClickListener(this::showDialog);
    }

    private void showDialog(View view) {
        new AlertDialog.Builder(requireContext())
            .setTitle(R.string.provider_delete_dialog_title)
            .setNegativeButton(R.string.btn_cancel, null)
            .setPositiveButton(R.string.btn_delete, (dialog, which) -> {
                viewModel.delete();
                requireActivity().onBackPressed();
            }).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.resetState();
    }
}