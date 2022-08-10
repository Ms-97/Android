package kr.co.aiai.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.util.logging.Logger

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val tv = findViewById<TextView>(R.id.tv)
        val btn = findViewById<Button>(R.id.btn)

        btn.setOnClickListener {
            var a = tv.text.toString()
            var aa : Int = a.toInt()
            aa++
            tv.text = aa.toString()
        }

    }
}











