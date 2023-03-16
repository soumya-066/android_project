package com.example.bikenest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    lateinit var UserPhoneNumber:TextInputEditText
    lateinit var PasswordForLogin:TextInputEditText
    lateinit var buttonForLogin:AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val Sign_Up_textview=findViewById<TextView>(R.id.Sign_Up_textview)
        Sign_Up_textview.setOnClickListener {
            startActivity(Intent(this,Sign_up::class.java))
        }
        UserPhoneNumber=findViewById(R.id.UserPhoneNumber)
        PasswordForLogin=findViewById(R.id.PasswordForLogin)
        buttonForLogin=findViewById(R.id.buttonForLogin)


        buttonForLogin.setOnClickListener {
            val mail=UserPhoneNumber.text.toString().trim(){it<=' '}
            val password=PasswordForLogin.text.toString().trim(){it<=' '}
            if (UserPhoneNumber.text.toString().isNotEmpty()){
                if (PasswordForLogin.text.toString().isNotEmpty()){
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(mail,password)
                        .addOnCompleteListener(
                            OnCompleteListener <AuthResult>{
                                    task->
                                if(task.isSuccessful){
                                    //val firebaseUser: FirebaseUser =task.result!!.user!!
                                    //Toast.makeText(this,"Registered",Toast.LENGTH_SHORT).show()
                                    val iLogin= Intent(this, MainActivity::class.java)
                                    iLogin.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    startActivity(iLogin)
                                }
                                else
                                {
                                    Toast.makeText(this,task.exception!!.message.toString(),Toast.LENGTH_SHORT).show()
                                }
                            }
                        )

                }
            }
        }









    }
}