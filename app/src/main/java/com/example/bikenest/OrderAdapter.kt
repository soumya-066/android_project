package com.example.bikenest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class OrderAdapter(val context: Context, private val dataList: List<OrderModel>): RecyclerView.Adapter<OrderAdapter.MyOrderViewHolder>() {

    inner class MyOrderViewHolder(itemViewo: View) : RecyclerView.ViewHolder(itemViewo) {

        val orderimage: ImageView= itemViewo.findViewById(R.id.imageView8)
        val bikename: TextView = itemViewo.findViewById(R.id.textView10)
        val orderdate: TextView = itemViewo.findViewById(R.id.textView8)
        val paymentid: TextView = itemViewo.findViewById(R.id.paymentid)
        val orderfor: TextView = itemViewo.findViewById(R.id.orderfor)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOrderViewHolder {
        val itemViewo = LayoutInflater.from(parent.context)
            .inflate(R.layout.ordersinglerow, parent, false)
        return MyOrderViewHolder(itemViewo)
    }

    override fun onBindViewHolder(holder: MyOrderViewHolder, position: Int) {
        val currentoItem = dataList[position]
        holder.bikename.text = currentoItem.oname
        holder.orderdate.text = currentoItem.date
        holder.orderfor.text = currentoItem.ford
        holder.paymentid.text = currentoItem.PaymentId
        Glide.with(holder.itemView.context)
            .load(currentoItem.oimage)
            .into(holder.orderimage)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}