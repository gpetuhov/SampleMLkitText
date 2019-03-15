package com.gpetuhov.android.samplemlkittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// In this example we will use on-device text recognition.
// Images with text are located inside the assets folder of the project
// and are taken from: https://firebase.google.com/docs/ml-kit/recognize-text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
