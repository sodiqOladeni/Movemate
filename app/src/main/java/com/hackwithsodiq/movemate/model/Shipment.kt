package com.hackwithsodiq.movemate.model

import androidx.annotation.DrawableRes
import com.hackwithsodiq.movemate.R

data class Shipment(
    val status: ShipmentStatus,
    val arrivingAlert: String,
    val deliveryStatement: String,
    val amount: String,
    val date: String,
    @DrawableRes val sampleImage: Int,
) {
    companion object {
        val samples = listOf(
            Shipment(
                ShipmentStatus.InProgress,
                "Arriving Today!",
                "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
                "$1400 USD",
                date = "Sep 20, 2023",
                R.drawable.box
            ),
            Shipment(
                ShipmentStatus.Pending,
                "Arriving Today!",
                "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
                "$650",
                date = "Sep 20, 2023",
                R.drawable.box
            ),
            Shipment(
                ShipmentStatus.Pending,
                "Arriving Today!",
                "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
                "$650",
                date = "Sep 20, 2023",
                R.drawable.box
            ),
            Shipment(
                ShipmentStatus.Completed,
                "Arrived Today!",
                "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
                "$750",
                date = "Sep 20, 2023",
                R.drawable.box
            ),
            Shipment(
                ShipmentStatus.InProgress,
                "Arriving Today!",
                "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
                "$1400 USD",
                date = "Sep 20, 2023",
                R.drawable.box
            ),
            Shipment(
                ShipmentStatus.Completed,
                "Arrived Today!",
                "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
                "$650",
                date = "Sep 20, 2023",
                R.drawable.box
            ),
            Shipment(
                ShipmentStatus.Completed,
                "Arrived Today!",
                "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
                "$650",
                date = "Sep 20, 2023",
                R.drawable.box
            ),
            Shipment(
                ShipmentStatus.Loading,
                "Arriving Today!",
                "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
                "$750",
                date = "Sep 20, 2023",
                R.drawable.box
            ),
            Shipment(
                ShipmentStatus.InProgress,
                "Arriving Today!",
                "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
                "$1400 USD",
                date = "Sep 20, 2023",
                R.drawable.box
            ),
            Shipment(
                ShipmentStatus.Pending,
                "Arriving Today!",
                "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
                "$650",
                date = "Sep 20, 2023",
                R.drawable.box
            ),
            Shipment(
                ShipmentStatus.Pending,
                "Arriving Today!",
                "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
                "$650",
                date = "Sep 20, 2023",
                R.drawable.box
            ),
            Shipment(
                ShipmentStatus.Loading,
                "Arriving Today!",
                "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
                "$750",
                date = "Sep 20, 2023",
                R.drawable.box
            ),
        )

        fun filterHistoryStatusSizeWithIndex(index: Int): Int{
            return when(index){
                0 -> samples.size
                1 -> samples.filter { it.status == ShipmentStatus.Completed }.size
                2 -> samples.filter { it.status == ShipmentStatus.InProgress }.size
                3 -> samples.filter { it.status == ShipmentStatus.Pending }.size
                4 -> samples.filter { it.status == ShipmentStatus.Loading }.size
                5 -> samples.filter { it.status == ShipmentStatus.Cancelled }.size
                else -> 0
            }
        }

        fun filterWithStatus(index: Int): List<Shipment> {
            return when(index){
                1 -> samples.filter { it.status == ShipmentStatus.Completed }
                2 -> samples.filter { it.status == ShipmentStatus.InProgress }
                3 -> samples.filter { it.status == ShipmentStatus.Pending }
                4 -> samples.filter { it.status == ShipmentStatus.Loading }
                5 -> samples.filter { it.status == ShipmentStatus.Cancelled }
                else -> samples
            }
        }
    }
}




