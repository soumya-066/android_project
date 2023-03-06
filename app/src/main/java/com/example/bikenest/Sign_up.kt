package com.example.bikenest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class Sign_up : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val sendOTPBtn=findViewById<AppCompatButton>(R.id.sendOTPBtn)
        sendOTPBtn.setOnClickListener {
            startActivity(Intent(this,Otp::class.java))
        }

    }
}