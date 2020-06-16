package com.dmr.ganu_alimentos.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dmr.ganu_alimentos.R
import com.dmr.ganu_alimentos.model.CartItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_row.view.*

class CartAdapter (private val cartItems: ArrayList<CartItem>): RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    private var cart = Cart()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_row, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = cartItems[position].products!!
        val cantidad = cartItems[position].quantity
        Picasso
            .get()
            .load(product.photoUrl)
            .error(R.drawable.ic_error_orange_24dp)
            .placeholder(R.drawable.progress_animation)
            .into(holder.image)

        holder.title.text = product.title
        val priceWithSymbol = "$" + product.price.toString() + " MXN"
        holder.price.text =  priceWithSymbol
        holder.quantity.text = cantidad.toString()
        holder.delete.setOnClickListener {
            cart.deleteCartItem(cartItems[position])
        }

    }

    override fun getItemCount(): Int = cartItems.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.photo
        val title : TextView = itemView.title
        val price : TextView = itemView.price
        var quantity : TextView = itemView.quantity
        var delete : TextView = itemView.delete
    }

}
