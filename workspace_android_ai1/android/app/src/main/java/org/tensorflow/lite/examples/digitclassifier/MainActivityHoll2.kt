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

class MainActivityHoll2 : AppCompatActivity() {

  private var digitClassifier = DigitClassifierHoll2(this)
  var etMine: EditText? = null
  var etCom: EditText? = null
  var etResult: EditText? = null


  @SuppressLint("ClickableViewAccessibility")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main5)

    etMine = findViewById<EditText>(R.id.etMine)
    etCom = findViewById<EditText>(R.id.etCom)
    etResult = findViewById<EditText>(R.id.etResult)

    val btn = findViewById<Button>(R.id.btn)

    btn.setOnClickListener {
      myclick()
    }
    digitClassifier
      .initialize()
      .addOnFailureListener { e -> Log.e(MainActivityHoll2.TAG, "Error to setting up digit classifier.", e) }
  }


  override fun onDestroy() {
    digitClassifier.close()
    super.onDestroy()
  }

  private fun classifyDrawing(mine:String) {
    digitClassifier
      .classifyAsyncHoll(mine)
      .addOnSuccessListener {
          resultText ->

          Log.d("taekwon95_resultText",resultText)
          var result:String = ""
          var com : String = ""
          if(resultText == "0"){
            com = "홀"
          }else{
            com = "짝"
          }

          if(com == mine){
            result = "이김"
          }else{
            result = "짐"
          }

          Log.d("taekwon95_mine",mine)
          Log.d("taekwon95_com",com)
          Log.d("taekwon95_result",result)

          etCom?.setText(com)
          etResult?.setText(result)


      }
      .addOnFailureListener { e ->
        Log.e(MainActivityHoll2.TAG, "Error classifying drawing.", e)
      }
  }



  fun myclick(){
    var mine:String = etMine?.text.toString()

    classifyDrawing(mine)


  }

  companion object {
    private const val TAG = "MainActivityHoll2"
  }
}
