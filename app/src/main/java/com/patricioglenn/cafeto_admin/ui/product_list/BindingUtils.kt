package com.patricioglenn.cafeto_admin.product_list

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.patricioglenn.cafeto_admin.model.Product

@BindingAdapter("titleVisibility")
fun TextView.setTitleVisibility(item: Product?) {
    item?.let {
        visibility = if (item.title != ""){
            View.VISIBLE
        } else{
            View.GONE
        }
    }
}

@BindingAdapter("descriptionVisibility")
fun TextView.setDescriptionVisibility(item: Product?) {
    item?.let {
        visibility = if (item.description != ""){
            View.VISIBLE
        } else{
            View.GONE
        }
    }
}

@BindingAdapter("productPriceFormatted")
fun TextView.setProductPriceFormatted(item: Product?){
    item?.let {
        text = if(item.price == ""){
            "Consultar"
        }else{
            "$ ${it.price}"
        }
    }
}

@BindingAdapter("categoryIdAsString")
fun TextView.setCategoryIdAsString(item: Product?){
    item?.let {
        text = "${it.categoryId}"
    }
}