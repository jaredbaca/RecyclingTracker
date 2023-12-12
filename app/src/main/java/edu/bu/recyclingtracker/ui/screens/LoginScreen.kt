package edu.bu.recyclingtracker.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import edu.bu.recyclingtracker.ui.components.loginField

/*
This is a custom login screen intended for use with Firebase Auth
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            loginField(label = "Username", keyboardType = KeyboardOptions.Default)
            loginField(label = "Password", keyboardType = KeyboardOptions(keyboardType = KeyboardType.Password))
            Button(modifier = Modifier.padding(16.dp), onClick = { /*TODO*/ }) {
                Text("Login")
            }

            Row {
                Text(text = "or", modifier = Modifier.padding(bottom = 16.dp))
            }
            Text(text = "Create Account", modifier = Modifier.clickable {

            })
        }

        var username by remember { mutableStateOf("Username") }

    }
}