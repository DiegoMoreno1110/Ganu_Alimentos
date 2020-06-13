package com.dmr.ganu_alimentos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dmr.ganu_alimentos.model.PayInfomation
import com.dmr.ganu_alimentos.model.Profile
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.modify_info.*
import kotlinx.android.synthetic.main.product_details.*

class ModifyInfo: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modify_info)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener { finish() }


        var profileFireBase = ProfileFireBase()
        var payInformationFireBase = PayInformationFireBase()
        var profile = Profile()
        var card = PayInfomation()

        modifyInfoProfileButton.setOnClickListener {

            profile.address = address.text.toString()
            profile.card = card
            profile.email = email.text.toString()
            profile.password = password.text.toString()
            profile.id = ""

            profileFireBase.addProfileInformation(profile)
        }

        modifyInfoCard.setOnClickListener {
            card.cvc = cvc.text.toString().toInt()
            card.expirationDate = expireDate.text.toString()
            card.id = ""
            card.nameCard = nameCard.text.toString()
            card.numberCard = numberCard.text.toString().toInt()

            payInformationFireBase.addPayInformation(card)
        }


        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }
}