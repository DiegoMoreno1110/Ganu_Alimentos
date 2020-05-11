package com.dmr.ganu_alimentos

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dmr.ganu_alimentos.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_row.view.*

class ProductsAdapter(private val products: ArrayList<Product>): RecyclerView.Adapter<ProductsAdapter.ViewHolder >(){
    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
        val product = products[position]
        Picasso
            .get()
            .load(product.photoUrl)
            .error(R.drawable.ic_error_orange_24dp)
            .placeholder(R.drawable.progress_animation)
            .into(holder.image)

        holder.title.text = product.title
        val priceWithSymbol = "$" + product.price.toString() + " MXN"
        holder.price.text =  priceWithSymbol

        if(product.sale!!) holder.saleIcon.visibility = View.VISIBLE
        else holder.saleIcon.visibility = View.GONE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)
        val holder = ViewHolder(view)
        view.setOnClickListener {
            val intent = Intent(parent.context, ProductDetails::class.java)
            intent.putExtra("title", products[holder.adapterPosition].title)
            intent.putExtra("photo_url", products[holder.adapterPosition].photoUrl)
            parent.context.startActivity(intent)
        }

        return holder
    }

    override fun getItemCount() = products.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.photo
        val title : TextView = itemView.title
        val price : TextView = itemView.price
        val saleIcon : ImageView = itemView.saleImageView
    }
}