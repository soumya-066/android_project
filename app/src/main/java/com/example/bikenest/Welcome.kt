package com.example.bikenest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        var splashToolbar: Toolbar = findViewById(R.id.splashToolbar)
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.upsliding)
        splashToolbar.startAnimation(slideAnimation)
        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        load()


    }

    private fun load() {
        val firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        if (firebaseUser == null) {
            Handler(Looper.getMainLooper()).postDelayed({
                val i = Intent(this, Login::class.java)
                startActivity(i)
                finish()
            }, 2500)
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()
            }, 2500)

        }

    }


}