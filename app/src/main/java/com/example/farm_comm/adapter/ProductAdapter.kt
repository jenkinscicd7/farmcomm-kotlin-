package com.example.farm_comm.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.farm_comm.model.Product

class ProductAdapter(private val context: Context, private val productList:List<Product>):
 RecyclerView.Adapter<ProductAdapter.ProductViewHolder>()

{
     inner class ProductViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

     }

}