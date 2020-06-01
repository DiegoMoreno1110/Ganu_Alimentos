package com.dmr.ganu_alimentos

import android.content.ContentValues
import android.util.Log
import com.dmr.ganu_alimentos.model.Profile
import com.google.firebase.database.*

class ProfileFireBase {

    private lateinit var database: DatabaseReference
    private lateinit var key: String

    constructor(){
        database = FirebaseDatabase.getInstance().reference
    }

    fun addProfileInformation(profile: Profile){
        val key = database.child("profile").push().key
        profile.id = key
        database.child("profile").child(key!!).setValue(profile)

    }

    fun updateProfileInformation(profile: Profile){
        val childUpdates = HashMap<String, Any>()
        key = profile.id.toString()
        childUpdates["/profile/$key"] = profile
        database.updateChildren(childUpdates)
    }

    fun getProfileInformation( ){
        val ref = database.child("profile")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.e("Count " , dataSnapshot.childrenCount.toString())
                for (child in dataSnapshot.children){
                    val item = child.getValue(ProfileFireBase::class.java)
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