package com.dmr.ganu_alimentos

import android.util.Log
import com.dmr.ganu_alimentos.model.CartItem
import com.dmr.ganu_alimentos.model.Order
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class OrderFireBase {

    private lateinit var database: DatabaseReference
    private lateinit var key: String
    private var firebaseUser: FirebaseUser? =  null


    constructor(){
        database = FirebaseDatabase.getInstance().reference
    }


    public fun addOrder(order: Order, items: ArrayList<CartItem>){
        val userID = FirebaseAuth.getInstance().currentUser!!.uid
        val itemOrderList = database.child("order").child(userID!!).push()
        order.id = itemOrderList.key
        var names = ""
        items.forEach {
            names += it.products!!.title!! + ","
        }
        order.cartItem = names
        var holderTotal = 0.0
        items.forEach{
            holderTotal += it.products!!.price!!
            Log.e("It", it.toString())
        }
        order.total = holderTotal
        Log.e("Order", order.toString())
        itemOrderList.setValue(order)
    }

}