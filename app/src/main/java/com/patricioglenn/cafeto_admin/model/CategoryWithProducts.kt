package com.patricioglenn.cafeto_admin.model

import androidx.room.Embedded
import androidx.room.Relation
import com.patricioglenn.cafeto_admin.model.Category
import com.patricioglenn.cafeto_admin.model.Product

data class CategoryWithProducts(
        @Embedded
    val category: Category,

        @Relation( parentColumn = "id", entityColumn = "category_id")
    val products: List<Product>
)