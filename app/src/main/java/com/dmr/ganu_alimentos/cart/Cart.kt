package com.dmr.ganu_alimentos.cart

import android.content.ContentValues
import android.util.Log
import com.dmr.ganu_alimentos.model.CartItem
import com.google.firebase.database.*

class Cart {

    private lateinit var database: DatabaseReference
    private lateinit var key: String

    constructor(){
        database = FirebaseDatabase.getInstance().reference
    }

    fun addCartItem(cartItem: CartItem){
        val key = database.child("cart").push().key
        cartItem.id = key
        database.child("cart").child(key!!).setValue(cartItem)

    }

    fun deleteCartItem(cartItem: CartItem){
        key = cartItem.id.toString()
        database.child("cart").child(key!!).removeValue()
    }


    fun updateCartItem( cartItem: CartItem){
        val childUpdates = HashMap<String, Any>()
        key = cartItem.id.toString()
        childUpdates["/cart/$key"] = cartItem
        database.updateChildren(childUpdates)
    }

    fun getCartItem( ){
        val ref = database.child("cart")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.e("Count " , dataSnapshot.childrenCount.toString())
                for (child in dataSnapshot.children){
                    val item = child.getValue(Cart::class.java)
                    Log.e("Item en firebase: ", item.toString())
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        ref.addValueEventListener(postListener)
    }

}