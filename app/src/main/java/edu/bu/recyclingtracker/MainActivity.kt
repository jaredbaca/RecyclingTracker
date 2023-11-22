package edu.bu.recyclingtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.firestoreSettings
import edu.bu.recyclingtracker.data.RecyclingTrackerRepository
import edu.bu.recyclingtracker.data.RecyclingTrackerDao
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel
//import edu.bu.recyclingtracker.ui.screens.LogRecyclablesScreen
import edu.bu.recyclingtracker.ui.screens.RecyclingTrackerNavigationGraph
import edu.bu.recyclingtracker.ui.theme.RecyclingTrackerTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

    db.firestoreSettings = firestoreSettings {
        isPersistenceEnabled = false
    }

    //Using emulator for testing mode
//    db.useEmulator("10.0.2.2", 8080)



    //Create Repository object
    var recyclingTrackerRepository = RecyclingTrackerRepository(RecyclingTrackerDao((db)))

//    //Test add method from repository
//    val user = hashMapOf(
//        "first" to "Miles",
//        "last" to "Davis",
//        "born" to "1900"
//    )
//
//    recyclingTrackerRepository.addUser(user)

    //Create view model here so that it can utilize the repository
    val recyclablesViewModel: LogRecyclablesViewModel = viewModel {LogRecyclablesViewModel(recyclingTrackerRepository)}



    RecyclingTrackerNavigationGraph(recyclablesViewModel)
}



//@Preview(showBackground = true)
//@Composable
//fun LogRecyclablesScreenPreview() {
//    RecyclingTrackerTheme {
//        LogRecyclablesScreen(model = LogRecyclablesViewModel(), navController = )
//    }
//}