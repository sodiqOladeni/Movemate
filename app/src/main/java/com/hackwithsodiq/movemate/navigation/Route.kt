package com.hackwithsodiq.movemate.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.hackwithsodiq.movemate.R

enum class Route(@StringRes val title: Int, val route: String, val icon: ImageVector) {
    HOME(R.string.home, "home", Icons.Outlined.Home),
    CALCULATE(R.string.calculate, "calculate", Icons.Outlined.Calculate),
    SHIPMENT(R.string.shipment, "shipment", Icons.Outlined.History),
    PROFILE(R.string.profile, "profile", Icons.Outlined.Person)
}
val CALCULATE_ESTIMATED_ROUTE = "calculate/estimated"