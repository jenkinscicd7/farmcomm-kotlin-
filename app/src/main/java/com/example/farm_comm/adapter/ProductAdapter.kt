package com.example.farm_comm.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.farm_comm.ProductDetails
import com.example.farm_comm.R
import com.example.farm_comm.ui.theme.model.Product


class ProductAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.productImageView)
        val productName: TextView = itemView.findViewById(R.id.productNameTextView)
        val productPrice: TextView = itemView.findViewById(R.id.priceTextView)
        val addToCartButton: Button = itemView.findViewById(R.id.addToCartButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.productName.text = product.title
        holder.productPrice.text = product.price
        holder.productImage.setImageResource(product.picUrl)

        holder.addToCartButton.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Added ${product.title} to cart", Toast.LENGTH_SHORT).show()
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ProductDetails::class.java).apply {
                putExtra("product_name", product.title)
                putExtra("product_price", product.price)
                putExtra("product_image", product.picUrl)
            }
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount() = productList.size


}











