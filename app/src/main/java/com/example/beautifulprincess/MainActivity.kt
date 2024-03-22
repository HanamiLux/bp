package com.example.beautifulprincess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.beautifulprincess.ui.theme.BeautifulPrincessTheme
import com.example.beautifulprincess.views.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeautifulPrincessTheme {
                MainScreen()
            }
        }
    }
}