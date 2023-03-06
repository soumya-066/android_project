package com.example.bikenest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText

class Sign_up : AppCompatActivity() {
    private lateinit var  sendOTPBtn:AppCompatButton
    private lateinit var  personName:TextInputEditText
    private lateinit var  EmailET:TextInputEditText
    private lateinit var  UserPasswordForSignup:TextInputEditText
    private lateinit var UserPasswordForSignupConfirm:TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        sendOTPBtn=findViewById(R.id.sendOTPBtn)
        personName=findViewById(R.id.personName)
        EmailET=findViewById(R.id.EmailET)
        UserPasswordForSignup=findViewById(R.id.UserPasswordForSignup)
        UserPasswordForSignupConfirm=findViewById(R.id.UserPasswordForSignupConfirm)

        sendOTPBtn.setOnClickListener {

            if (personName.text.toString().isEmpty()) {
                personName.setError("Please Enter Your Name")
                personName.requestFocus()
            } else if (EmailET.text.toString().isEmpty()) {
                EmailET.setError("Enter your Number")
                EmailET.requestFocus()
            } else if (UserPasswordForSignup.text.toString().isEmpty()) {
                UserPasswordForSignup.setError("Enter Your Password")
                UserPasswordForSignup.requestFocus()
            } else if (UserPasswordForSignup.text.toString().equals(UserPasswordForSignupConfirm.text.toString())) {
                startActivity(Intent(this, Otp::class.java))
            } else {
                UserPasswordForSignup.setError("Mismatched the Passwords")
                UserPasswordForSignup.requestFocus()
                UserPasswordForSignupConfirm.setError("Mismatched")
                UserPasswordForSignupConfirm.requestFocus()
            }
        }

    }
}