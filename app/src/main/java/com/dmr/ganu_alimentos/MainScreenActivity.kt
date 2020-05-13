package com.dmr.ganu_alimentos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dmr.ganu_alimentos.cart.CartActivity
import com.dmr.ganu_alimentos.model.Product
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.main_content.*
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.main.*

class MainScreenActivity : AppCompatActivity() {

    private var currentFragment:Int = R.id.actionHome

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        setSupportActionBar(toolbar)

        // Set initial Fragment
        changeFragment(currentFragment)

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

        outState.putInt("CurrentFragment", currentFragment)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentFragment = savedInstanceState.getInt("CurrentFragment")
        //var newCurrentFragment = savedInstanceState.getInt("CurrentFragment")
        changeFragment(currentFragment)
    }

    fun changeFragment(itemId:Int){
        when(itemId){
            // Example to navigate to other Fragment with the menu
            /*
            R.id.<id from an item in res/menu/menu_main> -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, <FragmentClassName>())
                    .commit()

                currentFragment = R.id.<actionName>
            }
             */
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
            R.id.actionProducts -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, ProductsFragment())
                    .commit()
                currentFragment = R.id.actionProducts
            }
        }

        Log.d("Current fragment", currentFragment.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar,menu)
        return true
    }
}
