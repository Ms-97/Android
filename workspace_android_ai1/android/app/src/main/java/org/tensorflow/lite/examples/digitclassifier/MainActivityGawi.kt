package org.tensorflow.lite.examples.digitclassifier

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivityGawi : AppCompatActivity() {

    private var digitClassifier = DigitClassifierGawi(this)
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
            .addOnFailureListener { e -> Log.e(MainActivityGawi.TAG, "Error to setting up digit classifier.", e) }
    }


    override fun onDestroy() {
        digitClassifier.close()
        super.onDestroy()
    }

    private fun classifyDrawing(mine:String) {
        digitClassifier
            .classifyAsyncGawi(mine)
            .addOnSuccessListener {
                    resultText ->

                Log.d("taekwon95_resultText",resultText)
                var result:String = ""
                var com : String = ""
                if(resultText == "0"){
                    com = "가위"
                }else if (resultText == "1"){
                    com = "바위"
                }else if (resultText == "2") {
                    com = "보"
                }
                if(com == "가위" && mine =="가위") result ="비김"
                if(com == "가위" && mine =="바위") result ="이김"
                if(com == "가위" && mine =="보") result ="짐"

                if(com == "바위" && mine =="가위") result ="짐"
                if(com == "바위" && mine =="바위") result ="비김"
                if(com == "바위" && mine =="보") result ="이김"

                if(com == "보" && mine =="가위") result ="이김"
                if(com == "보" && mine =="바위") result ="짐"
                if(com == "보" && mine =="보") result ="비김"


                Log.d("taekwon95_mine",mine)
                Log.d("taekwon95_com",com)
                Log.d("taekwon95_result",result)

                etCom?.setText(com)
                etResult?.setText(result)


            }
            .addOnFailureListener { e ->
                Log.e(MainActivityGawi.TAG, "Error classifying drawing.", e)
            }
    }



    fun myclick(){
        var mine:String = etMine?.text.toString()

        classifyDrawing(mine)


    }

    companion object {
        private const val TAG = "MainActivityGawi"
    }
}
