package com.example.beautifulprincess.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.beautifulprincess.R
import com.example.beautifulprincess.models.BPActivity
import com.example.beautifulprincess.navigation.Screens
import com.example.beautifulprincess.ui.theme.Gold
import com.example.beautifulprincess.ui.theme.Typography
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

// Profile screen
@Composable
fun ProfileScreen(navController:NavController) {
    val firebaseAuth = FirebaseAuth.getInstance()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (firebaseAuth.currentUser?.email != null) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.background_sign_in_up),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                contentDescription = "background_profile_activated"
            )

            Column {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxSize(0.15f), horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = {
                            FirebaseAuth.getInstance().signOut()
                            if (currentRoute != Screens.SignUp.route) {
                                navController.navigate(Screens.SignUp.route)
                            }
                        }
                    ) {
                        Image(painter = painterResource(id = R.drawable.exit_button_icon), "Exit")
                    }
                }

                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.text_input),
                            modifier = Modifier.fillMaxWidth(.8f),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = "text input background"
                        )
                        Text(
                            text = "${Firebase.auth.currentUser?.email}",
                            Modifier.offset(y = (-4).dp),
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = Color.White
                            ),
                            fontFamily = Typography.bodyLarge.fontFamily
                        )
                        Text(
                            text = "${Firebase.auth.currentUser?.email}",
                            Modifier.offset(y = (-4).dp),
                            fontFamily = Typography.bodyLarge.fontFamily,
                            fontSize = 20.sp,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 20.sp,
                                drawStyle = Stroke(
                                    width = 2f,
                                    join = StrokeJoin.Round,
                                )
                            )
                        )
                    }
                }
            }
        }
    } else{
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
                Box(contentAlignment = Alignment.Center) {
                    IconButton(
                        onClick = {
                            if (currentRoute != Screens.SignUp.route) {
                                navController.navigate(Screens.SignUp.route)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Image(painter = painterResource(id = R.drawable.sign_up_button), "Sign up")
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                Box(contentAlignment = Alignment.Center) {
                    IconButton(
                        onClick = {
                            if (currentRoute != Screens.SignIn.route) {
                                navController.navigate(Screens.SignIn.route)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Image(painter = painterResource(id = R.drawable.sign_in_button), "Sign in")
                    }
                }
            }
        }
    }
    BottomNavBar(BPActivity.Profile.drawableId, navController = navController)
}