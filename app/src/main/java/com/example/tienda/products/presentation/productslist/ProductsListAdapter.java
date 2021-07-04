package com.example.tienda.products.presentation.productslist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tienda.databinding.LayoutProductListItemBinding;
import com.example.tienda.framework.database.room.products.entities.Product;
import com.example.tienda.products.utils.interfaces.OnProductSelected;

import java.util.Objects;

public class ProductsListAdapter extends ListAdapter<Product, ProductsListAdapter.ViewHolder> {

    private final OnProductSelected onProductSelected;

    public ProductsListAdapter(OnProductSelected onProductSelected) {
        super(DIFF_CALLBACK);
        this.onProductSelected = onProductSelected;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
            LayoutProductListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setProduct(getItem(position));
        holder.binding.container.setOnClickListener(v ->
            onProductSelected.onProductSelected(getItem(position))
        );
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final LayoutProductListItemBinding binding;

        public ViewHolder(LayoutProductListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static final DiffUtil.ItemCallback<Product> DIFF_CALLBACK = new DiffUtil.ItemCallback<Product>() {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return Objects.equals(oldItem, newItem);
        }
    };
}
