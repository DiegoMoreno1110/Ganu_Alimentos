package com.dmr.ganu_alimentos.cart
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.dmr.ganu_alimentos.OrderFireBase
import com.dmr.ganu_alimentos.R
import com.dmr.ganu_alimentos.model.CartItem
import com.dmr.ganu_alimentos.model.Order
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.app_bar.*

class CartActivity:AppCompatActivity() {
    private val userID:String  = FirebaseAuth.getInstance().currentUser!!.uid
    private var sum:Double = 0.0
    private val cart = Cart()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
        recyclerViewCart.apply {
            layoutManager = GridLayoutManager(this.context, 1)
            adapter = CartAdapter(arrayListOf())
        }
        getCartItem()

        comprar.setOnClickListener{
            cart.deleteCart()
            Toast.makeText(this, "Procesando compra", Toast.LENGTH_LONG).show()
            total.text = "0.0"
        }

    }


    private fun getCartItem( ){
        val ref = FirebaseDatabase.getInstance().getReference("/cart/$userID")
        Log.e("Test", ref.toString())
        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError){}
            override fun onDataChange(p0: DataSnapshot){
                val cartProducts = arrayListOf<CartItem>()
                p0.children.forEach {
                    val cartItem = it.getValue(CartItem::class.java)
                    sum += cartItem!!.products!!.price!! * cartItem.quantity!!
                    total.text = sum.toString()
                    cartProducts.add(cartItem)
                }
                recyclerViewCart.adapter = CartAdapter(cartProducts)
            }
        })
    }

}
