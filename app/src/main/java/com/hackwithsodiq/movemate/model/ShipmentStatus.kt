package com.hackwithsodiq.movemate.model

sealed class ShipmentStatus(val status: String) {
    data object InProgress: ShipmentStatus("in-progress")
    data object Completed: ShipmentStatus("completed")
    data object Pending: ShipmentStatus("pending")
    data object Loading: ShipmentStatus("loading")
    data object Cancelled: ShipmentStatus("cancelled")
}