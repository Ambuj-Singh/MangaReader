package com.android.test.mangareader.base

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.android.test.mangareader.ui.SplashScreen
import com.android.test.mangareader.ui.view.authentication.Login
import com.android.test.mangareader.ui.view.authentication.SignUp
import com.android.test.mangareader.utils.AppConstants

@Composable
fun BaseNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String
    ) {
    NavHost(navController = navController, startDestination = startDestination,
        enterTransition = {
            // Define enter transition
            slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn()
        },
        exitTransition = {
            // Define exit transition
            slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut()
        },
        popEnterTransition = {
            // Define pop enter transition (when navigating back)
            slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn()
        },
        popExitTransition = {
            // Define pop exit transition (when navigating back)
            slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut()
        }
        ) {
        composable(AppConstants.K_LOGIN_SCREEN) { Login(navController) }
        composable(AppConstants.K_SPLASH_SCREEN) { SplashScreen(navController) }
        composable(AppConstants.K_SIGNUP_SCREEN) {  SignUp(navController)  }
        // Add more screens here
    }
}
