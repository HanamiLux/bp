package com.example.beautifulprincess.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.beautifulprincess.AppDatabase
import com.example.beautifulprincess.R
import com.example.beautifulprincess.daos.OrdersDAO
import com.example.beautifulprincess.models.BPActivity
import com.example.beautifulprincess.models.Order
import com.example.beautifulprincess.models.Product
import com.example.beautifulprincess.models.ProductRowModel
import com.example.beautifulprincess.navigation.Screens
import com.example.beautifulprincess.ui.theme.Gold
import com.example.beautifulprincess.ui.theme.Pink
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
    val currentUser = firebaseAuth.currentUser?.email
    if (currentUser != null) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.background_sign_in_up),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                contentDescription = "background_profile_activated"
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f)
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
                        fontSize = 16.sp,
                        color = Color.White
                    ),
                    fontFamily = Typography.bodyLarge.fontFamily
                )
                Text(
                    text = "${Firebase.auth.currentUser?.email}",
                    Modifier.offset(y = (-4).dp),
                    fontFamily = Typography.bodyLarge.fontFamily,
                    fontSize = 16.sp,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        drawStyle = Stroke(
                            width = 2f,
                            join = StrokeJoin.Round,
                        )
                    )
                )
            }

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
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Box(Modifier.fillMaxWidth().fillMaxHeight(0.1f), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Cart",
                            Modifier.fillMaxWidth(),
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 48.sp,
                                drawStyle = Stroke(
                                    width = 5f,
                                    join = StrokeJoin.Round,
                                )
                            ),
                            fontFamily = Typography.bodyLarge.fontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 48.sp,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                        )

                        Text(
                            text = "Cart",
                            Modifier.fillMaxWidth(),
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 48.sp,
                            ),
                            fontFamily = Typography.bodyLarge.fontFamily,
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                    }
                }

                val db = AppDatabase.getDbInstance(LocalContext.current)
                val orderInCart = remember{ mutableStateOf(db.ordersDao().getUserOrderedProducts(db.usersDao().getUser(currentUser).id!!.toInt())) }
                val context = LocalContext.current
                LazyColumn(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f),
                    verticalArrangement = Arrangement.spacedBy(10.dp))
                {

                        itemsIndexed(orderInCart.value) { _, order ->
                            val product: Product = db.productsDao().getProductById(order.productId)
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    Modifier
                                        .fillMaxHeight(0.4f)
                                        .fillMaxWidth(0.7f),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    ProductCard(
                                        ProductRowModel(
                                            product.name,
                                            product.price,
                                            imageId = product.image
                                        ),
                                        onClick = {
                                            navController.navigate(
                                                Screens.CurrentCard.route + "/${product.name}/${product.price}/${product.description}/" +
                                                        "${product.image}"
                                            )
                                        }, titleSp = 24.sp, priceSp = 22.sp
                                    )
                                }
                                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    Text(
                                        text = "${order.quantity}",
                                        Modifier.fillMaxWidth(.5f),
                                        style = TextStyle(
                                            color = Color.Black,
                                            fontSize = 64.sp,
                                            drawStyle = Stroke(
                                                width = 5f,
                                                join = StrokeJoin.Round,
                                            )
                                        ),
                                        fontFamily = Typography.bodyLarge.fontFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 64.sp,
                                        textAlign = TextAlign.Center,
                                        maxLines = 2,
                                    )

                                    Text(
                                        text = "${order.quantity}",
                                        Modifier.fillMaxWidth(.5f),
                                        style = TextStyle(
                                            color = Color.White,
                                            fontSize = 64.sp,
                                        ),
                                        fontFamily = Typography.bodyLarge.fontFamily,
                                        textAlign = TextAlign.Center,
                                        maxLines = 2
                                    )
                                }
                            }


                        }

                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f), horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Top){
                    IconButton(modifier = Modifier.fillMaxHeight().fillMaxWidth(0.5f).offset(y = ((-30).dp)), onClick = {
                        db.ordersDao().closeOrder(db.usersDao().getUser(currentUser).id!!.toInt())
                        orderInCart.value = listOf()
                        Toast.makeText(context, "Cart has cleared", Toast.LENGTH_SHORT).show()
                    }) {
                        Image(painter = painterResource(id = R.drawable.clear_button), "Clear",
                            contentScale = ContentScale.Fit)
                    }
                    IconButton(modifier = Modifier.fillMaxSize().offset(y = ((-30).dp)), onClick = { Toast.makeText(context, "Soon!", Toast.LENGTH_SHORT).show() }) {
                        Image(painter = painterResource(id = R.drawable.buy_button), "Buy", contentScale = ContentScale.Fit)
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