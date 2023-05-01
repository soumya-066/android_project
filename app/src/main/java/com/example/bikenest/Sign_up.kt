package com.example.bikenest

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Sign_up : AppCompatActivity() {
    private lateinit var sendOTPBtn: AppCompatButton
    private lateinit var personName: TextInputEditText
    private lateinit var EmailET: TextInputEditText
    private lateinit var UserPasswordForSignup: TextInputEditText
    private lateinit var UserPasswordForSignupConfirm: TextInputEditText

    //////////////////////////////////////////////
    private lateinit var auth: FirebaseAuth 
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        sendOTPBtn = findViewById(R.id.sendOTPBtn)
        personName = findViewById(R.id.personName)
        EmailET = findViewById(R.id.EmailET)
        UserPasswordForSignup = findViewById(R.id.UserPasswordForSignup)
        UserPasswordForSignupConfirm = findViewById(R.id.UserPasswordForSignupConfirm)
        auth=FirebaseAuth.getInstance()


        sendOTPBtn.setOnClickListener {
            val currentUser = auth.currentUser
            if (personName.text.toString().isEmpty()) {
                personName.setError("Please Enter Your Name")
                personName.requestFocus()
            } else if (EmailET.text.toString().isEmpty()) {
                EmailET.setError("Enter your Email")
                EmailET.requestFocus()
            } else if (UserPasswordForSignup.text.toString().isEmpty()) {
                UserPasswordForSignup.setError("Enter Your Password")
                UserPasswordForSignup.requestFocus()
            } else if (!UserPasswordForSignup.text.toString()
                    .equals(UserPasswordForSignupConfirm.text.toString())
            ) {
                UserPasswordForSignup.requestFocus()
                UserPasswordForSignupConfirm.setError("Password not matched")
                UserPasswordForSignupConfirm.requestFocus()
            } else if (currentUser != null) {
                Toast.makeText(this, "User Already Exists", Toast.LENGTH_SHORT).show()
            }else{
                var email=EmailET.text.toString().trim()
                var password=UserPasswordForSignup.text.toString().trim()

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(
                        OnCompleteListener <AuthResult>{
                                task->
                            //stopProgress()
                            if(task.isSuccessful){
                                val firebaseUser: FirebaseUser =task.result!!.user!!
                                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)


                                val User = hashMapOf(
                                    "Name" to "${personName.text.toString()}",
                                    "Mail" to "$email",
                                    "Password" to "$password"
                                )
                                val document=FirebaseAuth.getInstance().currentUser?.uid

                                db.collection("User").document("${document}")
                                    .set(User)
                                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                                    .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

                                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this,MainActivity::class.java))
                            }
                            else
                            {
                                Toast.makeText(this,task.exception!!.message.toString(),Toast.LENGTH_SHORT).show()
                                
                            }
                        }
                    )
            ///////////////////////////////////////////////////////////////////
            }
        }





    }
}