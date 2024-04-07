package com.example.beautifulprincess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.beautifulprincess.navigation.NavGraph
import com.example.beautifulprincess.ui.theme.BeautifulPrincessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            BeautifulPrincessTheme {
                NavGraph(navController = navController)
            }
        }
    }
}