package com.example.bikenest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    lateinit var bottomNav:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav=findViewById(R.id.bottomNavigationView)

        val Home=findViewById<FloatingActionButton>(R.id.Home)
        Home.setOnClickListener{
            loadFragment(HomeFragment())
        }

        loadFragment(HomeFragment())
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.Profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                R.id.Wallet -> {
                    loadFragment(WalletFragment())
                    true
                }
                R.id.Favourite -> {
                    loadFragment(FavFragment())
                    true
                }

                else -> {
                    loadFragment(HomeFragment())
                    true
                }
            }
        }


    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}