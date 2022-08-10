package kr.co.aiai.myapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    override fun onStart() {
        Log.d("taekwon","2onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("taekwon","2onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("taekwon","2onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("taekwon","2onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("taekwon","2onDestroy")
        super.onDestroy()
    }

    override fun onRestart() {
        Log.d("taekwon","2onRestart")
        super.onRestart()
    }


}