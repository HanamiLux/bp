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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.beautifulprincess.views.CatalogScreen
import com.example.beautifulprincess.views.CurrentCardScreen
import com.example.beautifulprincess.views.MainScreen
import com.example.beautifulprincess.views.ProfileScreen
import com.example.beautifulprincess.views.SignInScreen
import com.example.beautifulprincess.views.SignUpScreen
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
        composable(route = Screens.CurrentCard.route+"/{name}/{price}/{description}/{image}",
            arguments = listOf(
                navArgument(name = "name"){
                    type = NavType.StringType
                },
                navArgument(name = "price"){
                    type = NavType.FloatType
                },
                navArgument(name = "description"){
                    type = NavType.StringType
                },
                navArgument(name = "image"){
                    type = NavType.IntType
                },
            )){
            CurrentCardScreen(navController,
                name = it.arguments?.getString("name")!!,
                price = it.arguments?.getFloat("price")!!,
                description = it.arguments?.getString("description")!!,
                image = it.arguments?.getInt("image")!!
            )
        }
        composable(route = Screens.SignUp.route){
            SignUpScreen(navController)
        }
        composable(route = Screens.SignIn.route){
            SignInScreen(navController)
        }
    }
}
