# RecyclingTracker

##### Author: Jared Baca

## Overview
Recycling Tracker is an Android app that allows users to log the items that they recycle on a weekly basis in order to measure the impact their recycling habits have on the environment. It allows users to view their recycling trends over time and gain valuable insights into the types of materials they are recycling.

This ReadMe is an abbreviated version of the full project report available [here](https://github.com/jaredbaca/RecyclingTracker/blob/main/Doc/Project%20Report.md)

<img width="243" alt="Register" src="https://github.com/jaredbaca/RecyclingTracker/assets/110132943/77749b24-9199-4f75-a105-bd9e00f0813b">
<img width="238" alt="Login" src="https://github.com/jaredbaca/RecyclingTracker/assets/110132943/085b5e13-d2bf-4561-9258-11651be05366">
<img width="238" alt="Home_Screen_Selections" src="https://github.com/jaredbaca/RecyclingTracker/assets/110132943/bb9fbc96-3df1-477c-9eb0-23ea2ba222fc">
<img width="237" alt="Drawer" src="https://github.com/jaredbaca/RecyclingTracker/assets/110132943/15813488-7628-4e2b-a3c3-3e4eabffc413">
<img width="235" alt="Pie_Chart" src="https://github.com/jaredbaca/RecyclingTracker/assets/110132943/5075e99d-f7cd-433e-8e0c-62a0ce484fc0">
<img width="239" alt="Impact" src="https://github.com/jaredbaca/RecyclingTracker/assets/110132943/eeeff8a3-d456-40b8-baa3-9d8e440422da">
<img width="244" alt="Bin_Summary" src="https://github.com/jaredbaca/RecyclingTracker/assets/110132943/51d18016-c88b-446f-ac41-fc0ffbc783bf">
<img width="240" alt="Bar_Graph_Plastic" src="https://github.com/jaredbaca/RecyclingTracker/assets/110132943/50d354f1-bfed-41cf-a17e-316f0bef1996">

## Built With
[Kotlin](https://kotlinlang.org/)
<br>[Jetpack Compose](https://developer.android.com/jetpack/compose)
<br>[Firebase](https://firebase.google.com/)
<br>[Cloud Firestore](https://firebase.google.com/docs/firestore)

## App Architecture
### Overview
The app uses Model - View - ViewModel (MVVM) architecture. There is a single Main Activity with composable functions for each screen: HomeScreen, BinSummary, StatsPage, LoginScreen, and SignUpScreen. These components are invoked from within a Navigation Graph (RecyclingTrackerNavigationGraph), which resides in the top-level application composable, the RecyclingTrackerApp composable.

### View Models
The LogRecyclablesViewModel is the primary view model for the app. It contains all information about recyclable items, including item name, count, category, and icon. This information is stored in a RecyclableItemUiState object, which is a data class that provides the basic information for each recycling item. 

The primary UI State is a list of RecyclingItemUiState objects, held within a Mutable State. Many other functions within the app reference this UiState and the item list it contains.

In addition to the item counts and UI information, the LogRecyclablesViewModel also handles database read/writes (via the repository), as well as the logic that generates the data for the Stats page. This includes breaking down the items by category, estimating their weights, and generating the Carbon Offset estimate. 

Separate Hilt ViewModels are used for Sign In and Sign Up screens. These ViewModels communicate with a Auth Repository to handle all Firebase authentication.

### Database Configuration
The data layer consists of a NoSQL database provided by Google Cloud Firestore. A DAO and repository handle the database interactions.

The Cloud Firestore databases consists of documents, which contain key-value pairs. These documents are held in collections and subcollections. As a rule, all Firestore databases alternate between collections, and documents (a collection cannot contain another collection, and a document cannot contain another document).

The structure of the database for this project is shown below.

![RecyclingTracker_DB_Graph](https://github.com/jaredbaca/RecyclingTracker/assets/110132943/7857e312-24ff-4fd8-b56f-818cd6ef4979)

## Future Work
- Add badges and goals
- Make bin information editable
- Include a full history of user's previous subimssions

### Notes


## Sources

### Jetpack Compose & Android Resources
<br>[Firebase Authentication with Jetpack Compose - Android Studio Tutorial - Arfin Hosain](https://www.youtube.com/watch?v=Ke90Tje7VS0&t=1734s)
<br>[Building an Android app with Jetpack Compose and Firebase - Marina Coelho](https://firebase.blog/posts/2022/04/building-an-app-android-jetpack-compose-firebase)
<br>[Create Custom Pie Chart with Animations in Jetpack Compose | Android Studio | Kotlin - Developer Chunk](https://medium.com/@developerchunk/create-custom-pie-chart-with-animations-in-jetpack-compose-android-studio-kotlin-49cf95ef321e)
<br>[Create Custom BarGraph with Scales in Jetpack Compose | Android Studio | Kotlin - Developer Chunk](https://medium.com/@developerchunk/create-custom-bargraph-with-scales-in-jetpack-compose-android-studio-kotlin-deadba24fd9b)
<br>[How to Make A Bottom Navigation With Badges in Jetpack Compose - Android Studio Tutorial - Philipp Lackner](https://www.youtube.com/watch?v=4xyRnIntwTo&t=750s)
<br>[Mastering Firebase Integration: Jetpack Compose App with Login and SignUp Features - Native Mobile Bits](https://www.youtube.com/watch?v=KOI7fS7k8Y0&t=351s)
<br>[Building a Complete Login Registration Flow in Jetpack Compose | Step-by-Step Tutorial - Native Mobile Bits](https://www.youtube.com/watch?v=PeUERQJnHdI)
<br>[Developing a Complete Android Project in Jetpack Compose - Native Mobile Bits](https://www.youtube.com/watch?v=dEEyZkZekvI&t=3478s)
<br>[Get Started with Firebase Authentication on Android](https://firebase.google.com/docs/auth/android/start)

### Recycling Data Resources
[Department of Environmental Protection - Montgomery County, Maryland](https://www.montgomerycountymd.gov/sws/footprint/)
[Environmental Protection Agency - Greenhouse Gas Equivalencies Calculator](https://www.epa.gov/energy/greenhouse-gas-equivalencies-calculator)
[Environmental Protection Agency - WARM Waste Reduction Model](https://www.epa.gov/warm/documentation-waste-reduction-model-warm)
[Greenhouse Gas Reductions Calculator - StopWaste.org](https://www.stopwaste.org/at-work/reduce-and-reuse/recycling-business-waste/recycling-climate-protection/greenhouse-gas)
[Earthday.org Plastic Pollution Calculator](https://www.earthday.org/plastic-pollution-calculator-2/)
[Environmental Impact Calculators - California State University](https://www.csusm.edu/sustainability/takeaction/knowurimpact.html)

