package com.example.mobilproject1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.mobilproject1.Registro.RegistroView
import com.example.mobilproject1.ui.theme.Mobilproject1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mobilproject1Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    RegistroView(
                        onRegistroExitoso = {
                            // navegar a siguiente pantalla
                        },
                        onIrALogin = {
                            // navegar a login
                        }
                    )
                }
            }
        }
    }
}