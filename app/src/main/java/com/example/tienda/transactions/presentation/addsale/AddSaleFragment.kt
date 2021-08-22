package com.example.tienda.transactions.presentation.addsale

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import com.example.tienda.framework.database.room.products.entities.Product
import android.widget.ArrayAdapter
import com.example.tienda.R
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tienda.databinding.FragmentAddSaleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddSaleFragment : Fragment() {

    private val viewModel: AddSaleViewModel by viewModels()
    private lateinit var binding: FragmentAddSaleBinding
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddSaleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setup()
    }

    private fun setup() {
        setupBinding()
        setupDropDown()
    }

    private fun setupBinding() {
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupDropDown() {
        viewModel.products.observe(viewLifecycleOwner, {
            binding.dropdownProduct.setAdapter(
                ArrayAdapter(requireContext(), R.layout.list_item, it)
            )
        })
        binding.dropdownProduct.onItemClickListener = OnItemClickListener { parent, _, position, _ ->
            viewModel.setProduct(parent.getItemAtPosition(position) as Product)
        }
    }
}