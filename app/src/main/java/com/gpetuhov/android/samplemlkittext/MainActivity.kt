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


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener { recognizeText1() }

        button2.setOnClickListener { recognizeText2() }
    }

    private fun recognizeText1() = recognizeTextFromAssetImage("photo.jpg")

    private fun recognizeText2() = recognizeTextFromAssetImage("document.jpg")

    // Images with text are located inside the assets folder of the project
    // and are taken from: https://firebase.google.com/docs/ml-kit/recognize-text
    private fun recognizeTextFromAssetImage(filePath: String) {
        val bitmap = getBitmapFromAsset(filePath)

        if (bitmap != null) {
            val image = FirebaseVisionImage.fromBitmap(bitmap)

            // In this example images are already properly rotated,
            // so no need to compensate camera rotation.

            // Use on-device text recognition
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
