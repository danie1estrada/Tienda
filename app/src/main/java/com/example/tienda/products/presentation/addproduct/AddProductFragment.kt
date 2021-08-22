package com.example.tienda.products.presentation.addproduct

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tienda.R
import com.example.tienda.databinding.FragmentAddProductBinding

class AddProductFragment : Fragment() {
    
    private val viewModel: AddProductViewModel by viewModels()
    private lateinit var binding: FragmentAddProductBinding
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setup()
    }

    private fun setup() {
        setupBinding()
        setupDropdown()
    }

    private fun setupBinding() {
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupDropdown() {
        viewModel.providers.observe(viewLifecycleOwner, { providers: List<String> ->
            binding.dropdownProvider.setAdapter(
                ArrayAdapter(requireContext(), R.layout.list_item, providers)
            )
        })
    }
}