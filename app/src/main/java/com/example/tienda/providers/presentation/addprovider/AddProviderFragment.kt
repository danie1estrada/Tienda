package com.example.tienda.providers.presentation.addprovider

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tienda.databinding.FragmentAddProviderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProviderFragment : Fragment() {

    private val viewModel: AddProviderViewModel by viewModels()
    private lateinit var binding: FragmentAddProviderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddProviderBinding.inflate(inflater, container, false)
        setup()
        return binding.root
    }

    private fun setup() {
        setupBinding()
    }

    private fun setupBinding() {
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }
}