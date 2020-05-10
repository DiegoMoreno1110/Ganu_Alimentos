package com.dmr.ganu_alimentos

import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.product_details.*
import kotlinx.android.synthetic.main.product_details.photo
import kotlinx.android.synthetic.main.product_row.*

class ProductDetails: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)
        setSupportActionBar(toolbar)

        val title = intent.getStringExtra("title")
        val photoUrl = intent.getStringExtra("photo_url")

        Picasso
            .get()
            .load(photoUrl)
            .error(R.drawable.ic_error_orange_24dp)
            .placeholder(R.drawable.progress_animation)
            .into(photo)

        product_name.text = title

        buy.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("¿Añadir $title al carrito?")
                .setPositiveButton("SI") { dialog, which ->
                    Log.d("Se añadió al carrito", title)
                }.setNegativeButton("NO"){ dialog, which ->
                    Log.d("No se añadió al carrito", title)
                }
                .create().show()
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }
}