package com.example.avitotask.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.avitotask.viewModels.NavigationViewModel
import com.example.avitotask.views.AuthView
import com.example.avitotask.views.HomeView
import com.example.avitotask.views.RegistrationView

@Composable
fun Main() {
    val navController = rememberNavController()
    val navViewModel: NavigationViewModel = hiltViewModel()
/*    LaunchedEffect(navViewModel.getToken()) {
        if (navViewModel.getToken() != null) {
            navController.navigate(NavRoutes.Auth.route) {
                popUpTo(0)
            }
        } else {
            navController.navigate(NavRoutes.Register.route) {
                popUpTo(0)
            }
        }
    }*/
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route
    ) {
        composable(NavRoutes.Register.route) {
            RegistrationView(navController)
        }
        composable(NavRoutes.Auth.route) {
            AuthView(navController)
        }
        composable(NavRoutes.Home.route) {
            HomeView()
        }
    }
}