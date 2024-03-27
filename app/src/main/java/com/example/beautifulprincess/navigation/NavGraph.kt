package com.example.beautifulprincess.navigation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.beautifulprincess.views.CatalogScreen
import com.example.beautifulprincess.views.CurrentCardScreen
import com.example.beautifulprincess.views.MainScreen
import com.example.beautifulprincess.views.ProfileScreen
import com.example.beautifulprincess.views.SupportScreen

// Routing in screens
@Composable
fun NavGraph (navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route)
    {
        composable(route = Screens.Home.route){
            MainScreen(navController)
        }
        composable(route = Screens.Catalog.route){
            CatalogScreen(navController)
        }
        composable(route = Screens.Support.route){
            SupportScreen(navController)
        }
        composable(route = Screens.Profile.route){
            ProfileScreen(navController)
        }
        composable(route = Screens.CurrentCard.route){
            CurrentCardScreen(navController)
        }
    }
}
