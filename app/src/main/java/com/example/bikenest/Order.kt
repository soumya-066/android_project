package com.example.bikenest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Order : AppCompatActivity() {
    val db=Firebase.firestore
    val orderList = arrayListOf<OrderModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        val OrderRecycle=findViewById<RecyclerView>(R.id.OrderRecycler)
        lateinit var layoutmanager:RecyclerView.LayoutManager



        db.collection("User").document(FirebaseAuth.getInstance().currentUser!!.uid)
            .collection("Orders")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val name = document.getString("OrderedBike") ?: ""
                    val URL = document.getString("Image_Url") ?: ""
                    val pid = document.getString("Payment_id") ?: ""
                    val odate = document.getString("OrderDate") ?: ""
                    val fdate = document.getString("Ordered_For") ?: ""

                    val orders =OrderModel(name,URL,pid,odate,fdate)
                    orderList.add(orders)
                }
                lateinit var Orderadapte:OrderAdapter
                layoutmanager=LinearLayoutManager(this)
                Orderadapte= OrderAdapter(this,orderList)
                OrderRecycle.adapter=Orderadapte
                OrderRecycle.layoutManager=layoutmanager

                OrderRecycle.addItemDecoration(
                    DividerItemDecoration(OrderRecycle.context,
                        (layoutmanager as LinearLayoutManager).orientation
                    )
                )

            }




    }
}