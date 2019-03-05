# Weather App - Diego Pizzo
This is an Android app that shows the weather information based on the location of the user.
The project was created using Android Studio 3.3.1.

**User Interface:**
The UI of this app shows a CardView with some information and an icon that represents the current weather conditions. Moreover there is a Floating action button useful to refresh data, and at the bottom a text view where there is the last data update.
I have chosen to build a custom view for the card view so it will be possible to reuse it for others scenarios.

**Architecture:**
This project is based on MVVM pattern and written with Kotlin.

**Cache:**
This app is using a memory cache to hold the information that can be available when the user is offline. At the beginning I have implemented the [NYTimes store library](https://github.com/NYTimes/Store)  for caching but it was not simple to check if the data was expired. For this reason I have chosen to use a Room database. This database consists of one entity that keep the weather data. Each time the user downloads the info from network, these are saved into this database to always keep updated data. When the user is offline (I use the ConnectivityManager to  find out) the weather data are retrieved from database if there are some and if they are not expired.

**Possible improvements:**
If I had more time I would like to make more test to checking the correct flow of UI and viewModel and I would take care of handle the scenario where the gps of user is disabled.

# Third-party libraries
**Koin**  
Koin is a framework for the dependency injection. I have chosen to use this framework because I think is more simple than Dagger2. Avoid writing many classes, reducing boilerplate and it's easier to understand.
Link: https://github.com/InsertKoinIO/koin

**Retrofit**  
HTTP client used the make API Requests and retrieve the data from network.
Link: https://square.github.io/retrofit/

**RxJava**  
It is useful to do async operations and react to events.
Link: https://github.com/ReactiveX/RxJava

**Glide**  
Image loader for Android.
Link: https://github.com/bumptech/glide

 **TedPermission**
Library used to request at runtime the permission to use the location of the user.
Link: https://github.com/ParkSangGwon/TedPermission

**Android-ReactiveLocation**
Library much useful, that wraps Google Play Services API and permit to retrieve the location of the user with few lines of code.
https://github.com/mcharmas/Android-ReactiveLocation

**ThreeTen**
A small library that permit to use the java.time* package with sdk<26. I have used this library to handle the timestamp useful to understand if the data has expired.
Link: https://github.com/JakeWharton/ThreeTenABP