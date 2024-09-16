package com.hackwithsodiq.movemate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hackwithsodiq.movemate.navigation.MovemateBottomBar
import com.hackwithsodiq.movemate.navigation.Route
import com.hackwithsodiq.movemate.navigation.addHomeGraph
import com.hackwithsodiq.movemate.ui.theme.MovemateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MovemateTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier
                    .fillMaxSize(),
                    bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        AnimatedVisibility(currentDestination?.route == Route.HOME.route) {
                            MovemateBottomBar(navController, currentDestination)
                        }
                    }) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Route.HOME.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        addHomeGraph(navController)
                    }
                }
            }
        }
    }
}