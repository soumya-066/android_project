package com.example.bikenest

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.example.bikenest.databinding.ActivityBikeDetailsBinding
import com.example.bikenest.databinding.ActivityToPushDatasBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Bike_Details : AppCompatActivity() {

    private lateinit var binding1: ActivityBikeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding1= DataBindingUtil.
        setContentView(this,R.layout.activity_bike_details)
        val detailsvalue=intent.getStringExtra("Details")
        val enginevalue=intent.getStringExtra("Engine")
        val mileagevalue=intent.getStringExtra("Mileage")
        val powervalue=intent.getStringExtra("Power")
        val urlvalue=intent.getStringExtra("URL")
        val modelvalue=intent.getStringExtra("Model")
        val brandvalue=intent.getStringExtra("Brand")
        val pricevalue=intent.getStringExtra("Price")

        binding1.desctv.text=detailsvalue
        binding1.enginetv.text=enginevalue
        binding1.mileageTv.text=mileagevalue
        binding1.powertv.text=powervalue
        binding1.brandnametv.text=brandvalue
        binding1.modelnametv.text=modelvalue
        Glide.with(this)
            .load("$urlvalue")
            .into(binding1.imageView7)
        binding1.backbtn.setOnClickListener {
            finish()
        }

        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("User").document(FirebaseAuth.getInstance().currentUser!!.uid)
                .collection("Favourites").document("$modelvalue")


        docRef.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                binding1.changewish.setImageResource(R.drawable.baseline_favorite_24)
            }
        }


        binding1.wishlist.setOnClickListener {
            docRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {

                    binding1.Detailsparent.isEnabled=false
                    Toast.makeText(this, "Already Added", Toast.LENGTH_SHORT).show()

                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.Detailsparent,FavFragment())
                    transaction.commit()

                } else {
                    val Favmodel = hashMapOf(
                        "Name" to modelvalue,
                        "Brand" to brandvalue,
                        "Price" to pricevalue,
                        "Engine" to enginevalue,
                        "Details" to detailsvalue,
                        "Mileage" to mileagevalue,
                        "Power" to powervalue,
                        "Imageurl" to urlvalue,
                        "Added" to 1
                    )

                    Firebase.firestore.collection("User").document(FirebaseAuth.getInstance().currentUser!!.uid)
                        .collection("Favourites").document("$modelvalue")
                        .set(Favmodel, SetOptions.merge())
                        .addOnSuccessListener {
                            Log.d(
                                ContentValues.TAG,
                                "DocumentSnapshot successfully written!"
                            )
                            binding1.changewish.setImageResource(R.drawable.baseline_favorite_24)
                            Toast.makeText(this, "Added To Favourite", Toast.LENGTH_SHORT).show()

                        }

                }
            }.addOnFailureListener { e ->


            }

        }



    }
}