package com.dmr.ganu_alimentos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dmr.ganu_alimentos.model.Product
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.main_content.*
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.main.*

class MainScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        setSupportActionBar(toolbar)

        // Set initial Fragment
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, HomeFragment()).commit()

        nav_view.setNavigationItemSelectedListener {
            it.isChecked = true
            drawer_layout.closeDrawers()

            when(it.itemId){
                // Example to navigate to other Fragment with the menu
                /*
                R.id.<id from an item in res/menu/menu_main> -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, <FragmentClassName>())
                        .commit()
                }
                 */
                R.id.actionHome -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, HomeFragment())
                        .commit()
                }
                R.id.actionProducts -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, ProductsFragment())
                        .commit()
                }
            }

            true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        drawer_layout.openDrawer(GravityCompat.START)
        return true
    }
}
