package com.example.tienda.products.presentation.productslist

import com.example.tienda.products.presentation.productdetail.ProductDetailViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tienda.R
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tienda.databinding.FragmentProductsListBinding
import com.example.tienda.framework.database.room.products.entities.Product

class ProductsListFragment : Fragment() {

    private val productDetailViewModel: ProductDetailViewModel by activityViewModels()
    private val viewModel: ProductsListViewModel by viewModels()

    private lateinit var binding: FragmentProductsListBinding
    private lateinit var adapter: ProductsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        setup()
    }

    private fun init() {
        adapter = ProductsListAdapter(::onProductSelected)
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
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.addProductAction)
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ProductsListFragment.adapter
        }
        viewModel.products.observe(viewLifecycleOwner, { adapter.submitList(it) })
    }

    private fun onProductSelected(product: Product) {
        productDetailViewModel.setProduct(product)
        findNavController().navigate(R.id.seeProductDetailAction)
    }
}