package com.patricioglenn.cafeto_admin.ui.product_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.patricioglenn.cafeto_admin.R
import com.patricioglenn.cafeto_admin.databinding.FragmentProductListBinding
import com.patricioglenn.cafeto_admin.db.CafetoDatabase

class ProductListFragment : Fragment() {

    lateinit var binding: FragmentProductListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false)
        val arguments = ProductListFragmentArgs.fromBundle(requireArguments())
        val application = requireNotNull(this.activity).application
        val dataSource = CafetoDatabase.getInstance(application).cafetoDao
        val viewModelFactory = ProductListViewModelFactory(dataSource, arguments.categoryId)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ProductListViewModel::class.java)
        binding.productListViewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = ProductAdapter(ProductListener { id ->
            viewModel.onProductClick(id)
        })
        binding.recyclerView.adapter = adapter

        viewModel.products.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        val dividerItemDecoration = DividerItemDecoration(binding.recyclerView.getContext(), 1);
        binding.recyclerView.addItemDecoration(dividerItemDecoration);

        val navController = findNavController()
        binding.fabNewProduct.setOnClickListener {
            navController.navigate(R.id.action_productListFragment_to_productFragment)
        }

        binding.executePendingBindings()
        return binding.root
    }

}