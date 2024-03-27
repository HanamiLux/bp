package com.example.beautifulprincess.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.beautifulprincess.R
import com.example.beautifulprincess.models.BPActivity
import com.example.beautifulprincess.navigation.Screens
import com.example.beautifulprincess.ui.theme.LightPink

@Composable
fun BottomNavBar(currentActivity: Int = BPActivity.Home.drawableId, navController: NavController) {
    val icons by remember{
        mutableStateOf(
            arrayOf(R.drawable.home,
                R.drawable.catalog,
                R.drawable.support,
                R.drawable.profile)
        )
    }
    when(currentActivity){
        BPActivity.Home.drawableId -> icons[0] = R.drawable.home_selected
        BPActivity.Catalog.drawableId -> icons[1] = R.drawable.catalog_selected
        BPActivity.Support.drawableId -> icons[2] = R.drawable.support_selected
        BPActivity.Profile.drawableId -> icons[3] = R.drawable.profile_selected
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
       Box(
           Modifier
               .fillMaxWidth()
               .background(LightPink), contentAlignment = Alignment.Center
        ) {
           Row(
               Modifier
                   .offset(y = (-20).dp)
                   .scale(2.1f)) {
               IconButton(onClick = {
                   // Navigate to screen
                   if (currentRoute != Screens.Home.route) {
                       navController.navigate(Screens.Home.route)
                   }
               }) {
                   Image(
                       painter = painterResource(id = icons[0]),
                       "Home",
                   )
               }
               IconButton(onClick = {
                   if (currentRoute != Screens.Catalog.route) {
                       navController.navigate(Screens.Catalog.route)
                   }
               }) {
                   Image(painter = painterResource(id = icons[1]),
                       "Catalog")
               }
               IconButton(onClick = {
                   if (currentRoute != Screens.Support.route) {
                       navController.navigate(Screens.Support.route)
                   }
               }) {
                   Image(painter = painterResource(id = icons[2]),
                       "Support")
               }
               IconButton(onClick = {
                   if (currentRoute != Screens.Profile.route) {
                       navController.navigate(Screens.Profile.route)
                   }
               }) {
                   Image(painter = painterResource(id = icons[3]),
                       "Profile")
               }
           }
        }
    }
}
