package com.example.farm_comm

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.query
import com.example.farm_comm.adapter.ProductAdapter
import com.example.farm_comm.model.Product
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.*

class Products : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var auth:FirebaseAuth
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_products)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()


        // Initialize your RecyclerView, adapter, etc.
        val recyclerView: RecyclerView = findViewById(R.id.productsRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2) // 2 columns in grid layout

        val productList = listOf(
            Product(R.drawable.knapsack_battery_operated
            ,"ksh3500", "Knapsacksprayer"),
            Product(R.drawable.gardening_hoe,"ksh599", "gardening jembe"),
            Product(R.drawable.transformer_chloropyriphos_insecticide,
                "ksh199", "maize insecticide"),
            Product(R.drawable.r1, "ksh3400", "hand sprayer"),
            Product(R.drawable.oip1, "ksh1500", "fertiliser"),
            Product(R.drawable.product, "ksh900", "granular fertiliser"),


        )

        val adapter = ProductAdapter(productList)
        recyclerView.adapter = adapter



        // ...

                // Set up bottom navigation
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        bottomNavigation.setOnItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.navigation_profile -> {
                            // Show logout dialog when profile is clicked
                            showLogoutDialog()
                            false // Prevent selection change
                        }
                        // Other navigation items...
                        else -> true
                    }
                }
            }


    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes") { _, _ ->
                        // Sign out from Firebase
                auth.signOut()

                        // Redirect to login screen
                val intent = Intent(applicationContext, Login::class.java)
                        // Clear back stack
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
                    }
                .setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }



}



