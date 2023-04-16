package com.example.bikenest

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
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
import java.util.jar.Attributes

class HomeFragment : Fragment() {

    lateinit var textview: TextView
    lateinit var textView3: TextView
    val db = Firebase.firestore
    var name: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val offline = view.findViewById<LottieAnimationView>(R.id.offline)

        if (ConnectionManager().checkConnectivity(activity as Context)){
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

        }
    }else{
            val try_again=view.findViewById<Button>(R.id.try_again)
            val parent_of_home=view.findViewById<LinearLayout>(R.id.parent_of_home)
            val offlinetext=view.findViewById<TextView>(R.id.offlinetext)
            parent_of_home.visibility=View.VISIBLE
            try_again.setOnClickListener {
                if (ConnectionManager().checkConnectivity(activity as Context)){
                    parent_of_home.visibility=View.INVISIBLE
                }else{
                    parent_of_home.visibility=View.VISIBLE
                }
            }

        }
    }
}