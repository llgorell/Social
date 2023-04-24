package com.example.socialmediaghale

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.socialmediaghale.presentation.category.CategoryScreen
import com.example.socialmediaghale.presentation.content_discovery.ContentScreen
import com.example.socialmediaghale.presentation.login.LoginScreen
import com.example.socialmediaghale.presentation.profile.ProfileScreen
import com.example.socialmediaghale.presentation.sign_in.SignInScreen
import com.example.socialmediaghale.presentation.splash.SplashScreen
import com.example.socialmediaghale.presentation.util.Screens
import com.example.socialmediaghale.ui.theme.SocialMediaGhaleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialMediaGhaleTheme {

                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screens.SplashScreen.route
                    ) {
                        composable(Screens.SplashScreen.route) {
                            SplashScreen(navController = navController)
                        }

                        composable(Screens.SignInScreen.route) {
                            SignInScreen(navController)
                        }

                        composable(Screens.LoginScreen.route) {
                            LoginScreen(navController = navController)
                        }

                        composable(Screens.CategoryScreen.route) {
                            CategoryScreen(navController = navController)
                        }

                        composable(Screens.ContentScreen.route) {
                            ContentScreen(navController = navController)
                        }

                        composable(Screens.ProfileScreen.route) {
                            ProfileScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

