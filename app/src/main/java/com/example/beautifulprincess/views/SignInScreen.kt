package com.example.beautifulprincess.views

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.beautifulprincess.AppDatabase
import com.example.beautifulprincess.R
import com.example.beautifulprincess.models.User
import com.example.beautifulprincess.navigation.Screens
import com.example.beautifulprincess.ui.theme.Typography
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun SignInScreen(navController: NavController) {
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.background_sign_in_up),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            contentDescription = "background_profile"
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(Modifier.fillMaxHeight(.3f), contentAlignment = Alignment.BottomCenter) {
                Text(
                    text = "SIGN IN",
                    style = TextStyle(
                        fontSize = 64.sp,
                        color = Color.White,
                    ),
                    textAlign = TextAlign.Center,
                    fontFamily = Typography.bodyLarge.fontFamily,
                )
                Text(
                    text = "SIGN IN",
                    fontFamily = Typography.bodyLarge.fontFamily,
                    fontSize = 64.sp,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 64.sp,
                        drawStyle = Stroke(
                            width = 3f,
                            join = StrokeJoin.Round
                        ),
                    )
                )
            }
            Spacer(Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f),
                contentAlignment = Alignment.Center
            ) {

                TextField(
                    value = emailText,
                    onValueChange = { emailText = it },
                    Modifier
                        .clip(RoundedCornerShape(40.dp))
                        .fillMaxWidth(.71f),
                    placeholder = { Text(text = "Email") },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        unfocusedPlaceholderColor = Color.Gray,
                        focusedPlaceholderColor = Color.Gray,
                    ),
                    maxLines = 1,
                    textStyle = TextStyle(
                        fontSize = 16.sp, fontFamily = FontFamily.SansSerif,
                        color = Color.Black
                    ),
                    leadingIcon = {

                        Image(
                            painter = painterResource(id = R.drawable.email_icon),
                            contentDescription = "email_input"
                        )

                    }
                )
            }

            Spacer(Modifier.height(5.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f),
                contentAlignment = Alignment.Center
            ) {
                var passwordVisible by rememberSaveable { mutableStateOf(false) }
                TextField(
                    value = passwordText,
                    onValueChange = { passwordText = it },
                    Modifier
                        .clip(RoundedCornerShape(40.dp))
                        .fillMaxWidth(.71f),
                    placeholder = { Text(text = "Password") },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        unfocusedPlaceholderColor = Color.Gray,
                        focusedPlaceholderColor = Color.Gray,
                    ),
                    maxLines = 1,
                    textStyle = TextStyle(
                        fontSize = 16.sp, fontFamily = FontFamily.SansSerif,
                        color = Color.Black
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        val description = if (passwordVisible) "Hide password" else "Show password"
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = image, description)
                        }
                    },
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.password_icon),
                            contentDescription = "password_input"
                        )

                    }
                )
            }
            Box {
                Image(
                    painter = painterResource(id = R.drawable.not_registered__sign_up),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "not registered",
                    modifier = Modifier.clickable(onClick = {
                        navController.navigate(Screens.SignUp.route)
                    })
                )
            }

            val context = LocalContext.current
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(Modifier.fillMaxSize()) {
                    IconButton(
                        onClick = {
                            signIn(context, navController, emailText, passwordText)
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .align(Alignment.Center)

                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.sign_in_button),
                            "Sign in"
                        )
                    }
                }
            }
        }
    }
}

fun signIn(context: Context, navController: NavController, emailText: String, passwordText: String){
    val db = AppDatabase.getDbInstance(context).usersDao()
    if(db.getAllUsers().isEmpty())
        Log.d("mylog", "no users")
    for(user in AppDatabase.getDbInstance(context).usersDao().getAllUsers()){
        Log.d("mylog", "${user.id}, ${user.login}, ${user.password}")
    }
    if (emailText.isEmpty() || passwordText.isEmpty()) {
        Toast.makeText(
            context,
            "Fill empty fields!",
            Toast.LENGTH_SHORT
        ).show()
        return
    }
    val auth: FirebaseAuth = Firebase.auth

    auth.signInWithEmailAndPassword(emailText, passwordText)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if(db.getUser(emailText) == null){
                    db.insertUser(User(null, emailText, passwordText))
                }

                navController.navigate(Screens.Home.route)
            }
        }
        .addOnFailureListener {
            Toast.makeText(context, "Incorrect data!", Toast.LENGTH_SHORT).show()
        }

}