package com.dmr.ganu_alimentos

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dmr.ganu_alimentos.cart.Cart
import com.dmr.ganu_alimentos.model.CartItem
import com.dmr.ganu_alimentos.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.product_details.*


class ProductDetails: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)
        setSupportActionBar(toolbar)



        toolbar.setNavigationOnClickListener { finish() }


        var title = intent.getStringExtra("title")
        var photoUrl = intent.getStringExtra("photo_url")
        var price = intent.getStringExtra("price")

        Picasso
            .get()
            .load(photoUrl)
            .error(R.drawable.ic_error_orange_24dp)
            .placeholder(R.drawable.progress_animation)
            .into(photo)

        product_name.text = title

        lessCartItems.setOnClickListener {
            var currCount = itemCounter.text.toString().toInt()
            if(currCount != 0){
                currCount--
                itemCounter.text = currCount.toString()
            }
        }

        var MAX_ITEMS = 5
        moreCartItems.setOnClickListener {
            var currCount = itemCounter.text.toString().toInt()
            if(currCount != MAX_ITEMS){
                currCount++
                itemCounter.text = currCount.toString()
            }
        }

        buy.setOnClickListener {
            var cart = Cart()
            var product = Product()
            var cartItem = CartItem()

            cartItem.id = ""
            cartItem.products = product
            cartItem.quantity = itemCounter.text.toString().toInt()

            product.photoUrl = photoUrl
            product.price = 1250.00
            product.title = title


            AlertDialog.Builder(this)
                .setMessage("¿Añadir $title al carrito?")
                .setPositiveButton("SI") { dialog, which ->
                    Log.d("Se añadió al carrito", title)
                    cart.addCartItem(cartItem)
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