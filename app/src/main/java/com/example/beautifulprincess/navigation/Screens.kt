package com.example.beautifulprincess.navigation

// Screens class
sealed class Screens(val route: String) {
    object Home: Screens("home_screen")
    object Catalog: Screens("catalog_screen")
    object Support: Screens("support_screen")
    object Profile: Screens("profile_screen")
    object CurrentCard: Screens("current_card_screen")
}