package com.example.tienda.transactions.presentation.purchases

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tienda.databinding.FragmentPurchasesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PurchasesFragment : Fragment() {

    private val viewModel: PurchasesViewModel by viewModels()
    private lateinit var binding: FragmentPurchasesBinding
    private lateinit var adapter: PurchasesAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPurchasesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        setup()
    }

    private fun init() {
        adapter = PurchasesAdapter()
    }

    private fun setup() {
        setupBinding()
        setupRecyclerview()
    }

    private fun setupBinding() {
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupRecyclerview() {
        binding.recyclerView.apply {
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapter
        }

        viewModel.purchases.observe(viewLifecycleOwner, { purchases ->
            adapter.submitList(purchases)
        })
    }
}