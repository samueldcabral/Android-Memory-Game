<img src="https://i.imgur.com/Y2qp5lq.png" width="1500" alt="splash screen">

This is a memory game app using image resources provided by Shopify.
The user can select three levels of difficulty: 4x5 grid (Easy), 5x6 grid (Medium) and 5x8 (Hard). 

In the easy mode, the user has to find 10 pairs to win!

In the medium mode, the user must match three of a kind. The user must match 10 times to win.

In the hard mode, the user must match four of a kind. The user must match 10 times to win.

There's a button to shuffle the game and start over or you can go home and try a new difficulty.
The app keeps track of how many matches have been found and when the user wins, it displays a message.

## Table of Contents
[Getting Started](#getting-started)

[Screenshots](#screenshots)

[License](#license)

## Getting started <a name="getting-started"></a>

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

## Screenshots <a name="screenshots"></a>

 Splash screen           |  Main screen | Game Over
:-------------------------:|:-------------------------:|:------------:|
<img src="https://i.imgur.com/RYDnugK.png" width="178" alt="splash screen">  |  <img src="https://i.imgur.com/52GOtyq.png" width="178" alt="splash screen"> |<img src="https://i.imgur.com/73W7P7B.png" width="178" alt="splash screen">}

### 4x5 Grid
No matches found           |  Some matches found (2 of a kind)
:-------------------------:|:-------------------------:
<img src="https://i.imgur.com/edmujOO.png" width="178" alt="splash screen">  |  <img src="https://i.imgur.com/DOWlPnD.png" width="178" alt="splash screen"> |}

### 5x6 Grid
No matches found           |  Some matches found (3 of a kind)
:-------------------------:|:-------------------------:
<img src="https://i.imgur.com/lfhJWjo.png" width="178" alt="splash screen">  |  <img src="https://i.imgur.com/RoNdLBn.png" width="178" alt="splash screen"> |}

### 5x8 Grid
No matches found           |  Some matches found (4 of a kind)
:-------------------------:|:-------------------------:
<img src="https://i.imgur.com/4j2w778.png" width="178" alt="splash screen">  |  <img src="https://i.imgur.com/w2Cts4r.png" width="178" alt="splash screen"> |}

## License <a name="license"></a>

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
