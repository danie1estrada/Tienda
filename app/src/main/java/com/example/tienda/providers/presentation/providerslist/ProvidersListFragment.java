package com.example.tienda.providers.presentation.providerslist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tienda.R;
import com.example.tienda.databinding.FragmentProvidersListBinding;
import com.example.tienda.framework.database.room.providers.entities.Provider;
import com.example.tienda.providers.utils.interfaces.OnProviderSelected;

public class ProvidersListFragment extends Fragment implements OnProviderSelected {

    private FragmentProvidersListBinding binding;
    private ProvidersListViewModel viewModel;
    private ProvidersListAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProvidersListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        setup();
    }

    private void init() {
        viewModel = new ViewModelProvider(this).get(ProvidersListViewModel.class);
        adapter = new ProvidersListAdapter(this);
    }

    private void setup() {
        setupBinding();
        setupOnClick();
        setupRecyclerView();
    }

    private void setupBinding() {
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
    }

    private void setupOnClick() {
        binding.fab.setOnClickListener(v ->Navigation.findNavController(v).navigate(R.id.addProviderAction));
    }

    private void setupRecyclerView() {
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(adapter);

        viewModel.getProviders().observe(getViewLifecycleOwner(), providers ->
            adapter.submitList(providers)
        );
    }

    @Override
    public void onProviderSelected(Provider provider) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", provider.getId());
        Navigation.findNavController(requireView()).navigate(R.id.seeProviderDetailAction, bundle);
    }
}