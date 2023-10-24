package com.dog.ui.screen.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dog.R
import com.dog.ui.components.CommonInput
import com.dog.ui.components.HeadingText
import com.dog.ui.components.MainButton
import com.dog.ui.components.PasswordInput
import com.dog.ui.theme.DogTheme
import com.dog.ui.theme.Yellow300

@Preview
@Composable
fun SignIn() {
    DogTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Yellow300)
                .padding(28.dp)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.SpaceAround,
            ) {
                HeadingText(modifier = Modifier, value = stringResource(id = R.string.signin))
                CommonInput(modifier = Modifier, "id")
                PasswordInput(modifier = Modifier, "password")

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    MainButton(onClick = {}, text = "로그인")
                    MainButton(onClick = {}, text = "회원가입")
                }

            }
        }
    }

}
