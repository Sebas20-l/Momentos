package com.example.mobilproject1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mobilproject1.ui.navigation.AppNavigation
import com.example.mobilproject1.ui.theme.Mobilproject1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mobilproject1Theme {
                AppNavigation()
            }
        }
    }
}