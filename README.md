# Memory Game | Android Kotlin

This is a memory game app using image resources provided by Shopify.
The user can select three levels of difficulty: 4x5 grid (Easy), 5x6 grid (Medium) and 5x8 (Hard). 

In the easy mode, the user has to find 10 pairs to win!

In the medium mode, the user must match three of the same cards. The user must match 10 times to win.

In the hard mode, the user must match four of the same cards. The user must match 10 times to win.

There's a button to shuffle the game and start over or you can go home and try a new difficulty.
The app keeps track of how many matches have been found and when the user wins, it displays a message.

## Screenshots

 Splash screen           |  Main screen
:-------------------------:|:-------------------------:
<img src="https://i.imgur.com/RYDnugK.png" width="178" alt="splash screen">  |  <img src="https://i.imgur.com/RYDnugK.png" width="178" alt="splash screen"> |}
| 



## Getting started

### Third party libraries used in this project:

Retrofit [link](https://square.github.io/retrofit/)

Retrofit Gson Converter [link](https://github.com/square/retrofit/tree/master/retrofit-converters/gson)

Picasso [link](https://square.github.io/picasso/)

In your build.gradle (Module: app) make sure to include the following lines:
```
dependencies {
    //other dependencies above - do not include this line

    implementation 'com.squareup.retrofit2:retrofit:2.6.2'
    implementation 'com.squareup.retrofit2:converter-gson:2.6.2'
    implementation 'com.squareup.picasso:picasso:2.71828'
}
```

### Android Manifest changes

Since the app is using the internet to fetch data, you'll need to include the following lines in your Android Manifest:

```
<manifest xlmns:android...>
  <!-- other manifest stuff here -->

  <uses-permission android:name="android.permission.INTERNET" />

  <!-- other manifest stuff here -->
</manifest>
```

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
