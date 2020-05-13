package com.dmr.ganu_alimentos

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dmr.ganu_alimentos.cart.CartActivity
import com.dmr.ganu_alimentos.model.Product
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.main_content.*
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.main.*

class MainScreenActivity : AppCompatActivity() {

    private var currentFragment:Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        setSupportActionBar(toolbar)

        Log.d("FRAGMENT CURRENT", currentFragment.toString())
        // Set initial Fragment
        if(currentFragment === null){
            currentFragment = R.id.actionHome
        }

        changeFragment(currentFragment!!)

        nav_view.setNavigationItemSelectedListener {
            it.isChecked = true
            drawer_layout.closeDrawers()

            changeFragment(it.itemId)

            true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        }

        fab.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, ChatFragment())
                .commit()
            currentFragment = R.id.actionChat
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == R.id.actionCart){
            startActivity(Intent(this,CartActivity::class.java))
            return true
        }
        drawer_layout.openDrawer(GravityCompat.START)
        return true
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        currentFragment?.let { outState.putInt("CurrentFragment", it) }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentFragment = savedInstanceState.getInt("CurrentFragment")
        //var newCurrentFragment = savedInstanceState.getInt("CurrentFragment")
    }

    fun changeFragment(itemId:Int){
        when(itemId){
            R.id.actionHome -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, HomeFragment())
                    .commit()
                currentFragment = R.id.actionHome
            }
            R.id.actionProducts -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, ProductsFragment())
                    .commit()
                currentFragment = R.id.actionProducts
            }
            R.id.actionProfile -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, ProfileFragment())
                    .commit()
                currentFragment = R.id.actionProfile
            }
            R.id.actionOrder -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, OrderFragment())
                    .commit()
                currentFragment = R.id.actionOrder
            }
            R.id.actionChat -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, ChatFragment())
                    .commit()
                currentFragment = R.id.actionChat
            }
            R.id.actionLogOut -> {
                FirebaseAuth.getInstance().signOut();
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar,menu)
        return true
    }
}
