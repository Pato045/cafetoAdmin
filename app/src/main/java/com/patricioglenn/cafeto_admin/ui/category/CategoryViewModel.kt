package com.patricioglenn.cafeto_admin.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patricioglenn.cafeto_admin.db.CafetoDao
import com.patricioglenn.cafeto_admin.model.Category
import kotlinx.coroutines.launch

class CategoryViewModel(private val database:CafetoDao): ViewModel(){

    fun saveNewCategory(category:String){
        var newCategory = Category()
        newCategory.name = category
        viewModelScope.launch {
            insertNewCategoryOnDatabase(newCategory)
        }
    }

    private suspend fun insertNewCategoryOnDatabase(category: Category){
        database.insertCategory(category)
    }
}