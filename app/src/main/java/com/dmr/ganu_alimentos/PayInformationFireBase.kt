package com.dmr.ganu_alimentos

import android.content.ContentValues
import android.util.Log
import com.dmr.ganu_alimentos.model.PayInfomation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class PayInformationFireBase {

    private lateinit var database: DatabaseReference
    private lateinit var key: String

    constructor(){
        database = FirebaseDatabase.getInstance().reference
    }

    fun addPayInformation(card: PayInfomation){
        val key = FirebaseAuth.getInstance().currentUser!!.uid
        card.id = key
        database.child("card").child(key!!).setValue(card)

    }

    fun updatePayInformation(card: PayInfomation){
        val childUpdates = HashMap<String, Any>()
        key = card.id.toString()
        childUpdates["/card/$key"] = card
        database.updateChildren(childUpdates)
    }

    fun getProfileInformation( ){
        val ref = database.child("card")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.e("Count " , dataSnapshot.childrenCount.toString())
                for (child in dataSnapshot.children){
                    val item = child.getValue(PayInformationFireBase::class.java)
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