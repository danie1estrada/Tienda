package com.example.tienda.products.presentation.productslist;

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
import com.example.tienda.databinding.FragmentProductsListBinding;
import com.example.tienda.framework.database.room.products.entities.Product;
import com.example.tienda.products.presentation.productdetail.ProductDetailViewModel;
import com.example.tienda.products.utils.interfaces.OnProductSelected;

public class ProductsListFragment extends Fragment implements OnProductSelected {

    private ProductDetailViewModel productDetailViewModel;
    private FragmentProductsListBinding binding;
    private ProductsListViewModel viewModel;
    private ProductsListAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductsListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
        setup();
    }

    private void init() {
        productDetailViewModel = new ViewModelProvider(requireActivity()).get(ProductDetailViewModel.class);
        viewModel = new ViewModelProvider(this).get(ProductsListViewModel.class);
        adapter = new ProductsListAdapter(this);
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
        binding.fab.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.addProductAction));
    }

    private void setupRecyclerView() {
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(adapter);

        viewModel.getProducts().observe(getViewLifecycleOwner(), products ->
            adapter.submitList(products)
        );
    }

    @Override
    public void onProductSelected(Product product) {
        productDetailViewModel.setProduct(product);
        Navigation.findNavController(requireView()).navigate(R.id.seeProductDetailAction);
    }
}