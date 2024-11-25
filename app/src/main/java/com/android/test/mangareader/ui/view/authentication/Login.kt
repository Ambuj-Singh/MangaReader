package com.android.test.mangareader.ui.view.authentication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.ModifierLocalReadScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.test.mangareader.R
import com.android.test.mangareader.base.BaseScreen
import com.android.test.mangareader.utils.AppConstants
import com.android.test.mangareader.utils.Sizes
import kotlin.coroutines.coroutineContext

@Composable
fun Login(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFECE9E6),
                        Color(0xFFDEE0FF)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .matchParentSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WelcomeSection()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFECE9E6),
                                Color(0xFFDEE0FF)
                            )
                        )
                    )
            ) {
                Column(
                    modifier = Modifier
                        .matchParentSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    LoginForm()
                    ContinueButton()
                }
            }
        }

    }
}

@Composable
fun WelcomeSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Welcome",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black
        )
        Text(
            text = "Sign in to start",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(24.dp))

        GoogleButton()
        Spacer(modifier = Modifier.height(8.dp))
        MetaButton()

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text(
                text = "Haven't account?",
                color = Color.Gray
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Sign up!",
                color = Color.Blue,
                modifier = Modifier.clickable { /* Navigate to Sign Up screen */ }
            )
        }
    }
}

@Composable
fun LoginForm() {
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { n -> email = n  },
            label = { Text("Login") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = pass,
            onValueChange = { n -> pass = n },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = if (passwordVisible)  KeyboardType.Password else  KeyboardType.Text),
            visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    painterResource(id = R.drawable.baseline_visibility_24) // Your drawable here
                else
                    painterResource(id = R.drawable.baseline_visibility_off_24) // Another drawable for "off" state

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = image,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Forgot password?",
            color = Color.Blue,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { /* Navigate to forgot password */ }
        )
    }
}

@Composable
fun ContinueButton() {
    Button(
        onClick = { /* Handle login action */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(Color.Gray),
        shape = RoundedCornerShape(24.dp)
    ) {
        Text(text = "Continue", color = Color.White)
    }
}

@Composable
fun GoogleButton() {
    Button(
        onClick = { /* Handle Google Sign-in */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(Color.Black),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Icon(painterResource(id = R.drawable.baseline_fingerprint_24), contentDescription = "Google logo")
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Continue with Google", color = Color.White)
    }
}

@Composable
fun MetaButton() {
    Button(
        onClick = { /* Handle Meta Sign-in */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF1877F2)),
        shape = RoundedCornerShape(24.dp)
    ) {
        Icon(painterResource(id = R.drawable.baseline_bolt_24), contentDescription = "Meta logo", tint = Color.White)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Continue with Meta", color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun previewMainScreen(){
//    Login()
}
