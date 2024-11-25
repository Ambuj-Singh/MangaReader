package com.android.test.mangareader.ui.view.permission

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun RequestPermissionScreen() {
    val context = LocalContext.current
    val requiredPermissions = listOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.POST_NOTIFICATIONS,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    // State to keep track of denied permissions
    var deniedPermissions by remember { mutableStateOf(emptyList<String>()) }

    // Launcher to request permissions - this MUST be inside a Composable
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        // Collect denied permissions
        deniedPermissions = result.filterValues { !it }.keys.toList()
    }

    // LaunchedEffect ensures this side effect only happens when the Composable is first composed
    LaunchedEffect(Unit) {
        if (!PermissionUtils.hasPermissions(context, requiredPermissions)) {
            // Launch the permission request if not all permissions are granted
            permissionLauncher.launch(requiredPermissions.toTypedArray())
        }
    }

    // Update UI based on permission status
    if (deniedPermissions.isEmpty()) {
        // Show main content if permissions are granted
        ShowContent()
    } else {
        // Show a message if permissions are denied
        ShowPermissionDeniedMessage(deniedPermissions)
    }
}

@Composable
fun ShowContent() {
    // Your app's main content when permissions are granted
    Text("Permissions granted")
}

@Composable
fun ShowPermissionDeniedMessage(deniedPermissions: List<String>) {
    // Show message when permissions are denied
    RequestPermissionScreen()
}
