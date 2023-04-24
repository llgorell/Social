package com.example.socialmediaghale.presentation.util

sealed class Screens(val route: String) {

    object SplashScreen : Screens("splash_screen")
    object LoginScreen : Screens("login_screen")
    object SignInScreen : Screens("sign_in_screen")
    object SignUpScreen : Screens("sign_up_screen")
    object ContentScreen : Screens("Content_screen")
    object ProfileScreen : Screens("profile_screen")
    object CategoryScreen : Screens("category_screen")
}
