package com.example.tienda.transactions.presentation.sales

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tienda.R
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tienda.databinding.FragmentSalesBinding

class SalesFragment : Fragment() {

    private val viewModel: SalesViewModel by viewModels()
    private lateinit var binding: FragmentSalesBinding
    private lateinit var adapter: SalesAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSalesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        setup()
    }

    private fun init() {
        adapter = SalesAdapter()
    }

    private fun setup() {
        setupFAB()
        setupBinding()
        setupRecyclerview()
    }

    private fun setupBinding() {
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupFAB() {
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.addSaleAction)
        }
    }

    private fun setupRecyclerview() {
        binding.recyclerView.apply {
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapter
        }

        viewModel.sales.observe(viewLifecycleOwner, { sales ->
            adapter.submitList(sales)
        })
    }
}