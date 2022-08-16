package org.tensorflow.lite.examples.digitclassifier

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivityStrike : AppCompatActivity() {

    private var digitClassifier = DigitClassifierStrike(this)
    var etMine: EditText? = null
    var etCom: EditText? = null
    var etResult: EditText? = null


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_strike)

        etMine = findViewById<EditText>(R.id.etMine)
        etCom = findViewById<EditText>(R.id.etCom)
        etResult = findViewById<EditText>(R.id.etResult)

        val btn = findViewById<Button>(R.id.btn)

        btn.setOnClickListener {
            myclick()
        }
        digitClassifier
            .initialize()
            .addOnFailureListener { e -> Log.e(MainActivityStrike.TAG, "Error to setting up digit classifier.", e) }
    }


    override fun onDestroy() {
        digitClassifier.close()
        super.onDestroy()
    }

    private fun classifyDrawing(mine_com:String) {
        digitClassifier
            .classifyAsyncStrike(mine_com)
            .addOnSuccessListener {
                    resultText ->

                Log.d("taekwon95_resultText",resultText)

                etResult?.setText(resultText)

            }
            .addOnFailureListener { e ->
                Log.e(MainActivityStrike.TAG, "Error classifying drawing.", e)
            }
    }



    fun myclick(){
        var mine:String = etMine?.text.toString()
        var com:String = etCom?.text.toString()

        var mine_com:String = mine + com

        classifyDrawing(mine_com)


    }

    companion object {
        private const val TAG = "MainActivityStrike"
    }
}
