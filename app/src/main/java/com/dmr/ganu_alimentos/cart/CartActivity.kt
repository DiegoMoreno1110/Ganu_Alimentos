package com.dmr.ganu_alimentos.cart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dmr.ganu_alimentos.R
import kotlinx.android.synthetic.main.app_bar.*

class CartActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }


}
