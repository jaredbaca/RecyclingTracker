# Recycling Tracker
Author: Jared Baca

https://github.com/jaredbaca/RecyclingTracker

## Overview 

The Recycling Tracker app allows users to log the items that they recycle on a weekly basis in order to measure the impact their recycling habits have on the environment. It allows users to view their recycling trends over time and gain valuable insights into the types of materials they are recycling.

## Related Work

There are several apps that tackle a similar issue, each from a slightly different angle. Many focus on recycling done at the enterprise level rather than being aimed at individuals looking to measure their personal impact. Some of the notable ones are the EPA's ReCon tool, Klima, and Adva. Many of these apps look at the larger role recycling plays in an individual's carbon footprint, which is the eventual goal of this application. However, this iteration of the app focuses solely on recycling as a key component in one's overall carbon footprint. By logging individual recyclable items, my app will aim to track those data points on a daily and weekly basis, giving a more granular view of impact over time and help to educate users on what materials can and can't be recycled.

## Updates in Current Iteration

The fundamental logic for the app has mostly been implemented in this sprint. The updates include the following:

- Added Recyclables Screen for logging individual items, Recycling Bin screen which functions like a shopping cart to store currently selected recyclables, and a Stats screen to display lifetime totals. These were implemented using Jetpack Compose and a Scaffold layout with bottom navigation. It currently uses placeholder icons for each item. UI will be fleshed out and designed in future iteration.

- The Bin Summary page uses a floating action button for submitting current bin to the Firestore database. Each entry contains a map of items and their quanitites, along with a date stamp.

- Implemented a ViewModel to store current item counts, as well as to handle database access.

- Implemented Cloud Firestore database to track entries and totals. Project data layer contains a DAO and Repository, which are used by the ViewModel to access the database using coroutines in Android. More detailed information in the Database Structure section below.


## Requirement Analysis and Testing 

|Title|Create Account (Essential)|
|---|---|
|Description|As a user, I want to create a user account to record my recycling habits and view my impact data over time.  |
|Mockups||
|Acceptance Tests|The user will create an account with a username and password. They will then be prompted to provide some basic information, which will then be stored in their profile page for future viewing and editing.|
|Test Results|Account Creation successful |
|Status|Done|)* 

|Title|View Material Categories (Essential)|
|---|---|
|Description|As a user, I want to be able to start by viewing recycling categories by material so that I can find each items quickly. |
|Mockups||
|Acceptance Tests|The user will be presented with cards for each of the main material categories: Cardboard, Glass, Plastic, Paper, etc. When they select a material, they will see a grid of icons showing common recyclabes in that category. They will then select items by tapping on the icons and increasing the count, much like a shopping cart. 
|Test Results|Items counts successfully updated in UI |
|Status|Done|)* 

|Title|Build a Recycling "Bin" (Essential)|
|---|---|
|Description|As a user, I need to be able to select the items I am recycling from the grid of common recyclable items |
|Mockups||
|Acceptance Tests|The user will then select items by tapping on the icons and increasing the count, much like a shopping cart. These items will be added to a bin object, which contains a time and date stamp. When ready, the user can submit those items, and they will be logged and added to their overall total of recycled materials.|
|Test Results|Items successfully added to Bin|
|Status|Done|)* 

|Title|Edit Date of Already Submitted Bin (Desirable)|
|---|---|
|Description|As a user, I need to be able to edit the date of a previously submitted bin in order to make corrections or adjustments |
|Mockups||
|Acceptance Tests|The user can view their recycling history organized by bins (individual submissions). They need to be able to easily edit the data contained in this bin object and have it reflected in the database and update their totals accordingly.|
|Test Results|Not implemented|
|Status|TBD|)* 

|Title|View weekly, monthly, and yearly summary (Desirable)|
|---|---|
|Description|As a user, I want to have visual representations (graphs, charts, etc.) that summarize my overall recycling progress and how those habits have impacted the environment. |
|Mockups||
|Acceptance Tests|Click on the summary tab and click “yearly” to view pie chart and overall carbon reduction.|
|Test Results|Summary only shows totals from the beginning to now|
|Status|Partially implemented|)* 

