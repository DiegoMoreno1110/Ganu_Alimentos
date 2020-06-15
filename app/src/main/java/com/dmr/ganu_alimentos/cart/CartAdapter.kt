package com.dmr.ganu_alimentos.cart

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dmr.ganu_alimentos.R
import com.dmr.ganu_alimentos.model.CartItem
import com.dmr.ganu_alimentos.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_row.view.*

class CartAdapter (private val cartItems: ArrayList<Product>): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    /*
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(inflater, parent)
    }


     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_row, parent, false)
        val holder = ViewHolder(view)
        view.setOnClickListener {
            val intent = Intent(parent.context, CartActivity::class.java)
            intent.putExtra("title", cartItems[holder.adapterPosition].title)
            intent.putExtra("photo_url", cartItems[holder.adapterPosition].photoUrl)
            intent.putExtra("price", cartItems[holder.adapterPosition].price)

            parent.context.startActivity(intent)
        }

        return holder
    }

    /*
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val cartItem: CartItem = cartItems[position]
        var product = Product()
        holder.bind(product)

        /*
        deleteItem.setOnClickListener{

        }
        */

        /*
        holder.itemView.setOnClickListener {
            val cart = Cart()
            cart.borraUnItem(item)
        }

         */
    }

     */

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
    }

}

/*
class ItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.cart_row, parent, false)) {
    private var titleTextView: TextView? = null
    private var priceTextView: TextView? = null
    private var quantityTextView: TextView? = null





    //deleteItem

    init {
        titleTextView = itemView.findViewById(R.id.title)
        priceTextView = itemView.findViewById(R.id.price)
        //quantityTextView = itemView.findViewById(R.quantity)
    }


    fun bind(product: Product) {
        Log.i("Error despensa",product.title.toString() )
        titleTextView?.text = product.title.toString()
        priceTextView?.text = product.price.toString()
    }



    fun delete(cartItem: CartItem){
        val cart = Cart()
        cart.deleteCartItem(cartItem)
    }



}
*/