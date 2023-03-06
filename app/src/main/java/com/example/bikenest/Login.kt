package com.example.bikenest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val Sign_Up_textview=findViewById<TextView>(R.id.Sign_Up_textview)
        Sign_Up_textview.setOnClickListener {
            startActivity(Intent(this,Sign_up::class.java))
        }

    }
}