package com.example.bikenest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.bikenest.databinding.ActivityBikeDetailsBinding
import com.example.bikenest.databinding.ActivityToPushDatasBinding

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
        binding1.wishlist.setOnClickListener {
            Toast.makeText(this, "Added To Wishlist", Toast.LENGTH_SHORT).show()
        }



    }
}