|Title|Receive Feedback that Bin Has Been Submitted to Database (Desirable)|
|---|---|
|Description|As a user, I want to receive a confirmation message that my items have been logged successfully. |
|Mockups||
|Acceptance Tests|After submitting bin, receive a message showing the bin summary and whether it was successfully uploaded or not.|
|Test Results| Not implemented|
|Status|TBD|)* 

|Title|View Trends (Desirable)|
|---|---|
|Description|As a user, I want to view how my carbon footprint has increased or decreased over time as feedback on my recycling habits.|
|Mockups||
|Acceptance Tests|Under the summary tab, the user clicks the “Trends” button to display a line graph showing their carbon emissions over time.|
|Test Results| Not implemented|
|Status|TBD|)* 

|Title|View Material Breakdown (Desirable)|
|---|---|
|Description|As a user, I want to view a pie chart of my recycled items to date broken down by material and overall weight (e.g. "This year you've recycled 50lbs of plastic"), so that I can get a sense of my long term impact.|
|Mockups||
|Acceptance Tests|Under the summary tab, user will be presented with a pie chart that pulls from the database total amounts and separatees them by material. It will also total the approximate weight in each category.|
|Test Results|Pie chart and bar graph successfully created|
|Status|Done|)* 

|Title|View Offset Information Based on Totals (Desirable)|
|---|---|
|Description|As a user, along with the totals of each material weight, I want to view exmamples of the types of emissions that could possibly be offset by those amounts, so that I can see a direct benefit of the recycling I have done.|
|Mockups||
|Acceptance Tests|Along with the approximate weights of each material type, the user will receive one or more "impact statements" along the lines of "That is the equivalent of X number of car trips" in order to draw a parallel between recycling and overall carbon footprint|
|Test Results|CO2 offset estimate generated for plastic only|
|Status|Partially implemented|)* 

|Title|Receive Recommendations (Desirable)|
|---|---|
|Description|As a user, I want to be able to receive personalized recommendations from the app for how I can reduce my carbon footprint.|
|Mockups||
|Acceptance Tests|Under Summary, the user will see their highest impact activities identified and the app will provide recommendations for reducing those activities. Ex. User logs a consistent commute via car, and the app shows how much their emissions would be reduced if they opted to take the nearby commuter train instead.|
|Test Results|Not implemented|
|Status|TBD|)* 

|Title|Earn Badges (Optional)|
|---|---|
|Description|As a user, I want to be able to set recycling goals and earn badges and rewards for achieving them so that I can feel a sense of accomplishment for reducing emissions and developing environmentally friendly habits.|
|Mockups||
|Acceptance Tests|Goal value reached for a specific parameter should trigger the addition of a badge/achievement on user’s profile|
|Test Results|Not Implemented|
|Status|TBD|)* 

|Title|Receive Weekly Wrap-Up (Optional)|
|---|---|
|Description|As a user, I want to receive a weekly wrap-up showing my recycling totals for the week and the impact comparisons listed above.|
|Mockups||
|Acceptance Tests|At the end of each week, the user will receive a summary page outlining their progress throughout the week.|
|Test Results|Not Implemented|
|Status|TBD|)* 

## Design and Implementation

- App will use a single activity with composables and use ViewModel to manage state
- IAM will be handled using Firebase
- User profile data will be stored in Google Cloud Firestore, a NoSQL database
- One of the desired features is to use location services to show the user the average carbon footprint in their area and how they compare

### Wireframes

