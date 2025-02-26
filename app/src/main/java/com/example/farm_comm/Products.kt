package com.example.farm_comm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.farm_comm.adapter.ProductAdapter
import com.example.farm_comm.model.Product
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Products : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var auth:FirebaseAuth
    private lateinit var productAdapter: ProductAdapter
    private lateinit var databaseReference:DatabaseReference
    private val productList = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_products)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        // Initialize your RecyclerView, adapter, etc.
        recyclerView = findViewById(R.id.productsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        databaseReference = FirebaseDatabase.getInstance().getReference("items")


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




