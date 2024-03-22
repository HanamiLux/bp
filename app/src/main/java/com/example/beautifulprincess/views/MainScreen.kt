package com.example.beautifulprincess.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.beautifulprincess.R
import com.example.beautifulprincess.models.BPActivity
import com.example.beautifulprincess.models.ProductRowModel
import com.example.beautifulprincess.ui.theme.Pink
import com.example.beautifulprincess.ui.theme.Typography

@Composable
fun MainScreen() {
    //Header
    Image(
        painter = painterResource(id = R.drawable.header_main),
        contentDescription = "header",
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
            .offset(y = (-15).dp),
        contentScale = ContentScale.FillBounds
    )
    //Main container
    Column(Modifier.fillMaxSize()) {
        //Header search bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f),
            contentAlignment = Alignment.Center
        ) {

            var text by remember { mutableStateOf("") }
            TextField(
                value = text,
                onValueChange = { text = it },
                Modifier
                    .clip(RoundedCornerShape(40.dp))
                    .size(295.dp, 43.dp),
                maxLines = 1,
                textStyle = TextStyle(fontSize = 16.sp, fontFamily = FontFamily.SansSerif)

            )
        }
        //Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .padding(top = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.banner),
                contentDescription = "event banner",
                Modifier.fillMaxSize(0.85f)
            )
        }

        //Text "TOP"
        Image(
            painter = painterResource(id = R.drawable.top),
            contentDescription = "top",
            Modifier.fillMaxWidth(),
            alignment = Alignment.Center
        )
        //Products horizontal list
        LazyRow(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)) {
            itemsIndexed(
                listOf(
                    ProductRowModel("Pomade", 20.50, R.drawable.image_product),
                    ProductRowModel("Pomade", 20.50, R.drawable.image_product),
                    ProductRowModel("Pomade", 20.50, R.drawable.image_product),
                    ProductRowModel("Pomade", 20.50, R.drawable.image_product),
                    ProductRowModel("Pomade", 20.50, R.drawable.image_product),
                    ProductRowModel("Pomade", 20.50, R.drawable.image_product)
                )
            ) { _, product ->
                ProductCard(product)
            }

        }
        //Text "Discounts"
        Image(
            painter = painterResource(id = R.drawable.discounts),
            contentDescription = "discounts",
            Modifier.fillMaxWidth(),
            alignment = Alignment.Center
        )
        //Products horizontal list
        LazyRow (
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)){
            itemsIndexed(
                listOf(
                    ProductRowModel("Pomade", 20.50, R.drawable.image_product),
                    ProductRowModel("Pomade", 20.50, R.drawable.image_product),
                    ProductRowModel("Pomade", 20.50, R.drawable.image_product),
                    ProductRowModel("Pomade", 20.50, R.drawable.image_product),
                    ProductRowModel("Pomade", 20.50, R.drawable.image_product),
                    ProductRowModel("Pomade", 20.50, R.drawable.image_product)
                )
            ){
                _, product ->
                ProductCard(product)
            }
        }
        //NAVBAR
        BottomNavBar(BPActivity.Home.drawableId)
    }
}


//
@Composable
private fun ProductCard(
    product: ProductRowModel = ProductRowModel(
        "Pomade",
        20.50,
        R.drawable.image_product,
    ), titleSp: TextUnit = 12.sp, priceSp: TextUnit = 10.sp
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.scroll_product),
            contentDescription = "product_background",
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentScale = ContentScale.Crop
        )
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_product),
                    contentDescription = product.title,
                    Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            //Product title text
            Box{
                Text(
                    text = product.title,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 12.sp,
                        drawStyle = Stroke(
                            width = 5f,
                            join = StrokeJoin.Round,
                        )
                    ),
                    fontFamily = Typography.bodyLarge.fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = titleSp,
                    maxLines = 1

                )

                Text(
                    text = product.title,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 12.sp,
                    ),
                    fontFamily = Typography.bodyLarge.fontFamily
                )
            }


            //Price text
            Box{
                Text(
                    text = "${product.price}€",
                    fontFamily = Typography.bodyLarge.fontFamily,
                    fontSize = priceSp,
                    maxLines = 1,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 10.sp,
                        drawStyle = Stroke(
                            width = 5f,
                            join = StrokeJoin.Round,
                        )
                    )
                )
                Text(
                    text = "${product.price}€",
                    style = TextStyle(
                        color = Pink,
                        fontSize = 10.sp,
                    ),
                    fontFamily = Typography.bodyLarge.fontFamily

                )
            }


        }
    }


}