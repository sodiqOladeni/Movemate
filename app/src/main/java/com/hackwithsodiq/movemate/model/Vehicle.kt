package com.hackwithsodiq.movemate.model

import androidx.annotation.DrawableRes
import com.hackwithsodiq.movemate.R

data class Vehicle(
    val name: String,
    val route: String,
    @DrawableRes val sampleImage: Int,
){
    companion object {
        val samples = listOf(Vehicle("Ocean Freight", "International", R.drawable.ship), Vehicle("Cargo Freight", "Reliable", R.drawable.truck), Vehicle("Air Freight", "International", R.drawable.freight))
    }
}




