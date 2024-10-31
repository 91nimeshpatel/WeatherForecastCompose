package com.nimeshpatel.weatherforcast.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nimeshpatel.weatherforcast.R
import com.nimeshpatel.weatherforcast.navigation.WeatherScreens

/**
 * Created by Nimesh Patel on 28-Sep-24.
 * Purpose:
 */
@Composable
fun WeatherSplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true, block = {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 800, easing = {
                    OvershootInterpolator(8f).getInterpolation(it)
                },
                delayMillis = 2000
            )
        )
        navController.navigate(route = WeatherScreens.MainScreen.name+"/Navsari")
    })
    Surface(
        modifier = Modifier
            .padding(15.dp)
            .size(240.dp)
            .scale(scale.value),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(
            width = 2.dp, color = Color.LightGray
        )
    ) {
        Column(
            modifier = Modifier.padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(95.dp),
                painter = painterResource(R.drawable.sun),
                contentDescription = "Sun Icon",
                contentScale = ContentScale.Fit
            )
            Text(
                text = "Find the Sun?",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.LightGray
            )
        }
    }
}