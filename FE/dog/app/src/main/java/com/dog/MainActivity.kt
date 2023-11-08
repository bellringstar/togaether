package com.dog;

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dog.ui.navigation.BottomNavigationBar
import com.dog.ui.theme.DogTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogTheme {

                BottomNavigationBar()
            }
        }
    }
}

