package edu.ucne.ejemplofirebase.presentation.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    AuthBodyScreen(
        state = state,
        onSignIn = {
            viewModel.processIntent(AuthIntent.SignInWithGoogle(context))
        },
        onSignOut = {
            viewModel.processIntent(AuthIntent.SignOut)
        }
    )
}

@Composable
fun AuthBodyScreen(
    state: AuthState,
    onSignIn: () -> Unit,
    onSignOut: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(contentAlignment = Alignment.Center) {
            when {
                state.isLoading -> CircularProgressIndicator()

                state.user != null -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            "¡Hola, ${state.user.displayName}!",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = onSignOut) {
                            Text("Cerrar Sesión")
                        }
                    }
                }

                else -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            "Ejemplo Firebase",
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Text(
                            "Inicia sesión para continuar",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = onSignIn) {
                            Text("Iniciar sesión con Google")
                        }
                        if (state.errorMessage != null) {
                            Text(
                                state.errorMessage,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthBodyScreenPreview() {
    MaterialTheme {
        AuthBodyScreen(
            state = AuthState(),
            onSignIn = {},
            onSignOut = {}
        )
    }
}