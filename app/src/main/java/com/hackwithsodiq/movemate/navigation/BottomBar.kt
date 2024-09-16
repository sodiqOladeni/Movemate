package com.hackwithsodiq.movemate.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.hackwithsodiq.movemate.ui.theme.GrayTextColor

@Composable
fun MovemateBottomBar(
    navController: NavController,
    currentDestination: NavDestination?
){
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = Color.Red,
    ) {
        Route.entries.toTypedArray().forEach { route ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        route.icon,
                        contentDescription = route.name
                    )
                },
                label = { Text(stringResource(route.title)) },
                selected = currentDestination?.route == route.route,
                onClick = {
                    navController.navigate(route.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = GrayTextColor
            )
        }
    }
}