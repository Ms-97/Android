package org.tensorflow.lite.examples.digitclassifier

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.divyanshu.draw.widget.DrawView
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_main5.*

class MainActivityHol : AppCompatActivity() {

  private var holJjakClassifier = DigitClassifierHoll(this)

  @SuppressLint("ClickableViewAccessibility")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main5)

    var et1 = findViewById<EditText>(R.id.etMine)
    var et2 = findViewById<EditText>(R.id.etCom)
    var et3 = findViewById<EditText>(R.id.etResult)
    var btn = findViewById<Button>(R.id.btn)
    // Setup digit classifier
    holJjakClassifier
      .initialize()
      .addOnFailureListener { e -> Log.e(TAG, "Error to setting up digit classifier.", e) }

    btn.setOnClickListener {
      var user : String = et1.text.toString()
      var com = ""
      if(user != null){
        holJjakClassifier.holClassifyAsync(user)
          .addOnSuccessListener { resultText ->
            Log.d("gyeom", resultText)

            if (resultText == "0") {
              com = "hol"
            } else if (resultText == "1") {
              com = "jjak"
            }

            etCom.setText(com)

            var result = if(user == com){
              "COM승리"
            }else{
              "COM패배"
            }
            etResult.setText(result)
          }
      }
    }
  }

  override fun onDestroy() {
    holJjakClassifier.close()
    super.onDestroy()
  }

  companion object {
    private const val TAG = "MainActivity"
  }
}