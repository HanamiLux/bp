package com.example.beautifulprincess.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.beautifulprincess.AppDatabase
import com.example.beautifulprincess.R
import com.example.beautifulprincess.models.BPActivity
import com.example.beautifulprincess.models.Order
import com.example.beautifulprincess.navigation.Screens
import com.example.beautifulprincess.ui.theme.Pink
import com.example.beautifulprincess.ui.theme.PurpleTEXT
import com.example.beautifulprincess.ui.theme.Typography
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

// Current card from catalog screen
@Composable
fun CurrentCardScreen(navController:NavController, name: String, price: Float, description: String, image: Int) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(Modifier.fillMaxWidth().fillMaxSize(0.1f),
                contentAlignment = Alignment.Center) {
                Text(
                    text = name,
                    style = TextStyle(
                        fontSize = 35.sp,
                        color = PurpleTEXT
                    ),
                    fontFamily = Typography.bodyLarge.fontFamily,
                )
            }
        // Product description with image, price and description as scroll letter
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = "product",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxSize(0.35f)
                    )
                }
                Box {
                    Text(
                        text = "${price}€",
                        fontFamily = Typography.bodyLarge.fontFamily,
                        fontSize = 40.sp,
                        maxLines = 1,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 40.sp,
                            drawStyle = Stroke(
                                width = 5f,
                                join = StrokeJoin.Round,
                            )
                        )
                    )
                    Text(
                        text = "${price}€",
                        style = TextStyle(
                            color = Pink,
                            fontSize = 40.sp,
                        ),
                        fontFamily = Typography.bodyLarge.fontFamily
                    )
                }
            }
            Column(Modifier.fillMaxWidth()) {
                Box(contentAlignment = Alignment.Center) {
                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.scroll_description),
                            contentDescription = "scroll description",
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.7f)
                        )
                    }
                    Box(Modifier.fillMaxWidth(0.65f)
                        .fillMaxHeight(.56f).padding(0.dp, 17.dp, 0.dp, 0.dp)) {
                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState()),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = description,
                                fontSize = 20.sp,
                            )
                        }
                    }
                }
                val context = LocalContext.current
                Box(
                    modifier = Modifier
                        .offset(y = (-50).dp)
                ) {
                    IconButton(
                        onClick = { // Quantity check
                            val db = AppDatabase.getDbInstance(context)
                            val currentUser = FirebaseAuth.getInstance().currentUser?.email

                            if(currentUser == null){
                                navController.navigate(Screens.Profile.route)
                                return@IconButton
                            }
                            val userId = db.usersDao().getUser(currentUser).id!!.toInt()
                            val ordersDao = db.ordersDao().getUserOrderedProducts(userId)
                            val productId = db.productsDao().getProductsByName(name)[0].id
                            if (ordersDao.isEmpty()){
                                db.ordersDao().insertOrder(Order(null,
                                    productId,
                                    userId,1))
                            }else {
                                db.ordersDao().insertOrder(Order(null, productId, userId, 1))
                            }
                            Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Image(painter = painterResource(id = R.drawable.buy_button), "Buy")
                    }
                }
            }
        }
    }
    BottomNavBar(BPActivity.Catalog.drawableId, navController = navController)
}