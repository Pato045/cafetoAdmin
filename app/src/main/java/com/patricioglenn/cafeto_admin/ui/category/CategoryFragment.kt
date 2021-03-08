package com.patricioglenn.cafeto_admin.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.patricioglenn.cafeto_admin.R
import com.patricioglenn.cafeto_admin.databinding.FragmentCategoryBinding
import com.patricioglenn.cafeto_admin.db.CafetoDatabase

class CategoryFragment : Fragment() {

    lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = CafetoDatabase.getInstance(application).cafetoDao
        val viewModelFactory = CategoryViewModelFactory(dataSource)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(CategoryViewModel::class.java)
        binding.categoryViewModel = viewModel

        binding.buttonSave.setOnClickListener {
            viewModel.saveNewCategory(binding.editTextNewCategory.text.toString())
        }
        return binding.root
    }
}