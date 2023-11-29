package com.cmps312.todolist.ui.view.loginregistration

//import androidx.compose.material3.HorizontalDivider
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//create a simple login screen with email and password fields
//step 2
@Composable
fun LoginScreen(onLogin: (String, String) -> Unit, onRegisterAccount: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        username
        Text(
            text = "Login",
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
//        HorizontalDivider()
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            label = { Text(text = "Username") }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation()
        )

        OutlinedButton(
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty())
                    onLogin(email.trim(), password.trim())
                else
                    Toast.makeText(
                        context,
                        "Please enter user name or password",
                        Toast.LENGTH_SHORT
                    ).show()

            }
        ) {
            Text(text = "Login", fontSize = 20.sp)
        }


        Text(
            text = "Don't have an account? Register",
            color = Color.Blue,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(10.dp)
                .clickable {
                    onRegisterAccount()
                }

        )


    }
}