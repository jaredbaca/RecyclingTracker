package edu.bu.recyclingtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.firestoreSettings
import edu.bu.recyclingtracker.data.RecyclingTrackerRepository
import edu.bu.recyclingtracker.data.RecyclingTrackerDao
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

    //Instantiating Firestore DB
    val db = Firebase.firestore

    //Using emulator for testing mode
    db.useEmulator("10.0.2.2", 8080)
    db.firestoreSettings = firestoreSettings {
        isPersistenceEnabled = false
    }

    //Create Repository object
    var recyclingTrackerRepository = RecyclingTrackerRepository(RecyclingTrackerDao((db)))

    //Test add method from repository
    val user = hashMapOf(
        "first" to "Miles",
        "last" to "Davis",
        "born" to "1900"
    )

    recyclingTrackerRepository.addUser(user)

    RecyclingTrackerNavigationGraph()
}



//@Preview(showBackground = true)
//@Composable
//fun LogRecyclablesScreenPreview() {
//    RecyclingTrackerTheme {
//        LogRecyclablesScreen(model = LogRecyclablesViewModel(), navController = )
//    }
//}