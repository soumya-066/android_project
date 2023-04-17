package com.example.bikenest

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.bikenest.databinding.ActivityToPushDatasBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class To_Push_Datas : AppCompatActivity() {
    private lateinit var binding:ActivityToPushDatasBinding
   // lateinit var model:BikeModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.
        setContentView(this,R.layout.activity_to_push_datas)
        val db = FirebaseFirestore.getInstance()

        binding.submit.setOnClickListener {

            if(binding.name.text.isEmpty()||
            binding.brand.text.isEmpty()||
            binding.price.text.toString().isEmpty()||
            binding.engine.text.toString().isEmpty()||
            binding.details.text.toString().isEmpty()||
            binding.mileage.text.toString().isEmpty()||
            binding.power.text.toString().isEmpty()) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()

            }

            else {


                val model = hashMapOf(
                    "Name" to binding.name.text.toString(),
                    "Brand" to binding.brand.text.toString(),
                    "Price" to binding.price.text.toString(),
                    "Engine" to binding.engine.text.toString(),
                    "Details" to binding.details.text.toString(),
                    "Mileage" to binding.mileage.text.toString(),
                    "Power" to binding.power.text.toString(),
                    "Imageurl" to "",
                    "numbers_available" to 2

                )


                db.collection("Bikes").document("${binding.model.text}")
                    .set(model, SetOptions.merge())
                    .addOnSuccessListener {
                        Log.d(
                            ContentValues.TAG,
                            "DocumentSnapshot successfully written!"
                        )
                    }
                    .addOnFailureListener { e ->
                        Log.w(
                            ContentValues.TAG,
                            "Error writing document",
                            e
                        )
                    }
 startActivity(Intent(this,To_Push_Datas::class.java))

            }



        }
    }
}