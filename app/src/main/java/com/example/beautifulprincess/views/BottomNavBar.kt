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
import com.example.beautifulprincess.R
import com.example.beautifulprincess.models.BPActivity
import com.example.beautifulprincess.ui.theme.LightPink

@Preview(showBackground = true)
@Composable
fun BottomNavBar(currentActivity: Int = BPActivity.Home.drawableId) {
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
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
           Box(
               Modifier
                   .fillMaxWidth()
                   .background(LightPink), contentAlignment = Alignment.Center
            ) {
               Row(Modifier.offset(y = (-20).dp).scale(2.1f)) {
                   IconButton(onClick = { /*TODO*/ }) {
                       Image(
                           painter = painterResource(id = icons[0]),
                           "Home",

                       )
                   }
                   IconButton(onClick = { /*TODO*/ }) {
                       Image(painter = painterResource(id = icons[1]), "Catalog")
                   }
                   IconButton(onClick = { /*TODO*/ }) {
                       Image(painter = painterResource(id = icons[2]), "Support")
                   }
                   IconButton(onClick = { /*TODO*/ }) {
                       Image(painter = painterResource(id = icons[3]), "Profile")
                   }
               }

            }

        }
}