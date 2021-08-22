package com.example.tienda.transactions.presentation.purchases

import android.app.AlertDialog
import android.content.Context
import com.example.tienda.framework.database.room.transactions.entities.Purchase
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.tienda.R
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.tienda.databinding.DialogPurchaseDetailsBinding
import com.example.tienda.databinding.LayoutPurchaseListItemBinding

class PurchasesAdapter : ListAdapter<Purchase, PurchasesAdapter.ViewHolder>(PurchaseDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutPurchaseListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun showDetailDialog(context: Context, purchase: Purchase) {
        val binding = DialogPurchaseDetailsBinding.inflate(LayoutInflater.from(context))
        binding.purchase = purchase
        AlertDialog.Builder(context)
            .setTitle(R.string.title_purchase_detail)
            .setView(binding.root)
            .setNegativeButton(R.string.btn_close, null)
            .show()
    }

    inner class ViewHolder(
        private val binding: LayoutPurchaseListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(purchase: Purchase) {
            binding.purchase = purchase
            binding.container.setOnClickListener { showDetailDialog(itemView.context, purchase) }
        }
    }

    private class PurchaseDiffUtil : DiffUtil.ItemCallback<Purchase>() {
        override fun areItemsTheSame(oldItem: Purchase, newItem: Purchase): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Purchase, newItem: Purchase): Boolean {
            return oldItem == newItem
        }
    }
}