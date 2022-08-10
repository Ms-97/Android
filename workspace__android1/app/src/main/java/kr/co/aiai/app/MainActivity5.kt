package kr.co.aiai.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity5 : AppCompatActivity() {

    var etMine: EditText? = null
    var etCom: EditText? = null
    var etResult: EditText? = null

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

    }

    fun myclick(){
        var mine:String = etMine?.text.toString()
        var com:String = "";
        var result:String = ""

        var rnd = Math.random()

        if(rnd>0.5){
            com = "홀"
        }else{
            com = "짝"
        }

        if(com == mine){
            result = "이김"
        }else{
            result = "짐"
        }
        etCom?.setText(com)
        etResult?.setText(result)

    }
}




