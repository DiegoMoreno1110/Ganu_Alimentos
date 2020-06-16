package com.dmr.ganu_alimentos

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


    fun addOrder(order: Order){
        val userID = FirebaseAuth.getInstance().currentUser!!.uid
        val itemOrderList = database.child("order").child(userID!!).push()
        order.id = itemOrderList.key
        itemOrderList.setValue(order)

    }

}