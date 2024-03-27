package com.example.beautifulprincess.views

import android.graphics.Path.Direction
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.beautifulprincess.R
import com.example.beautifulprincess.models.BPActivity
import com.example.beautifulprincess.ui.theme.Gold
import com.example.beautifulprincess.ui.theme.Typography

// Profile screen
@Composable
fun ProfileScreen(navController:NavController) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.background_profile),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            contentDescription = "background_profile"
        )

        Column {
            Box {
                Text(
                    text = "You don't have an account yet",
                    style = TextStyle(
                        fontSize = 40.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    ),
                    fontFamily = Typography.bodyLarge.fontFamily,
                )
                Text(
                    text = "You don't have an account yet",
                    fontFamily = Typography.bodyLarge.fontFamily,
                    fontSize = 40.sp,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 40.sp,
                        drawStyle = Stroke(
                            width = 3f,
                            join = StrokeJoin.Round
                        ),
                        textAlign = TextAlign.Center
                    )
                )
            }
            // Space between two elements
            // Two buttons - Sign up and sign in
            Spacer(modifier = Modifier.height(16.dp))
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(40.6.dp)
                    .width(180.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(painter = painterResource(id = R.drawable.sign_up_button), "Sign up")
            }
            Spacer(modifier = Modifier.height(16.dp))
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(40.6.dp)
                    .width(180.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(painter = painterResource(id = R.drawable.sign_in_button), "Sign in")
            }
        }
    }
    BottomNavBar(BPActivity.Profile.drawableId, navController = navController)
}