package com.patricioglenn.cafeto_admin.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.patricioglenn.cafeto_admin.db.CafetoDao

class MainActivityViewModelFactory (private val database: CafetoDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}