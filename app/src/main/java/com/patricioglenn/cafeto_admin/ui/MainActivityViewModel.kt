package com.patricioglenn.cafeto_admin.ui

import androidx.lifecycle.ViewModel
import com.patricioglenn.cafeto_admin.db.CafetoDao

class MainActivityViewModel ( database: CafetoDao): ViewModel(){

    val categories = database.getAllCategories()

}