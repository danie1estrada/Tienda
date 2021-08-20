package com.example.tienda.providers.presentation.providerslist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.tienda.R
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tienda.databinding.FragmentProvidersListBinding
import com.example.tienda.framework.database.room.providers.entities.Provider

class ProvidersListFragment : Fragment() {

    private val viewModel: ProvidersListViewModel by viewModels()
    private lateinit var binding: FragmentProvidersListBinding
    private lateinit var adapter: ProvidersListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProvidersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        setupBinding()
        setupOnClick()
        setupRecyclerView()
    }

    private fun setupBinding() {
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupOnClick() {
        binding.fab.setOnClickListener { findNavController().navigate(R.id.addProviderAction) }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ProvidersListAdapter { onProviderSelected(it) }
        }

        viewModel.providers.observe(viewLifecycleOwner, { providers ->
            adapter.submitList(providers)
        })
    }

    private fun onProviderSelected(provider: Provider) {
        Navigation.findNavController(requireView()).navigate(
            R.id.seeProviderDetailAction,
            bundleOf("id" to provider.id)
        )
    }
}