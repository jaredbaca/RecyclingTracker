package edu.bu.recyclingtracker.authentication

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel
import edu.bu.recyclingtracker.ui.components.loginField
import edu.bu.recyclingtracker.ui.theme.navBarColor
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    navController: NavHostController,
    recyclablesViewModel: LogRecyclablesViewModel,
) {

    var signUpViewModel: SignUpViewModel = hiltViewModel()
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scope  = rememberCoroutineScope()
    val state = signUpViewModel.signUpState.collectAsState(initial = null)
    val context = LocalContext.current


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            loginField(label = "Email", keyboardType = KeyboardOptions.Default)
            loginField(label = "Password", keyboardType = KeyboardOptions(keyboardType = KeyboardType.Password))
//            loginField(label = "Confirm Password", keyboardType = KeyboardOptions(keyboardType = KeyboardType.Password))

            Button(modifier = Modifier
                .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = navBarColor),
                onClick = {
                    scope.launch {
                        signUpViewModel.registerUser(email, password)
                    }
                }) {
                Text("Register")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                if (state.value?.isLoading == true) {
                    CircularProgressIndicator()
                }
            }

            LaunchedEffect(key1 = state.value?.isSuccess ) {
                scope.launch {
                    if(state.value?.isSuccess?.isNotEmpty() == true) {
                        val success = state.value?.isSuccess
                        Toast.makeText(context, "$success", Toast.LENGTH_LONG).show()
                    }
                }
            }

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