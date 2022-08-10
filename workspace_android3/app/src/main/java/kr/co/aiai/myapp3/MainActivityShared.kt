package kr.co.aiai.myapp3


import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivityShared : AppCompatActivity() {
    var sharedpreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv01 = findViewById<View>(R.id.tv01) as TextView
        sharedpreferences = getSharedPreferences("babo", MODE_WORLD_WRITEABLE)
        var text: String? = ""
        text += sharedpreferences?.getString("name", "babo")

        tv01.text = text

    }

    override fun onStop() {
        val editor: SharedPreferences.Editor = sharedpreferences!!.edit()
        editor.putString("name", "chunjae")
        editor.commit()
        super.onStop()
    }
}