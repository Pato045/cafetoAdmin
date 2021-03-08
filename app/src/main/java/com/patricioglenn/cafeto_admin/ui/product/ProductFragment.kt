package com.patricioglenn.cafeto_admin.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.patricioglenn.cafeto_admin.R
import com.patricioglenn.cafeto_admin.databinding.FragmentProductBinding
import com.patricioglenn.cafeto_admin.db.CafetoDatabase

class ProductFragment : Fragment() {

    lateinit var binding: FragmentProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = CafetoDatabase.getInstance(application).cafetoDao
        val viewModelFactory = ProductViewModelFactory(dataSource)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ProductViewModel::class.java)
        binding.productViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.categories.observe(viewLifecycleOwner, Observer {
            binding.auto.setAdapter(ArrayAdapter(requireContext(),R.layout.list_item, viewModel.getList()))
        })

        binding.auto.setOnItemClickListener { parent, view, position, id ->
            viewModel.setProductCategory(binding.auto.text.toString())
        }

        binding.buttonSaveNewProduct.setOnClickListener {
            viewModel.saveProduct()
        }

        val navController = findNavController()
        binding.buttonNewCategory.setOnClickListener {
            navController.navigate(R.id.action_productFragment_to_categoryFragment)
        }
        return binding.root
    }

}