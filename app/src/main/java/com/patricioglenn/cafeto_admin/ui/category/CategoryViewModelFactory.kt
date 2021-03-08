package com.patricioglenn.cafeto_admin.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.patricioglenn.cafeto_admin.db.CafetoDao

class CategoryViewModelFactory (private val database: CafetoDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}