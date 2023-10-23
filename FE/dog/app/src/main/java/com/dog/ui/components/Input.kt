@file:Suppress("unused", "UNUSED_VARIABLE", "PreviewMustBeTopLevelFunction")
@file:OptIn(ExperimentalMaterial3Api::class)

package com.dog.ui.components

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CommonInput(
    modifier: Modifier
) {
    var text by remember {
        mutableStateOf(" ")
    }
    OutlinedTextField(
        value = text, onValueChange = { text = it },
        label =
        { Text("place holder") })
}

@Composable
fun PasswordInput(
    modifier: Modifier
) {
    var password by rememberSaveable { mutableStateOf("") }

    TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Enter password") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

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
