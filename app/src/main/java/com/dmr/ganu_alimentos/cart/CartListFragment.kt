package com.dmr.ganu_alimentos.cart

import android.content.ClipData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmr.ganu_alimentos.R
import com.dmr.ganu_alimentos.model.CartItem
import kotlinx.android.synthetic.main.activity_cart.*

class CartListFragment : Fragment() {

    var cart = Cart()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_cart, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerViewCart.apply {
            layoutManager = LinearLayoutManager(activity)
            //adapter = CartAdapter(mutableListOf<CartItem>())
        }

        cart.getCartItem()

    }


}