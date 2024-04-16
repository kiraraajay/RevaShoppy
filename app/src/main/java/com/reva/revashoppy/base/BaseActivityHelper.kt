package com.reva.revashoppy.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.reva.revashoppy.R
import com.reva.revashoppy.ui.dashboard.Fragments.CartFragment
import com.reva.revashoppy.ui.dashboard.Fragments.CategoryFragment
import com.reva.revashoppy.ui.dashboard.Fragments.HomeFragment
import com.reva.revashoppy.ui.dashboard.Fragments.ProfileFragment

class BaseActivityHelper(private val activity: AppCompatActivity) {

    var navigationView: BottomNavigationView? = null

    // Initialize selectedListener here
    private val selectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.nav_home -> {
                val homefragment = HomeFragment()
                replaceFragment( true,homefragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_cart -> {
                val cartfragment = CartFragment()
                replaceFragment(true,cartfragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_category -> {
                val categoryFragment = CategoryFragment()
                replaceFragment(true,categoryFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_favorite -> {
                val profilefragment = ProfileFragment()
                replaceFragment( true,profilefragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_profile -> {
                val profilefragment = ProfileFragment()
                replaceFragment( true,profilefragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    init {
        // Initialize your UI components here
        navigationView = activity.findViewById<View>(R.id.bnv_navigation) as BottomNavigationView?
        navigationView!!.setOnNavigationItemSelectedListener(selectedListener)

        // Load the default fragment
        val fragment = HomeFragment()
        replaceFragment(fragment)

    }

    fun replaceFragment(fragment: Fragment) { replaceFragment(false, fragment) }


    fun replaceFragment(isAdd: Boolean?, fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
       // fragmentTransaction.replace(R.id.content, fragment,"")
        fragmentTransaction.replace(R.id.fl_content, fragment, fragment.javaClass.name)
        fragmentTransaction.commit()
        if (isAdd==true){
          //  fragmentTransaction.addToBackStack(fragment.javaClass.name)
           // fragmentTransaction.addToBackStack("")
        }
    }


}