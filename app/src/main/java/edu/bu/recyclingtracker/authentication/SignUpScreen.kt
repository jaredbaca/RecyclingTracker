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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import edu.bu.recyclingtracker.R
import edu.bu.recyclingtracker.ui.screens.viewmodels.LogRecyclablesViewModel
import edu.bu.recyclingtracker.ui.navigation.Routes
import edu.bu.recyclingtracker.ui.theme.navBarColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavHostController,
    recyclablesViewModel: LogRecyclablesViewModel,
) {

    var signUpViewModel: SignUpViewModel = hiltViewModel()
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
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

            Text(text = stringResource(R.string.sign_up), fontSize = 36.sp, color = Color.LightGray)

            //Email
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = { Text(stringResource(R.string.email)) },
            )

            //Password
            OutlinedTextField(value = password,
                onValueChange = {
                    password = it
                },
                label = {Text(stringResource(R.string.password))},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            )

            var passwordsMatch by remember { mutableStateOf(true) }
            //Confirm Password
            OutlinedTextField(value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    passwordsMatch = it == password
                },
                label = {Text(stringResource(R.string.confirm_password))},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                colors = if(passwordsMatch) TextFieldDefaults.outlinedTextFieldColors() else
                TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = Color.Red
                )
            )

            if (!passwordsMatch) {
                Text(stringResource(R.string.passwords_do_not_match), color = Color.Red)
            }
//            loginField(label = "Confirm Password", keyboardType = KeyboardOptions(keyboardType = KeyboardType.Password))

            Button(modifier = Modifier
                .padding(16.dp),

                colors = ButtonDefaults.buttonColors(containerColor = navBarColor),
                enabled = passwordsMatch,
                onClick = {
                        scope.launch {
                            signUpViewModel.registerUser(email, password)
                        }
                }) {
                Text(stringResource(R.string.register))
            }

            Row {
                Text(text = stringResource(id = R.string.or), modifier = Modifier.padding(bottom = 16.dp))
            }
            Text(text = stringResource(R.string.login_with_existing_account), modifier = Modifier.clickable {
                navController.navigate(Routes.LOGIN_SCREEN)
            })

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
                        navController.navigate(Routes.LOGIN_SCREEN)
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