package edu.bu.recyclingtracker.ui.screens

<<<<<<< HEAD
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
=======
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
>>>>>>> 03bef571b0f40fb07bbaf90a0e6ca9250010ebf3
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
<<<<<<< HEAD
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import edu.bu.recyclingtracker.ui.components.loginField
=======
import androidx.compose.ui.Modifier
>>>>>>> 03bef571b0f40fb07bbaf90a0e6ca9250010ebf3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
<<<<<<< HEAD
=======

>>>>>>> 03bef571b0f40fb07bbaf90a0e6ca9250010ebf3
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {
<<<<<<< HEAD
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
=======

        var username by remember { mutableStateOf("Username") }

        TextField(value = username, onValueChange = {
                text -> username = text
        })

>>>>>>> 03bef571b0f40fb07bbaf90a0e6ca9250010ebf3
    }
}