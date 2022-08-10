package kr.co.aiai.myapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btnStart: Button = findViewById<Button>(R.id.btn)
        var btnClose: Button = findViewById<Button>(R.id.btn)
        btnStart.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("ddit","홍길동")
            this?.startService(intent)

        }
        btnClose.setOnClickListener {

            val intent = Intent(this, MainActivity2::class.java)
            this?.stopService(intent)
        }

    }
}