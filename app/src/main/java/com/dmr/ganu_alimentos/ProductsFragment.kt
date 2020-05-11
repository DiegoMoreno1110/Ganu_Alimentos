package com.dmr.ganu_alimentos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dmr.ganu_alimentos.model.Product
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.fragment_products.view.*

class ProductsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val root = inflater.inflate(R.layout.fragment_products, container, false)
        Log.d("TAG", "Prueba")
        return inflater.inflate(R.layout.fragment_products, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler_view.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = ProductsAdapter(arrayListOf<Product>())
        }
        getProducts()
    }

/*
        val products = arrayListOf<Product>()

        //Hardcoding the products, in the future this data will be fetched from a JSON
        products.add(Product("Ganu Sabor Carne de Res (caja con 20 piezas)", "https://i.ibb.co/ftntGsL/ganu-prod-image-1-min.jpg", 499.00, false))
        products.add(Product("Ganu Sabor Carne de Res (caja con 45 piezas)", "https://i.ibb.co/ftntGsL/ganu-prod-image-1-min.jpg", 1125.00, true))

        root.recycler_view.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = ProductsAdapter(products)
        }
*/
        /*return root
    }*/

    fun getProducts(){
        val ref = FirebaseDatabase.getInstance().getReference("/producto")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError){}
            override fun onDataChange(p0: DataSnapshot){
                val products = arrayListOf<Product>()
                p0.children.forEach {
                    val product = it.getValue(Product::class.java)
                    products.add(product!!)
                }
                recycler_view.adapter = ProductsAdapter(products)
            }
        })
    }
}