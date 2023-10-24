@file:Suppress("unused", "UNUSED_VARIABLE", "PreviewMustBeTopLevelFunction")

package com.dog.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dog.ui.theme.DogTheme
import com.dog.ui.theme.Pink300
import com.dog.ui.theme.Pink400
import com.dog.ui.theme.Pink500
import com.dog.ui.theme.PinkGray400
import com.dog.ui.theme.Purple400


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonInput(
    modifier: Modifier,
    placeHolder: String
) {
    DogTheme {
        var text by remember {
            mutableStateOf(" ")
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Purple400)),
            value = text, onValueChange = { text = it },
            label = { Text(text = placeHolder) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Pink400,
                focusedLabelColor = Pink300,
                cursorColor = Pink500,

                ),
            textStyle = TextStyle(
                color = PinkGray400, fontWeight = FontWeight.Bold
            ),
            keyboardOptions = KeyboardOptions.Default,
            leadingIcon = {
//                Icon(painter = painterResource(id = R.drawable.profile), contentDescription =)
            }
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    modifier: Modifier,
    placeHolder: String
) {
    DogTheme {
        var password by rememberSaveable { mutableStateOf("") }
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Purple400)),
            value = password,
            onValueChange = { password = it },
            label = { Text(placeHolder) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Pink400,
                focusedLabelColor = Pink300,
                cursorColor = Pink500,
            ),
            textStyle = TextStyle(
                color = PinkGray400, fontWeight = FontWeight.Normal, fontSize = 12.sp
            ),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchInput(
    modifier: Modifier
) {
    var content by remember {
        mutableStateOf(" ")
    }
    TextField(
        value = content, onValueChange = { content = it },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        placeholder = {
            Text("검색할 내용")
        },
        modifier = modifier
            .heightIn(min = 56.dp)
    )
}
