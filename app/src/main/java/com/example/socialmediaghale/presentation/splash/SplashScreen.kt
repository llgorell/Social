package com.example.socialmediaghale.presentation.splash

import android.view.animation.OvershootInterpolator
import android.window.SplashScreen
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.core.R
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.socialmediaghale.presentation.util.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel = hiltViewModel()) {

    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(targetValue = 0.6f, animationSpec = tween(durationMillis = 1500, easing = {
            OvershootInterpolator(3f).getInterpolation(it)
        }))
        delay(1000L)
        val isEmptyToken = viewModel.checkToken()
        if (isEmptyToken) {
            navController.navigate(Screens.LoginScreen.route)
        } else {
            navController.navigate(Screens.ContentScreen.route)
        }
    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = com.example.socialmediaghale.R.drawable.round),
            contentDescription = "logo",
            modifier = Modifier.scale(scale = scale.value)
        )
    }
}