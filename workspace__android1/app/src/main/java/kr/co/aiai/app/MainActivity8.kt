package kr.co.aiai.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity8 : AppCompatActivity() {
    var etFirst:EditText? = null
    var etLast:EditText? = null
    var tvDisp:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)

        etFirst = findViewById<EditText>(R.id.etFirst)
        etLast = findViewById<EditText>(R.id.etLast)
        tvDisp = findViewById<TextView>(R.id.tvDisp)
        var btn = findViewById<Button>(R.id.btn)

        btn.setOnClickListener {
            myclick()
        }
    }

    fun myclick(){
        var a:Int  = etFirst?.text.toString().toInt()
        var b:Int  = etLast?.text.toString().toInt()

        var txt : String = ""

        for(i:Int in a..b){
            txt += drawStar(i)
        }
        tvDisp?.setText(txt)

    }

    fun drawStar(cnt:Int): String {
        var ret:String = ""
        for(i: Int in 1..cnt){
            ret += "*"
        }
        ret += "\n"
        return ret
    }




}

