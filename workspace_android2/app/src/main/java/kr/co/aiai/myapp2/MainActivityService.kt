package kr.co.aiai.myapp2

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivityService : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_service)
        var btnStart : Button = findViewById<Button>(R.id.btnStart)
        var btnClose : Button = findViewById<Button>(R.id.btnClose)
        btnStart.setOnClickListener {
            val toast = Toast.makeText(this, "myclick:start", Toast.LENGTH_SHORT)
            toast.show()
        }
        btnClose.setOnClickListener {
            val toast = Toast.makeText(this, "myclick:close", Toast.LENGTH_LONG)
            toast.show()
        }

    }
}