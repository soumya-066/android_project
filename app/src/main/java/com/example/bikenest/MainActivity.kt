package com.example.bikenest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    lateinit var bottomNav:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav=findViewById(R.id.bottomNavigationView)

        val Profile=findViewById<FloatingActionButton>(R.id.Profile)
        Profile.setOnClickListener{
            loadFragment(ProfileFragment())
        }
        loadFragment(HomeFragment())
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.Home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.Wallet -> {
                    gotoorder()
                    true
//                    loadFragment(WalletFragment())
//                    true
                }
                R.id.Favourite -> {
                    loadFragment(FavFragment())
                    true
                }
                R.id.Support -> {
//                    FirebaseAuth.getInstance().signOut()
//                    startActivity(Intent(this,Login::class.java))
//                    true
                    val phoneNumber = "9348727746" // replace this with your actual phone number
                    val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                    startActivity(dialIntent)
                    true

                }
                else -> {
                    loadFragment(HomeFragment())
                    true
                }
            }
        }


    }
      fun loadFragment(fragment: Fragment){
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.container1,fragment)
//        transaction.commit()

          supportFragmentManager.beginTransaction().apply {
              replace(R.id.container1, fragment)
              addToBackStack(null)
              commit()
          }
    }
    fun gotoorder(){
        startActivity(Intent(this,Order::class.java))
    }
}