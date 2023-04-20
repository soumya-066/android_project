package com.example.bikenest

import android.content.ContentValues.TAG
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BikeList : AppCompatActivity() {

    val db = Firebase.firestore
    val dataList = arrayListOf<BikeModel>()

    lateinit var Bikerecycle: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bike_list)
        var tabLayout = findViewById<androidx.appcompat.widget.Toolbar>(R.id.tabLayout)
        setSupportActionBar(tabLayout)
        supportActionBar?.title = "Bikes"


        var brandvalue = intent.getStringExtra("brand").toString()


        Bikerecycle = findViewById(R.id.Bikerecycle)
        lateinit var layoutManager: RecyclerView.LayoutManager

        db.collection("Bikes")
            .whereEqualTo("Brand", brandvalue)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val name = document.getString("Name") ?: ""
                    val Brand = document.getString("Brand") ?: ""
                    val price = document.getString("Price") ?: ""
                    val engine = document.getString("Engine") ?: ""
                    val description = document.getString("Details") ?: ""
                    val mileage = document.getString("Mileage") ?: ""
                    val Power = document.getString("Power") ?: ""
                    val imageUrl = document.getString("Imageurl") ?: ""
                    val myData =
                        BikeModel(name, Brand, price, engine, description, mileage, Power, imageUrl)
                    dataList.add(myData)
                }
                lateinit var bikerecycleradapter: MyAdapter
                layoutManager = LinearLayoutManager(this)
                bikerecycleradapter = MyAdapter(this, dataList)
                Bikerecycle.adapter = bikerecycleradapter
                Bikerecycle.layoutManager = layoutManager

//                Bikerecycle.addItemDecoration(
//                    DividerItemDecoration(Bikerecycle.context,
//                        (layoutManager as LinearLayoutManager).orientation
//                    )
//                )
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error getting documents: ", exception)
            }


    }
}