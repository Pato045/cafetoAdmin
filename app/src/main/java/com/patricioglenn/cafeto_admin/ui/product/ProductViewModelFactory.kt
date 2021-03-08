package com.patricioglenn.cafeto_admin.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.patricioglenn.cafeto_admin.db.CafetoDao

class ProductViewModelFactory (private val database: CafetoDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}