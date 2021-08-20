package com.example.tienda.providers.presentation.providerslist

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.tienda.databinding.LayoutProviderListItemBinding
import com.example.tienda.framework.database.room.providers.entities.Provider

class ProvidersListAdapter(
    private val listener: (Provider) -> Unit
) : ListAdapter<Provider, ProvidersListAdapter.ViewHolder>(ProviderDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutProviderListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        val binding: LayoutProviderListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(provider: Provider) {
            binding.provider = provider
            binding.container.setOnClickListener { listener(provider) }
        }
    }

    internal class ProviderDiffCallback : DiffUtil.ItemCallback<Provider>() {
        override fun areItemsTheSame(oldItem: Provider, newItem: Provider) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Provider, newItem: Provider) = oldItem == newItem
    }
}