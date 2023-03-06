package com.example.bikenest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class Otp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        val verifyBtn=findViewById<AppCompatButton>(R.id.verifyBtn)

        verifyBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }


    }
}