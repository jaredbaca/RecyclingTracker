package edu.bu.recyclingtracker.authentication

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import edu.bu.recyclingtracker.ui.navigation.Routes
import edu.bu.recyclingtracker.ui.theme.navBarColor
import kotlinx.coroutines.launch

// =================================== Custom Login Screen =======================================

/**
 * Uses the AuthRepository to perform basic login/logout functions
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {

    // ===================== Accessing ViewModels and setting state ====================

    var loginViewModel: LoginViewModel = hiltViewModel()
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scope  = rememberCoroutineScope()
    val state = loginViewModel.signInState.collectAsState(initial = null)
    val context = LocalContext.current

    // =================================== Login Page UI ===============================
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Sign In", fontSize = 36.sp, color = Color.LightGray)

            // Email Field
            OutlinedTextField(value = email,
                onValueChange = {
                    email = it
                },
                label = {Text("email")},
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
            )

            // Password Field
            OutlinedTextField(value = password,
                onValueChange = {
                    password = it
                },
                label = {Text("password")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )

            // Login Button
            Button(modifier = Modifier
                .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = navBarColor),
                onClick = {
                    loginViewModel.loginUser(email, password)
                }) {
                Text("Login")
            }

            // Direct to Sign Up page
            Row {
                Text(text = "or", modifier = Modifier.padding(bottom = 16.dp))
            }
            Text(text = "Create Account", modifier = Modifier.clickable {
                navController.navigate(Routes.SIGNUP_SCREEN)
            })

            // Loading indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                if (state.value?.isLoading == true) {
                    CircularProgressIndicator()
                }
            }

        // ============================ Perform Firebase Auth =====================================

            // Successful Login - Provide Toast Message saying login successful and navigate Home
            LaunchedEffect(key1 = state.value?.isSuccess ) {
                scope.launch {
                    if(state.value?.isSuccess?.isNotEmpty() == true) {
                        val success = state.value?.isSuccess
                        Toast.makeText(context, "$success", Toast.LENGTH_LONG).show()
                        loginViewModel.updateCurrentUser()
                        navController.navigate(Routes.HOME_SCREEN)
                    }
                }
            }

            // Loading
            LaunchedEffect(key1 = state.value?.isError ) {
                scope.launch {
                    if(state.value?.isError?.isNotEmpty() == true) {
                        val error = state.value?.isError
                        Toast.makeText(context, "$error", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}