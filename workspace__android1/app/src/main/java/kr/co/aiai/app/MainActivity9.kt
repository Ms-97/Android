package kr.co.aiai.app

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity9 : AppCompatActivity() {

    var et:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main9)

        et = findViewById<EditText>(R.id.et)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)

        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)
        val btn0 = findViewById<Button>(R.id.btn0)
        val btnCall = findViewById<Button>(R.id.btnCall)


        btn1.setOnClickListener { myclick(it) }
        btn2.setOnClickListener { myclick(it) }
        btn3.setOnClickListener { myclick(it) }
        btn4.setOnClickListener { myclick(it) }
        btn5.setOnClickListener { myclick(it) }

        btn6.setOnClickListener { myclick(it) }
        btn7.setOnClickListener { myclick(it) }
        btn8.setOnClickListener { myclick(it) }
        btn9.setOnClickListener { myclick(it) }
        btn0.setOnClickListener { myclick(it) }

        btnCall.setOnClickListener { mycall() }



    }

    fun mycall(){
        var str_tel :String = et?.text.toString()

        val builder = AlertDialog.Builder(this)
        builder.setTitle("calling.")
            .setMessage(str_tel)
            .setPositiveButton("확인",null)
        builder.show()

    }

    fun myclick(it:View){
        var imsi:Button =  it as Button

        var str_new : String = imsi.text.toString()
        var str_old :String = et?.text.toString()

        et?.setText(str_old+str_new)
    }



}