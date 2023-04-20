package com.example.bikenest

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(val context: Context,private val dataList: List<BikeModel>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val ToDetails: ImageView = itemView.findViewById(R.id.ToDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.nameTextView.text = currentItem.name
        Glide.with(holder.itemView.context)
            .load(currentItem.imageurl)
            .into(holder.imageView)
        holder.ToDetails.setOnClickListener {
            val intent=Intent(context,Bike_Details::class.java)
            intent.putExtra("Details","${currentItem.description}")
            intent.putExtra("Engine","${currentItem.engine}")
            intent.putExtra("Mileage","${currentItem.mileage}")
            intent.putExtra("Power","${currentItem.power}")
            intent.putExtra("URL","${currentItem.imageurl}")
            intent.putExtra("Model","${currentItem.name}")
            intent.putExtra("Brand","${currentItem.Brand}")
            intent.putExtra("Price","${currentItem.Price}")
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataList.size
}
