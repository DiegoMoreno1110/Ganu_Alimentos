package com.dmr.ganu_alimentos.cart

import android.content.ContentValues
import android.util.Log
import com.dmr.ganu_alimentos.model.CartItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class Cart {

    private lateinit var database: DatabaseReference
    private lateinit var key: String
    private var firebaseUser: FirebaseUser? =  null

    constructor(){
        database = FirebaseDatabase.getInstance().reference
    }

    fun addCartItem(cartItem: CartItem){
        //val key = database.child("cart").push().key
        val userID = FirebaseAuth.getInstance().currentUser!!.uid
        val itemList = database.child("cart").child(userID!!).push()
        cartItem.id = itemList.key
        itemList.setValue(cartItem)


    }

    fun deleteCartItem(cartItem: CartItem){

        val key = FirebaseAuth.getInstance().currentUser!!.uid
        database.child("cart").child(key!!).removeValue()

        /*
        key = cartItem.id.toString()
        database.child("cart").child(key!!).removeValue()

         */
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