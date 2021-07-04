package com.example.tienda.transactions.presentation.purchases;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tienda.R;
import com.example.tienda.databinding.DialogPurchaseDetailsBinding;
import com.example.tienda.databinding.LayoutPurchaseListItemBinding;
import com.example.tienda.framework.database.room.transactions.entities.Purchase;

import java.util.Objects;

public class PurchasesAdapter extends ListAdapter<Purchase, PurchasesAdapter.ViewHolder> {

    public PurchasesAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
            LayoutPurchaseListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setPurchase(getItem(position));
        holder.binding.container.setOnClickListener(v ->
            showDetailDialog(holder.itemView.getContext(), getItem(position))
        );
    }

    private void showDetailDialog(Context context, Purchase purchase) {
        DialogPurchaseDetailsBinding binding = DialogPurchaseDetailsBinding.inflate(LayoutInflater.from(context));
        binding.setPurchase(purchase);

        new AlertDialog.Builder(context)
            .setTitle(R.string.title_purchase_detail)
            .setView(binding.getRoot())
            .setNegativeButton(R.string.btn_close, null)
            .show();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final LayoutPurchaseListItemBinding binding;

        public ViewHolder(LayoutPurchaseListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static final DiffUtil.ItemCallback<Purchase> DIFF_CALLBACK = new DiffUtil.ItemCallback<Purchase>() {
        @Override
        public boolean areItemsTheSame(@NonNull Purchase oldItem, @NonNull Purchase newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Purchase oldItem, @NonNull Purchase newItem) {
            return Objects.equals(oldItem, newItem);
        }
    };
}
