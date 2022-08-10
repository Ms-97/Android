package kr.co.aiai.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val etDan = findViewById<EditText>(R.id.etDan)
        val tvDisp = findViewById<TextView>(R.id.tvDisp)
        val btn = findViewById<Button>(R.id.btn)

        btn.setOnClickListener {
            var dan : Int = etDan.text.toString().toInt()
            var txt : String = ""

            for(i: Int in 1..9){
                txt += dan.toString()+"*"+i+"="+(i*dan)+"\n";
            }

            tvDisp.setText(txt)
        }

    }
}