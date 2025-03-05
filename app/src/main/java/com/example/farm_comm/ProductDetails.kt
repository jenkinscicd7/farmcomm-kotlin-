package com.example.farm_comm

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProductDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val productName = intent.getStringExtra("product_name")
        val productPrice = intent.getStringExtra("product_price")
        val productImage = intent.getIntExtra("product_image", 0) // Default to 0

        val imageView = findViewById<ImageView>(R.id.productImage)
        val nameTextView = findViewById<TextView>(R.id.productName)
        val priceTextView = findViewById<TextView>(R.id.productPrice)
        val btnAddToCart = findViewById<Button>(R.id.btnAddToCart)
        val btnShare = findViewById<Button>(R.id.btnShare)

        nameTextView.text = productName
        priceTextView.text = "Price: $productPrice"
        imageView.setImageResource(productImage)

        btnAddToCart.setOnClickListener {
            // Implement add-to-cart logic
        }

        btnShare.setOnClickListener {
            shareProduct(productName, productPrice, productImage)
        }
    }

    private fun shareProduct(name: String?, price: String?, imageResId: Int) {
        val drawableUri = getDrawableUri(imageResId)

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_STREAM, drawableUri)
            putExtra(Intent.EXTRA_TEXT, "Check out $name for just $price!")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }

    private fun getDrawableUri(imageResId: Int): Uri {
        return Uri.parse("android.resource://" + packageName + "/" + imageResId)
    }
}

