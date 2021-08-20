package com.example.tienda.providers.presentation.providerdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.widget.Toast
import com.example.tienda.R
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tienda.databinding.FragmentProviderDetailBinding

class ProviderDetailFragment : Fragment() {

    private val viewModel: ProviderDetailViewModel by viewModels()
    private lateinit var binding: FragmentProviderDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProviderDetailBinding.inflate(inflater, container, false)
        init()
        setup()
        return binding.root
    }

    private fun init() {
        val id = requireArguments().getInt("id")
        viewModel.setUserId(id)
    }

    private fun setup() {
        setupBinding()
        setupOnClick()
    }

    private fun setupBinding() {
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.provider.observe(viewLifecycleOwner, { provider ->
            if (provider == null) Toast.makeText(
                requireContext(),
                "Provider does not exist",
                Toast.LENGTH_SHORT
            ).show()
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
}