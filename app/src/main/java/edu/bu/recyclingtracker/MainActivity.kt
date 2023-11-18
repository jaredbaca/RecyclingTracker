package edu.bu.recyclingtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel
//import edu.bu.recyclingtracker.ui.screens.LogRecyclablesScreen
import edu.bu.recyclingtracker.ui.screens.RecyclingTrackerNavigationGraph
import edu.bu.recyclingtracker.ui.theme.RecyclingTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecyclingTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecyclingTrackerApp()
                }
            }
        }
    }
}

@Composable
fun RecyclingTrackerApp() {

    RecyclingTrackerNavigationGraph()
}



//@Preview(showBackground = true)
//@Composable
//fun LogRecyclablesScreenPreview() {
//    RecyclingTrackerTheme {
//        LogRecyclablesScreen(model = LogRecyclablesViewModel(), navController = )
//    }
//}