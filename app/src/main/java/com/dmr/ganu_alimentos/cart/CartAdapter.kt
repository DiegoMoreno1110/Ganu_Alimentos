package com.dmr.ganu_alimentos.cart

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dmr.ganu_alimentos.R
import com.dmr.ganu_alimentos.model.CartItem
import com.dmr.ganu_alimentos.model.Product

class CartAdapter (private val list: List<CartItem>): RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val cartItem: CartItem = list[position]
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

    override fun getItemCount(): Int = list.size


}

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