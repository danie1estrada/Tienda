package com.example.tienda.transactions.presentation.sales

import android.app.AlertDialog
import android.content.Context
import com.example.tienda.framework.database.room.transactions.entities.Sale
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.tienda.R
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.tienda.databinding.DialogSaleDetailsBinding
import com.example.tienda.databinding.LayoutSaleListItemBinding

class SalesAdapter : ListAdapter<Sale, SalesAdapter.ViewHolder>(SaleDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutSaleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun showDetailDialog(context: Context, sale: Sale) {
        val binding = DialogSaleDetailsBinding.inflate(LayoutInflater.from(context))
        binding.sale = sale
        AlertDialog.Builder(context)
            .setTitle(R.string.title_sale_detail)
            .setView(binding.root)
            .setNegativeButton(R.string.btn_close, null)
            .show()
    }

    inner class ViewHolder(
        private val binding: LayoutSaleListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sale: Sale) {
            binding.sale = sale
            binding.container.setOnClickListener { showDetailDialog(itemView.context, sale) }
        }
    }

    class SaleDiffCallback: DiffUtil.ItemCallback<Sale>() {
        override fun areItemsTheSame(oldItem: Sale, newItem: Sale): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Sale, newItem: Sale): Boolean {
            return oldItem == newItem
        }
    }
}