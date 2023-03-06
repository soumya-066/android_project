package com.example.bikenest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText

class Sign_up : AppCompatActivity() {
    private lateinit var  sendOTPBtn:AppCompatButton
    private lateinit var  personName:TextInputEditText
    private lateinit var  phoneNumberET:TextInputEditText
    private lateinit var  UserPasswordForSignup:TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        sendOTPBtn=findViewById(R.id.sendOTPBtn)
        personName=findViewById(R.id.personName)
        phoneNumberET=findViewById(R.id.phoneNumberET)
        UserPasswordForSignup=findViewById(R.id.UserPasswordForSignup)

        sendOTPBtn.setOnClickListener {

            if (personName.text.toString().isEmpty()) {
                personName.setError("Please Enter Your Name")
                personName.requestFocus()
            } else if (phoneNumberET.text.toString().isEmpty()) {
                phoneNumberET.setError("Enter your Number")
                phoneNumberET.requestFocus()
            } else if (UserPasswordForSignup.text.toString().isEmpty()) {
                UserPasswordForSignup.setError("Enter Your Password")
                UserPasswordForSignup.requestFocus()
            } else if (phoneNumberET.text.toString().length == 10) {
                startActivity(Intent(this, Otp::class.java))
            } else {
                phoneNumberET.setError("Enter The Correct Number")
                phoneNumberET.requestFocus()
            }
        }

    }
}