package com.example.beautifulprincess.views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.beautifulprincess.R
import com.example.beautifulprincess.models.BPActivity
import com.example.beautifulprincess.navigation.Screens
import com.example.beautifulprincess.ui.theme.Gold
import com.example.beautifulprincess.ui.theme.Typography
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

@Composable
fun SupportScreen(navController: NavController) {
    val context = LocalContext.current
    // Screen background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.header_report),
                contentDescription = "header_report",
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f)
                    .offset(y = (-15).dp),
                contentScale = ContentScale.FillBounds
            )

            Box {
                Image(
                    painter = painterResource(id = R.drawable.report_header_text),
                    contentDescription = "suggest or report",
                    modifier = Modifier
                        .offset(y = (-15).dp)
                        .fillMaxWidth(),
                    alignment = Alignment.Center
                )
            }

            // Social apps icons for links
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { /*TODO*/ }, Modifier.padding(0.dp, 0.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.socials_telegram),
                        contentDescription = "telegram",
                    )
                }
                IconButton(onClick = { /*TODO*/ }, Modifier.padding(23.dp, 0.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.socials_whatsapp),
                        contentDescription = "whatsapp",
                    )
                }
                IconButton(onClick = { /*TODO*/ }, Modifier.padding(0.dp, 0.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.socials_vk),
                        contentDescription = "vkontakte",
                    )
                }
            }

            // Report area
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var text by remember { mutableStateOf("") }
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.scroll_report),
                        contentDescription = "scroll report",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.75f)
                    )

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Your Majesty developers,",
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .offset(y = 70.dp),
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = Gold
                            ),
                            fontFamily = Typography.bodyLarge.fontFamily
                        )
                        Text(
                            text = "Your Majesty developers,",
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .offset(y = 70.dp),
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

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextField(
                            value = text,
                            onValueChange = { text = it },
                            modifier = Modifier
                                .fillMaxHeight(.7f)
                                .fillMaxWidth(.7f)
                                .padding(0.dp, 120.dp, 0.dp, 0.dp),
                            placeholder = { Text(text = "Print your request here...") },
                            colors = TextFieldDefaults.colors(
                                unfocusedPlaceholderColor = Color.Gray,
                                focusedPlaceholderColor = Color.Gray,
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily.SansSerif,
                                textAlign = TextAlign.Start,
                                color = Color.Black
                            ),
                            maxLines = 11
                        )
                    }
                }

                // Send button
                Box {
                    IconButton(
                        onClick = {
                            sendReport(context, navController, text)
                            text = ""
                        },
                        Modifier
                            .fillMaxSize(.5f)
                            .offset(y = (-15).dp)
                            .align(Alignment.Center)
                    ) {
                        Image(painter = painterResource(id = R.drawable.send_button_icon), "Send")
                    }
                }
            }
            BottomNavBar(BPActivity.Support.drawableId, navController = navController)
        }
    }
}

//Send email function
fun sendReport(context: Context, navController: NavController, text: String) {

    val firebaseAuth = FirebaseAuth.getInstance()
    if (firebaseAuth.currentUser?.email == null || text.isEmpty()) {
        navController.navigate(Screens.Profile.route)
        return
    }
    GlobalScope.launch(Dispatchers.IO) {
        val properties = Properties()
        properties.put("mail.smtp.auth", "true")
        properties.put("mail.smtp.starttls.enable", "true")
        properties.put("mail.smtp.host", "smtp.gmail.com")
        properties.put("mail.smtp.port", "587")

        val username = "beautifullprincessmobile@gmail.com"
        val password = "gqsh xswu mery redc"

        // Create a session with authentication
        val session = Session.getInstance(properties, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(username, password)
            }
        })

        try {
            val message = MimeMessage(session) // Create a MimeMessage object
            message.setFrom(InternetAddress(username)) // Set the sender email address
            message.addRecipient(
                Message.RecipientType.TO,
                InternetAddress(username)
            ) // Set the recipient email address
            message.subject = "User report"
            message.setText("$text\n\n\nsender: ${firebaseAuth.currentUser?.email} ")
            Transport.send(message) // Send the message
            launch(Dispatchers.Main) {
                Toast.makeText(context, "Email sent successfully!", Toast.LENGTH_SHORT).show()
            }
        } catch (e: MessagingException) {
            launch(Dispatchers.Main) {
                Toast.makeText(context, "Error sending email: ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


}