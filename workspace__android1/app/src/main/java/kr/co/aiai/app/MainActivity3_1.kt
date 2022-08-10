package kr.co.aiai.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity3_1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3_1)

        val et1 = findViewById<EditText>(R.id.et1)
        val et2 = findViewById<EditText>(R.id.et2)
        val et3 = findViewById<EditText>(R.id.et3)

        val btn = findViewById<Button>(R.id.btn)

        btn.setOnClickListener {
            var a = et1.text.toString().toInt()
            var b = et2.text.toString().toInt()

            var sum = a + b

            et3.setText(sum.toString())


        }

    }
}