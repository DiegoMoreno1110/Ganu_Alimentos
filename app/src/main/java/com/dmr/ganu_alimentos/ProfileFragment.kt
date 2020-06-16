package com.dmr.ganu_alimentos

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dmr.ganu_alimentos.model.PayInfomation
import com.dmr.ganu_alimentos.model.Profile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment: Fragment() {

    private var database: DatabaseReference = FirebaseDatabase.getInstance().reference
    val key = FirebaseAuth.getInstance().currentUser!!.uid

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        view.buttonModifyInfo.setOnClickListener {
            activity?.let{
                val intent = Intent(it, ModifyInfo::class.java)
                it.startActivity(intent)
            }
        }

        getInfoProfile()
        getInfoCard()

        return view
    }


    fun getInfoCard(){

        val usersRef = database.child("card").child(key!!)

        usersRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    val pay = p0.getValue<PayInfomation>(PayInfomation::class.java)
                    payUser.text = pay!!.numberCard.toString()

                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }


    fun getInfoProfile(){

        val usersRef = database.child("profile").child(key!!)

        usersRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    val profile = p0.getValue<Profile>(Profile::class.java)
                    addressUser?.text = profile!!.address + " " + profile!!.email + " " +profile!!.password
                    //emailUser.text =  profile!!.email

                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }


}