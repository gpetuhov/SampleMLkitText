package com.gpetuhov.android.samplemlkittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import com.pawegio.kandroid.toast
import java.io.InputStream


// In this example we will use on-device text recognition.
// Images with text are located inside the assets folder of the project
// and are taken from: https://firebase.google.com/docs/ml-kit/recognize-text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener { recognizeText1() }

        button2.setOnClickListener { recognizeText2() }
    }

    private fun recognizeText1() {
        val bitmap = getBitmapFromAsset("photo.jpg")

        if (bitmap != null) {
            val image = FirebaseVisionImage.fromBitmap(bitmap)

            val detector = FirebaseVision.getInstance().onDeviceTextRecognizer

            detector.processImage(image)
                .addOnSuccessListener { firebaseVisionText ->
                    resultTextView.text = firebaseVisionText.text
                }
                .addOnFailureListener {
                    toast("Error recognizing text")
                }
        }
    }

    private fun recognizeText2() {
        // TODO

    }

    private fun getBitmapFromAsset(filePath: String): Bitmap? {
        val inputStream: InputStream
        var bitmap: Bitmap? = null

        try {
            inputStream = assets.open(filePath)
            bitmap = BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            toast("Error opening image file")
        }

        return bitmap
    }

}
