package com.example.beautifulprincess.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.beautifulprincess.AppDatabase
import com.example.beautifulprincess.R
import com.example.beautifulprincess.models.BPActivity
import com.example.beautifulprincess.models.Product
import com.example.beautifulprincess.models.ProductRowModel
import com.example.beautifulprincess.navigation.Screens

// Catalog of products screen
@Composable
fun CatalogScreen(navController:NavController){
    var text by remember { mutableStateOf("") }

    Box(modifier = Modifier.background(Color.White)) {
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
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    Modifier
                        .clip(RoundedCornerShape(40.dp))
                        .fillMaxHeight(.43f)
                        .fillMaxWidth(.71f),
                    placeholder = { Text(text = "What do you want?") },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        unfocusedPlaceholderColor = Color.Gray,
                        focusedPlaceholderColor = Color.Gray,
                    ),
                    maxLines = 1,
                    textStyle = TextStyle(fontSize = 16.sp, fontFamily = FontFamily.SansSerif,
                        color = Color.Black),

                    trailingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Image(
                                painter = painterResource(id = R.drawable.search_icon),
                                contentDescription = "search"
                            )
                        }
                    }
                )
            }

            var expanded by remember { mutableStateOf(false) }
            val list = listOf("All", "Pomades", "Brushes", "Decorate")
            var selectedItem by remember { mutableStateOf("") }
            var textFiledSize by remember { mutableStateOf(Size.Zero) }
            val icon = if(expanded){
                Icons.Filled.KeyboardArrowUp
            }else{
                Icons.Filled.KeyboardArrowDown
            }

            Column(modifier = Modifier.padding(horizontal = 30.dp, vertical = 30.dp)){
                OutlinedTextField(
                    value = selectedItem,
                    onValueChange = { selectedItem = it },
                    modifier = Modifier
                        .fillMaxWidth(.55f)
                        .onGloballyPositioned { coordinates ->
                            textFiledSize = coordinates.size.toSize()
                        },
                    label = {Text(text = "Category")},
                    trailingIcon = {
                        Icon(icon, "", Modifier.clickable{ expanded = !expanded })
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        unfocusedTextColor = Color.Black,
                        focusedTextColor = Color.Black
                    ),
                    maxLines = 1
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current){textFiledSize.width.toDp()})
                ) {
                    list.forEach { label ->
                        DropdownMenuItem(text = {Text(text = label)},onClick = {
                            selectedItem = label
                            expanded = false
                        })
                    }
                }
            }

            Box {
                Image(
                    painter = painterResource(id = R.drawable.catalog_text),
                    contentDescription = "catalog",
                    modifier = Modifier
                        .fillMaxWidth(),
                    alignment = Alignment.Center
                )
            }

            Spacer(Modifier.height(5.dp))

            val context = LocalContext.current
            val db:AppDatabase = AppDatabase.getDbInstance(context.applicationContext)

            val product = if(selectedItem != "" && selectedItem != "All") {
                if (text == null || text == ""){
                    db.productsDao().getProductsByCategory(selectedItem)
                } else{
                    db.productsDao().getProductsByCategoryAndName(text, selectedItem)
                }
            } else {
                if (text == null || text == ""){
                    db.productsDao().getAllProducts()
                } else{
                    db.productsDao().getProductsByName(text)
                }
            }
            // Product list
            Box {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    Modifier.fillMaxHeight(0.87f),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    itemsIndexed(
                        product
                    ) { _, product ->
                        ProductCard(ProductRowModel(product.name, product.price, imageId = product.image),
                            onClick = {
                                navController.navigate(
                                    Screens.CurrentCard.route+"/${product.name}/${product.price}/${product.description}/" +
                                            "${product.image}"
                                )
                            })
                    }
                }
            }
        }
    }
    BottomNavBar(BPActivity.Catalog.drawableId, navController = navController)
}