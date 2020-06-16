package com.dmr.ganu_alimentos.cart

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.dmr.ganu_alimentos.ProductsAdapter
import com.dmr.ganu_alimentos.R
import com.dmr.ganu_alimentos.model.CartItem
import com.dmr.ganu_alimentos.model.Product
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_products.*

class CartActivity:AppCompatActivity() {
    val UsuarioIDVariable:String  = FirebaseAuth.getInstance().currentUser!!.uid
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        recyclerViewCart.apply {
            layoutManager = GridLayoutManager(this.context, 1)
            adapter = CartAdapter(arrayListOf<Product>())
        }

        getCartItem()

        var cart = Cart()
        var cartItem = CartItem()


        deleteCart.setOnClickListener {
            Log.d("Boton eliminar recycler", "delete")
            cart.deleteCartItem(cartItem)
            Toast.makeText(this, "Cart deleted", Toast.LENGTH_LONG).show()
        }

    }


    fun getCartItem( ){
        val ref = FirebaseDatabase.getInstance().getReference("/cart/"+ UsuarioIDVariable)
        Log.e("Test", ref.toString())
        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError){}
            override fun onDataChange(p0: DataSnapshot){
                val products = arrayListOf<Product>()
                p0.children.forEach {
                    //val product = it.getValue(Product::class.java)
                    val product = it.child("products").getValue(Product::class.java)
                    products.add(product!!)
                }
                recyclerViewCart.adapter = CartAdapter(products)
            }
        })
    }

}
