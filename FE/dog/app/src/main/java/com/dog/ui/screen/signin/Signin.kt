package com.dog.ui.screen.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dog.ui.components.CommonInput
import com.dog.ui.components.MainButton
import com.dog.ui.components.PasswordInput

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Signin() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "dog") }

            )
        },
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CommonInput(modifier = Modifier, "id")
            PasswordInput(modifier = Modifier, "password")
            MainButton(onClick = {}, text = "button")
        }
    }
}