![image](https://github.com/CS683/project-jared-baca/assets/110132943/f2119d09-961d-467e-9c50-0908935ca691)

### Final UI
<img width="243" alt="Screen Shot 2023-12-13 at 2 44 10 AM" src="https://github.com/jaredbaca/RecyclingTracker/assets/110132943/5583cd8d-6b5b-4c7f-8ea2-c1fa276c12fc">
<img width="238" alt="Screen Shot 2023-12-13 at 2 44 02 AM" src="https://github.com/jaredbaca/RecyclingTracker/assets/110132943/f03f1cf0-030d-4abe-aea1-7e5290f42dae">
<img width="237" alt="Screen Shot 2023-12-13 at 2 43 53 AM" src="https://github.com/jaredbaca/RecyclingTracker/assets/110132943/578dccaf-aaa4-4733-9887-a84709e26134">
<img width="238" alt="Screen Shot 2023-12-13 at 2 42 30 AM" src="https://github.com/jaredbaca/RecyclingTracker/assets/110132943/dca36d04-3a42-47ab-9ffa-90bf00b4afb0">
<img width="244" alt="Screen Shot 2023-12-13 at 2 42 40 AM" src="https://github.com/jaredbaca/RecyclingTracker/assets/110132943/701f0209-0d0c-4f44-8f1f-12b553908530">
<img width="235" alt="Screen Shot 2023-12-13 at 2 43 13 AM" src="https://github.com/jaredbaca/RecyclingTracker/assets/110132943/60884aea-67c0-4236-9ba9-002b2a4ac5fb">
<img width="240" alt="Screen Shot 2023-12-13 at 2 43 23 AM" src="https://github.com/jaredbaca/RecyclingTracker/assets/110132943/e78e6d1c-d788-4739-80d5-0f8cae65e5db">
<img width="239" alt="Screen Shot 2023-12-13 at 2 43 39 AM" src="https://github.com/jaredbaca/RecyclingTracker/assets/110132943/ac60d44e-8b8c-4ecc-bfa1-3368b83f2d60">

### Early UI

Current UI assets are placeholders and are intended only to test the underlying logic. Icons may be replaced with stock photos, and pie chart colors will be updated to reflect app design pallette.

<img width="260" alt="Screen Shot 2023-11-24 at 3 19 23 PM" src="https://github.com/CS683/project-jared-baca/assets/110132943/b1e2f9a8-2067-4d16-bae9-9f1d76748192">
<img width="256" alt="Screen Shot 2023-11-24 at 3 19 37 PM" src="https://github.com/CS683/project-jared-baca/assets/110132943/32daff66-8de3-4879-984d-c46a9d51c301">
<img width="261" alt="Screen Shot 2023-11-24 at 3 19 43 PM" src="https://github.com/CS683/project-jared-baca/assets/110132943/5050da74-327a-4546-b3ba-01dadf16c058">
<img width="255" alt="Screen Shot 2023-11-24 at 3 20 04 PM" src="https://github.com/CS683/project-jared-baca/assets/110132943/4a9b0c85-effe-4b1b-b393-704f96939859">
<img width="236" alt="Screen Shot 2023-11-24 at 9 54 41 PM" src="https://github.com/CS683/project-jared-baca/assets/110132943/146d005c-bbc8-4391-8bc7-1d55e3b9f5f4">


## App Architecture
### Overview
The app uses Model - View - ViewModel (MVVM) architecture. There is a single Main Activity with composable functions for each screen: HomeScreen, BinSummary, StatsPage, LoginScreen, and SignUpScreen. These components are invoked from within a Navigation Graph (RecyclingTrackerNavigationGraph), which resides in the top-level application composable, the RecyclingTrackerApp composable.

### View Models
The LogRecyclablesViewModel is the primary view model for the app. It contains all information about recyclable items, including item name, count, category, and icon. This information is stored in a RecyclableItemUiState object, which is a data class that provides the basic information for each recycling item. 

<img width="632" alt="Screen Shot 2023-12-13 at 11 41 40 PM" src="https://github.com/jaredbaca/RecyclingTracker/assets/110132943/ca13b393-9e81-4a38-b8eb-d2bf59cd4a52">

The primary UI State is a list of RecyclingItemUiState objects, held within a Mutable State. Many other functions within the app reference this UiState and the item list it contains.

In addition to the item counts and UI information, the LogRecyclablesViewModel also handles database read/writes (via the repository), as well as the logic that generates the data for the Stats page. This includes breaking down the items by category, estimating their weights, and generating the Carbon Offset estimate. 

Separate Hilt ViewModels are used for Sign In and Sign Up screens. These ViewModels communicate with a Auth Repository to handle all Firebase authentication.

### Database Configuration
The data layer consists of a NoSQL database provided by Google Cloud Firestore. A DAO and repository handle the database interactions.

The Cloud Firestore databases consists of documents, which contain key-value pairs. These documents are held in collections and subcollections. As a rule, all Firestore databases alternate between collections, and documents (a collection cannot contain another collection, and a document cannot contain another document).

The structure of the database for this project is shown below.

![RecyclingTracker_DB_Graph](https://github.com/jaredbaca/RecyclingTracker/assets/110132943/7857e312-24ff-4fd8-b56f-818cd6ef4979)


<img width="1150" alt="Screen Shot 2023-11-24 at 3 20 34 PM" src="https://github.com/CS683/project-jared-baca/assets/110132943/6d679b75-3b6f-4713-8c1d-4db3e6ff8aa6">
<img width="1149" alt="Screen Shot 2023-11-24 at 3 20 26 PM" src="https://github.com/CS683/project-jared-baca/assets/110132943/d1a28235-9809-4b1a-954f-5493d6b6f2c8">

### UI

## Project Structure

<img width="521" alt="Screen Shot 2023-11-24 at 4 07 03 PM" src="https://github.com/CS683/project-jared-baca/assets/110132943/a4beafe5-4ffa-44dc-90fd-49ae9d89fee0">



    
## Timeline

|Iteration | Application Requirements (Eseential/Desirable/Optional) | Android Components and Features| member 1 contribution/tasks| member 2 contribution/tasks|
|---|---|---|---|---|
|1|- UI Wireframes - Implement calculator logic - Prototype of the Recycle Items page UI using composables | Main Activity| | | |
|2|- Set up database - Log one activity type |Date/Time | | |
|3|- Log all activity types - Create charts for summaries and trends | | | |


## Future Work (Optional)
- Add desirable features such as badges
- Make bin information editable
- Include a full history of user's recycling subimssions, so that they can be edited

    
## Project Demo Links



## References

### Jetpack Compose and Android Resources
[Firebase Authentication with Jetpack Compose - Android Studio Tutorial - Arfin Hosain](https://www.youtube.com/watch?v=Ke90Tje7VS0&t=1734s)
<br>[Building an Android app with Jetpack Compose and Firebase - Marina Coelho](https://firebase.blog/posts/2022/04/building-an-app-android-jetpack-compose-firebase)
<br>[Create Custom Pie Chart with Animations in Jetpack Compose | Android Studio | Kotlin - Developer Chunk](https://medium.com/@developerchunk/create-custom-pie-chart-with-animations-in-jetpack-compose-android-studio-kotlin-49cf95ef321e)
<br>[Create Custom BarGraph with Scales in Jetpack Compose | Android Studio | Kotlin - Developer Chunk](https://medium.com/@developerchunk/create-custom-bargraph-with-scales-in-jetpack-compose-android-studio-kotlin-deadba24fd9b)
<br>[How to Make A Bottom Navigation With Badges in Jetpack Compose - Android Studio Tutorial - Philipp Lackner](https://www.youtube.com/watch?v=4xyRnIntwTo&t=750s)
<br>[Mastering Firebase Integration: Jetpack Compose App with Login and SignUp Features - Native Mobile Bits](https://www.youtube.com/watch?v=KOI7fS7k8Y0&t=351s)
<br>[Building a Complete Login Registration Flow in Jetpack Compose | Step-by-Step Tutorial - Native Mobile Bits](https://www.youtube.com/watch?v=PeUERQJnHdI)
<br>[Developing a Complete Android Project in Jetpack Compose - Native Mobile Bits](https://www.youtube.com/watch?v=dEEyZkZekvI&t=3478s)
<br>[Get Started with Firebase Authentication on Android](https://firebase.google.com/docs/auth/android/start)

### Recycling Data References
[Department of Environmental Protection - Montgomery County, Maryland](https://www.montgomerycountymd.gov/sws/footprint/)
[Environmental Protection Agency - Greenhouse Gas Equivalencies Calculator](https://www.epa.gov/energy/greenhouse-gas-equivalencies-calculator)
[Environmental Protection Agency - WARM Waste Reduction Model](https://www.epa.gov/warm/documentation-waste-reduction-model-warm)
[Greenhouse Gas Reductions Calculator - StopWaste.org](https://www.stopwaste.org/at-work/reduce-and-reuse/recycling-business-waste/recycling-climate-protection/greenhouse-gas)
[Earthday.org Plastic Pollution Calculator](https://www.earthday.org/plastic-pollution-calculator-2/)
[Environmental Impact Calculators - California State University](https://www.csusm.edu/sustainability/takeaction/knowurimpact.html)
