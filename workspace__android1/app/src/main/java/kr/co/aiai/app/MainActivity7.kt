package kr.co.aiai.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity7 : AppCompatActivity() {

    var et_mine: EditText? = null
    var et_com: EditText? = null
    var et_result: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        et_mine = findViewById<EditText>(R.id.et_mine)
        et_com = findViewById<EditText>(R.id.et_com)
        et_result = findViewById<EditText>(R.id.et_result)

        val btn = findViewById<Button>(R.id.btn)

        btn.setOnClickListener {
            myclick()
        }

    }

    fun myclick(){
        var mine:String = et_mine?.text.toString()
        var com:String = "";
        var result:String = ""

        var rnd = Math.random()

        if(rnd>0.66) {
            com = "가위"
        }else if(rnd>0.33){
            com = "바위"
        }else{
            com = "보"
        }

        if( com == "가위" && mine == "가위" )     result = "비김"
        if( com == "가위" && mine == "바위" )     result = "이김"
        if( com == "가위" && mine == "보" )     result = "짐"

        if( com == "바위" && mine == "가위" )     result = "짐"
        if( com == "바위" && mine == "바위" )     result = "비김"
        if( com == "바위" && mine == "보" )     result = "이김"

        if( com == "보" && mine == "가위" )     result = "이김"
        if( com == "보" && mine == "바위" )     result = "짐"
        if( com == "보" && mine == "보" )     result = "비김"
        
        et_com?.setText(com)
        et_result?.setText(result)

    }
}




