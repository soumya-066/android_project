package com.example.bikenest

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import util.ConnectionManager
import java.util.Locale
import java.util.jar.Attributes

class HomeFragment : Fragment() {

    lateinit var textview: TextView
    lateinit var textView3: TextView
    lateinit var searchView:SearchView
    lateinit var Honda:ImageView
    val db = Firebase.firestore
    var name: String? = ""
    var submitsearch: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Honda=view.findViewById(R.id.Honda)
        var Hero=view.findViewById<ImageView>(R.id.Hero)
        var Royalenfield=view.findViewById<ImageView>(R.id.Royalenfield)
        var Bajaj=view.findViewById<ImageView>(R.id.Bajaj)
        var KTM=view.findViewById<ImageView>(R.id.KTM)
        var BMW=view.findViewById<ImageView>(R.id.BMW)
        searchView=view.findViewById(R.id.searchView)

            textview = view.findViewById(R.id.textView)
        var use = FirebaseAuth.getInstance().currentUser?.uid
        val docRef = FirebaseFirestore.getInstance().collection("User")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)
        docRef.get().addOnSuccessListener {
            //makeText(activity, "$it", Toast.LENGTH_LONG).show()
            Log.i("fetch", "${it.getString("Name")}")
            if (it.exists()) {
                name = it.getString("Name")

            }

            textView3 = view.findViewById(R.id.textView3)
            textview.setText("Hello $name !!")
            textView3.setText("Choose Your Ride")
            //textview.text = name
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    BikeList().filtering(newText!!)

                    return false
                }
            })



        }
        Honda.setOnClickListener {
            var a=Intent(context,BikeList::class.java)
            a.putExtra("brand","Honda")
            startActivity(a)
        }
        Hero.setOnClickListener {
            var a=Intent(context,BikeList::class.java)
            a.putExtra("brand","Hero")
            startActivity(a)
        }
        Royalenfield.setOnClickListener {
            var a=Intent(context,BikeList::class.java)
            a.putExtra("brand","Royal Enfield")
            startActivity(a)
        }
        Bajaj.setOnClickListener {
            var a=Intent(context,BikeList::class.java)
            a.putExtra("brand","Bajaj")
            startActivity(a)
        }
        KTM.setOnClickListener {
            var a=Intent(context,BikeList::class.java)
            a.putExtra("brand","KTM")
            startActivity(a)
        }
        BMW.setOnClickListener {
            var a=Intent(context,BikeList::class.java)
            a.putExtra("brand","BMW")
            startActivity(a)
        }




    }
}