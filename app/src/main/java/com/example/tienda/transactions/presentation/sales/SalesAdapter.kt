package com.example.tienda.transactions.presentation.sales;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tienda.R;
import com.example.tienda.databinding.DialogSaleDetailsBinding;
import com.example.tienda.databinding.LayoutSaleListItemBinding;
import com.example.tienda.framework.database.room.transactions.entities.Sale;

import java.util.Objects;

public class SalesAdapter extends ListAdapter<Sale, SalesAdapter.ViewHolder> {

    public SalesAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
            LayoutSaleListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setSale(getItem(position));
        holder.binding.container.setOnClickListener(v ->
            showDetailDialog(holder.itemView.getContext(), getItem(position))
        );
    }

    private void showDetailDialog(Context context, Sale sale) {
        DialogSaleDetailsBinding binding = DialogSaleDetailsBinding.inflate(LayoutInflater.from(context));
        binding.setSale(sale);

        new AlertDialog.Builder(context)
            .setTitle(R.string.title_sale_detail)
            .setView(binding.getRoot())
            .setNegativeButton(R.string.btn_close, null)
            .show();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final LayoutSaleListItemBinding binding;

        public ViewHolder(LayoutSaleListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static final DiffUtil.ItemCallback<Sale> DIFF_CALLBACK = new DiffUtil.ItemCallback<Sale>() {
        @Override
        public boolean areItemsTheSame(@NonNull Sale oldItem, @NonNull Sale newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Sale oldItem, @NonNull Sale newItem) {
            return Objects.equals(oldItem, newItem);
        }
    };
}
