package com.patricioglenn.cafeto_admin.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.patricioglenn.cafeto_admin.model.Category
import com.patricioglenn.cafeto_admin.model.CategoryWithProducts
import com.patricioglenn.cafeto_admin.model.Product

@Dao
interface CafetoDao {

    @Insert
    suspend fun insertCategory(category: Category)

    @Update
    suspend fun updateCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("SELECT * FROM categories_table WHERE id = :id")
    suspend fun getOneCategory(id: Long): Category?

    @Query("SELECT * FROM categories_table ORDER BY id")
    fun getAllCategories(): LiveData<List<Category>>

    @Insert
    suspend fun insertProduct(product: Product)

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("SELECT * FROM products_table WHERE id = :id")
    suspend fun getOneProduct(id: Long): Product?

    @Query("SELECT * FROM products_table ORDER BY category_id , id")
    fun getAllProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM products_table WHERE category_id = :categoryId ORDER BY id")
    fun getProductsFromCategory(categoryId: Int): LiveData<List<Product>>

    @Transaction
    @Query("SELECT * FROM categories_table ORDER BY id")
    fun getAllCategoriesWithProducts(): LiveData<List<CategoryWithProducts>>

}