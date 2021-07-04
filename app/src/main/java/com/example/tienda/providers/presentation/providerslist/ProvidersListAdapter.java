package com.example.tienda.providers.presentation.providerslist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tienda.databinding.LayoutProviderListItemBinding;
import com.example.tienda.framework.database.room.providers.entities.Provider;
import com.example.tienda.providers.utils.interfaces.OnProviderSelected;

import java.util.Objects;

public class ProvidersListAdapter extends ListAdapter<Provider, ProvidersListAdapter.ViewHolder> {

    private final OnProviderSelected onProviderSelected;

    public ProvidersListAdapter(OnProviderSelected onProviderSelected) {
        super(DIFF_CALLBACK);
        this.onProviderSelected = onProviderSelected;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
            LayoutProviderListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
            )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setProvider(getItem(position));
        holder.binding.container.setOnClickListener(v ->
            onProviderSelected.onProviderSelected(getItem(position))
        );
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final LayoutProviderListItemBinding binding;

        public ViewHolder(LayoutProviderListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static final DiffUtil.ItemCallback<Provider> DIFF_CALLBACK = new DiffUtil.ItemCallback<Provider>() {
        @Override
        public boolean areItemsTheSame(@NonNull Provider oldItem, @NonNull Provider newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Provider oldItem, @NonNull Provider newItem) {
            return Objects.equals(oldItem, newItem);
        }
    };
}
