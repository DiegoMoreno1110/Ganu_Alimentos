package com.dmr.ganu_alimentos.cart

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dmr.ganu_alimentos.ProductDetails
import com.dmr.ganu_alimentos.R
import com.dmr.ganu_alimentos.model.CartItem
import com.dmr.ganu_alimentos.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_row.view.*

class CartAdapter (private val cartItems: ArrayList<Product>): RecyclerView.Adapter<CartAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_row, parent, false)
        val holder = ViewHolder(view)



        view.setOnClickListener {

            /*
            val intent = Intent(parent.context, CartActivity::class.java)
            intent.putExtra("title", cartItems[holder.adapterPosition].title)
            intent.putExtra("photo_url", cartItems[holder.adapterPosition].photoUrl)
            intent.putExtra("price", cartItems[holder.adapterPosition].price)

            parent.context.startActivity(intent)
            */
        }

        return holder
    }


    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        val product = cartItems[position]
        Picasso
            .get()
            .load(product.photoUrl)
            .error(R.drawable.ic_error_orange_24dp)
            .placeholder(R.drawable.progress_animation)
            .into(holder.image)

        holder.title.text = product.title
        val priceWithSymbol = "$" + product.price.toString() + " MXN"
        holder.price.text =  priceWithSymbol

        
    }

    override fun getItemCount(): Int = cartItems.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.photo
        val title : TextView = itemView.title
        val price : TextView = itemView.price
        var quantity : TextView = itemView.quantity
    }

}
