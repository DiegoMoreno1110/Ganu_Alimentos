package com.dmr.ganu_alimentos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.dmr.ganu_alimentos.model.Product
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.main.*

class MainScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        setSupportActionBar(toolbar)

        val products = arrayListOf<Product>()

        for(i in 0..100){
            products.add(Product("Product $i", "https://via.placeholder.com/350/dddddd/000000", 1.99))
        }

        recycler_view.apply {
            layoutManager = GridLayoutManager(this@MainScreenActivity, 2)
            adapter = ProductsAdapter(products)
        }
    }
}
