package com.nimeshpatel.weatherforcast.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.nimeshpatel.weatherforcast.R
import com.nimeshpatel.weatherforcast.model.WeatherItem
import com.nimeshpatel.weatherforcast.util.formatDate
import com.nimeshpatel.weatherforcast.util.formatDateTime
import com.nimeshpatel.weatherforcast.util.formatDecimals

/**
 * Created by Nimesh Patel on 11-Oct-24.
 * Purpose:
 */

@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(imageUrl), contentDescription = "Icon Image",
        modifier = Modifier.size(80.dp)
    )
}

@Composable
fun HumidityWindPressureRow(weatherItem: WeatherItem?, isImperial: Boolean) {

    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(R.drawable.humidity),
                contentDescription = "Humidity Icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weatherItem?.humidity ?: 0}%",
                style = MaterialTheme.typography.titleSmall
            )

        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(R.drawable.pressure),
                contentDescription = "Pressure Icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weatherItem?.pressure ?: 0} psi",
                style = MaterialTheme.typography.titleSmall
            )

        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(R.drawable.wind),
                contentDescription = "Wind Icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weatherItem?.speed ?: 0} ${if (isImperial) "mph" else "m/s"}",
                style = MaterialTheme.typography.titleSmall
            )

        }

    }
}

@Composable
fun SunsetSunRiseRow(weatherItem: WeatherItem?) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(R.drawable.sunrise),
                contentDescription = "SunRaise Icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = formatDateTime(weatherItem?.sunrise ?: 0),
                style = MaterialTheme.typography.titleSmall
            )

        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(R.drawable.sunset),
                contentDescription = "Sunset Icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = formatDateTime(weatherItem?.sunset ?: 0),
                style = MaterialTheme.typography.titleSmall
            )

        }
    }

}

@Composable
fun WeatherDetailRow(weatherItem: WeatherItem?) {
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem?.weather?.get(0)?.icon}.png"

    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color.White
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatDate(weatherItem?.dt ?: 0).split(",")[0],
                modifier = Modifier.padding(start = 5.dp)
            )
            WeatherStateImage(imageUrl = imageUrl)
            Surface(
                modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFFFC400)
            ) {
                Text(
                    text = weatherItem?.weather?.get(0)?.description ?: "",
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue.copy(alpha = 0.7f),
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(formatDecimals(weatherItem?.temp?.max ?: 0.0) + "\u00B0")
                }
                withStyle(style = SpanStyle(color = Color.LightGray)) {
                    append(formatDecimals(weatherItem?.temp?.min ?: 0.0) + "\u00B0")
                }
            })

        }
    }

}
