package edu.ucne.ejemplofirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.ejemplofirebase.presentation.auth.AuthScreen
import edu.ucne.ejemplofirebase.ui.theme.EjemploFirebaseTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjemploFirebaseTheme {
                AuthScreen()
            }
        }
    }
}