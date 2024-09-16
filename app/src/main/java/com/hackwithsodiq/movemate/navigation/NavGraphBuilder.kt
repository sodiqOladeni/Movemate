package com.hackwithsodiq.movemate.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hackwithsodiq.movemate.ui.CalculateScreen
import com.hackwithsodiq.movemate.ui.EstimatedCalculation
import com.hackwithsodiq.movemate.ui.HomeScreen
import com.hackwithsodiq.movemate.ui.ProfileScreen
import com.hackwithsodiq.movemate.ui.ShipmentScreen


fun NavGraphBuilder.addHomeGraph(navController: NavController) {
    composable(Route.HOME.route) {
        HomeScreen()
    }
    composable(Route.CALCULATE.route) {
        CalculateScreen(navController)
    }
    composable(Route.SHIPMENT.route) {
        ShipmentScreen(navController)
    }
    composable(Route.PROFILE.route) {
        ProfileScreen(navController)
    }
    composable(CALCULATE_ESTIMATED_ROUTE) {
        EstimatedCalculation(navController)
    }
}