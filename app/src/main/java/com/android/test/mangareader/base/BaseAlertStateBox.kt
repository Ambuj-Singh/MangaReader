package com.android.test.mangareader.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.test.mangareader.R
import com.android.test.mangareader.ui.view.authentication.Login

@Composable
fun BaseAlertStateBox(uiState: UIState<String>) {
    when (uiState) {
        is UIState.Loading -> LoadingScreen()
        is UIState.Error -> ErrorScreen(uiState.message)
        is UIState.Success -> SuccessScreen(uiState.data)
        is UIState.Info -> InfoScreen(uiState.message)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingScreen() {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .height(125.dp)
        ) {
            CircularProgressIndicator()
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorScreen(errorMessage: String) {
            AlertDialog(
                onDismissRequest = { /* Dismiss action */ },
                title = { Text("Error") },
                text = { Text(errorMessage) },
                confirmButton = {
                    Button(onClick = { /* Confirm action */ }) {
                        Text("OK")
                    }
                }
            )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessScreen(data: String) {
    AlertDialog(
        onDismissRequest = { /* Dismiss action */ },
        title = { Text("Info") },
        icon = { Icons.Default.Check },
        text = { Text(data) },
        confirmButton = {
            Button(onClick = { /* Confirm action */ }) {
                Text("OK")
            }
        }
    )
}

@Composable
fun InfoScreen(infoMessage: String) {
            AlertDialog(
                onDismissRequest = { /* Dismiss action */ },
                title = { Text("Info") },
                icon = { Icons.Default.Check },
                text = { Text(infoMessage) },
                confirmButton = {
                    Button(onClick = { /* Confirm action */ }) {
                        Text("OK")
                    }
                }
            )
}


@Composable
fun PreviewMainScreen(){
    LoadingScreen()
}
