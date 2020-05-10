package com.dmr.ganu_alimentos

import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.product_details.*

class ProductDetails: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        val title = intent.getStringExtra("title")
        product_name.text = title

        buy.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("Hey, $title is in stock!")
                .setPositiveButton("OK") { dialog, which ->
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
                .create().show()
        }
    }
}