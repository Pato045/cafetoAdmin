package com.patricioglenn.cafeto_admin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.patricioglenn.cafeto_admin.R
import com.patricioglenn.cafeto_admin.databinding.ActivityMainBinding
import com.patricioglenn.cafeto_admin.db.CafetoDatabase
import com.patricioglenn.cafeto_admin.ui.product_list.ProductListFragmentDirections

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout
        drawerLayout.openDrawer(GravityCompat.START)
        navController = this.findNavController(R.id.navHostFragment)

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        val application = this.application
        val dataSource = CafetoDatabase.getInstance(application).cafetoDao
        val viewModelFactory = MainActivityViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)
        binding.mainActivityViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.categories.observe(this, Observer {
            binding.navView.menu.add(0, 1, 1, "Ver Todo").setOnMenuItemClickListener {
                navController.navigate(ProductListFragmentDirections.actionProductListFragmentSelf(0))
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
            it.forEach {
                binding.navView.menu.add(1, it.id.toInt(), it.id.toInt(), it.name).setOnMenuItemClickListener {
                    navController.navigate(ProductListFragmentDirections.actionProductListFragmentSelf(it.itemId))
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
            }
            binding.navView.invalidate()
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

}