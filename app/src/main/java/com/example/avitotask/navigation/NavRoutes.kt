package com.example.avitotask.navigation

sealed class NavRoutes(val route: String) {
    data object Register : NavRoutes("register")
    data object Auth : NavRoutes("auth")
    data object Home : NavRoutes("home")
    data object ProductDetail : NavRoutes("productDetail/{productId}") {
        fun createRoute(productId: String) = "productDetail/$productId"
    }
}