package com.dmr.ganu_alimentos

import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment:Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_home, container, false)



        val animDrawable = view.home_layout.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(5000)
        animDrawable.start()



        view.to_facebook.setOnClickListener {
            context?.let { it1 -> goToURL(it1, "https://www.facebook.com/GanuAlimentos/") }
        }

        view.to_twitter.setOnClickListener {
            context?.let { it1 -> goToURL(it1, "https://twitter.com/ganualimentos") }
        }

        return view
    }
}

fun goToURL(context: Context,url:String){
    val uri: Uri = Uri.parse(url) // missing 'http://' will cause crashed
    val intent = Intent(Intent.ACTION_VIEW, uri)
    startActivity(context, intent, null)
}