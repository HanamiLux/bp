package com.example.beautifulprincess.views

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.beautifulprincess.R
import com.example.beautifulprincess.models.BPActivity
import com.example.beautifulprincess.ui.theme.Pink
import com.example.beautifulprincess.ui.theme.Typography
import org.w3c.dom.Text

// Current card from catalog screen
@Composable
fun CurrentCardScreen(navController:NavController) {
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
            Box {
                Image(
                    painter = painterResource(id = R.drawable.pomade_text),
                    contentDescription = "pomade text",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize(0.05f),
                    alignment = Alignment.Center
                )
            }
        // Product description with image, price and description as scroll letter
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.image_product),
                        contentDescription = "product",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxSize(0.35f)
                    )
                }
                Box {
                    Text(
                        text = "5.99€",
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
                        text = "5.99€",
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
                                text = "DES\nCRIPTION DESC DKFLJLKD\nGFLKDGKLSD\nFLFDKGLF\nSLKGFLKDGJLDF\nАЛДВАЫЛДВА\nЛЫДВАЛДЫ\nВАЛВЫА\nвлады\nвдавыдаьбсчм",
                                fontSize = 20.sp,
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.End)
                        .offset(y = (-15).dp)
                        .padding(end = 15.dp)
                ) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxSize(.5f)
                    ) {
                        Image(painter = painterResource(id = R.drawable.buy_button), "Buy")
                    }
                }
            }
        }
    }
    BottomNavBar(BPActivity.Catalog.drawableId, navController = navController)
}