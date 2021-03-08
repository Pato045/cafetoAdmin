package com.patricioglenn.cafeto_admin.ui.product


import androidx.lifecycle.*
import com.patricioglenn.cafeto_admin.db.CafetoDao
import com.patricioglenn.cafeto_admin.model.Product
import kotlinx.coroutines.launch

class ProductViewModel(private val database:CafetoDao): ViewModel(){

    var categories = database.getAllCategories()

    var newProduct = Product()

    fun setProductCategory(category: String){
        categories.value?.forEach{
            if (it.name==category){
                newProduct.categoryId = it.id
            }
        }
    }

    fun saveProduct(){
        viewModelScope.launch {
            insertProductOnDatabase(newProduct)
        }
    }

    fun getList() : List<String>{
        var list = mutableListOf<String>()
        categories.value?.forEach{
            list.add(it.name)
        }
        return list
    }

    private suspend fun insertProductOnDatabase(product: Product){
        database.insertProduct(product)
    }
}