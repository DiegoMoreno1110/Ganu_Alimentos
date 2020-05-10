package com.dmr.ganu_alimentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dmr.ganu_alimentos.model.Product
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.fragment_products.view.*

class ProductsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_products, container, false)


        val products = arrayListOf<Product>()

        for(i in 0..100){
            products.add(Product("Product $i", "https://via.placeholder.com/350/dddddd/000000", 1.99))
        }

        root.recycler_view.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = ProductsAdapter(products)
        }

        return root
    }
}