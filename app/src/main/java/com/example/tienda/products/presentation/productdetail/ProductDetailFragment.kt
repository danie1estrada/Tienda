package com.example.tienda.products.presentation.productdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.tienda.R
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.tienda.databinding.FragmentProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {
    
    private lateinit var binding: FragmentProductDetailBinding
    private val viewModel: ProductDetailViewModel by activityViewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setup()
    }

    private fun setup() {
        setupViewModel()
        setupOnClick()
        setupDropdown()
    }

    private fun setupViewModel() {
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupDropdown() {
        viewModel.providers.observe(viewLifecycleOwner, {
            binding.dropdownProvider.setAdapter(
                ArrayAdapter(requireContext(), R.layout.list_item, it)
            )
        })
    }

    private fun setupOnClick() {
        binding.btnDelete.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.provider_delete_dialog_title)
            .setNegativeButton(R.string.btn_cancel, null)
            .setPositiveButton(R.string.btn_delete) { _, _ ->
                viewModel.delete()
                requireActivity().onBackPressed()
            }.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.resetState()
    }
}