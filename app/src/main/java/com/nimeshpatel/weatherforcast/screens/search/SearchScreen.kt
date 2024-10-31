package com.nimeshpatel.weatherforcast.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nimeshpatel.weatherforcast.navigation.WeatherScreens
import com.nimeshpatel.weatherforcast.widgets.WeatherAppBar

/**
 * Created by Nimesh Patel on 14-Oct-24.
 * Purpose:
 */
@Composable
fun SearchScreen(navController: NavController) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                navController = navController,
                title = "Search",
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                isMainScreen = false,
                elevation = 5.dp
            ) {
                navController.popBackStack()
            }
        }

    ) { paddingValues ->
        SearchBody(paddingValues, navController)
    }
}

@Composable
fun SearchBody(paddingValues: PaddingValues, navController: NavController) {
    Surface(modifier = Modifier.padding(paddingValues)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomSearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            ) { mSearchText ->
                navController.navigate(WeatherScreens.MainScreen.name + "/$mSearchText")
            }
        }
    }

}

@Composable
fun CustomSearchBar(
    modifier: Modifier,
    onSearch: (String) -> Unit
) {
    val searchQueryState = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty()

    }
    Column {
        CommonTextField(
            modifier = modifier,
            valueState = searchQueryState,
            placeHolder = "Search by City",
            onAction = KeyboardActions{
                 if(!valid) return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyboardController?.hide()
            }
        )
    }

}

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    placeHolder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {

    OutlinedTextField(
        modifier = modifier,
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = placeHolder) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
    )

}
