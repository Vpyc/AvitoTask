package com.example.avitotask.navigation

sealed class NavRoutes(val route: String) {
    object Register : NavRoutes("register")
    object Auth : NavRoutes("auth")
}