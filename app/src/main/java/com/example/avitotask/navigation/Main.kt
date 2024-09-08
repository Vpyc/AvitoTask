package com.example.avitotask.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.avitotask.views.AuthView
import com.example.avitotask.views.RegistrationView

@Composable
fun Main() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Auth.route
    ) {
        composable(NavRoutes.Register.route) {
            RegistrationView(navController)
        }
        composable(NavRoutes.Auth.route) {
            AuthView(navController)
        }
    }
}