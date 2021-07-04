package com.example.tienda.providers.presentation.providerdetail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tienda.R;
import com.example.tienda.databinding.FragmentProviderDetailBinding;
import com.google.android.material.snackbar.Snackbar;

public class ProviderDetailFragment extends Fragment {

    private FragmentProviderDetailBinding binding;
    private ProviderDetailViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProviderDetailBinding.inflate(inflater, container, false);
        init();
        setup();
        return binding.getRoot();
    }

    private void init() {
        viewModel = new ViewModelProvider(this).get(ProviderDetailViewModel.class);

        int id = getArguments().getInt("id");
        viewModel.setUserId(id);
    }

    private void setup() {
        setupViewModel();
        setupOnClick();
    }

    private void setupViewModel() {
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        viewModel.getProvider().observe(getViewLifecycleOwner(), provider -> {
            if (provider == null)
                Toast.makeText(requireContext(), "Provider does not exist", Toast.LENGTH_SHORT).show();
        });
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
}