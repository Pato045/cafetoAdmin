package com.patricioglenn.cafeto_admin.ui.product_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.patricioglenn.cafeto_admin.db.CafetoDao
import com.patricioglenn.cafeto_admin.model.Product

class ProductListViewModel (database: CafetoDao, categoryId: Int): ViewModel(){

    lateinit var products : LiveData<List<Product>>

    init {
        initializeProducts(categoryId, database)
    }

    fun onProductClick(id: Long){

    }

    private fun initializeProducts(categoryId: Int, database: CafetoDao){
        products = if (categoryId == 0) {
            database.getAllProducts()
            }else{
                database.getProductsFromCategory(categoryId)
        }
    }
}