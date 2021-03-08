package com.patricioglenn.cafeto_admin.ui.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.patricioglenn.cafeto_admin.db.CafetoDao


class ProductListViewModelFactory (private val database: CafetoDao, private val categoryId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductListViewModel::class.java)) {
            return ProductListViewModel(database, categoryId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}