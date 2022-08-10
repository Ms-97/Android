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

class MainActivitya : AppCompatActivity() {

    var et1:EditText? = null
    var et2:EditText? = null
    var et3:EditText? = null
    var et4:EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maina)

        et1 = findViewById<EditText>(R.id.et1)
        et2= findViewById<EditText>(R.id.et2)
        et3 = findViewById<EditText>(R.id.et3)
        et4 = findViewById<EditText>(R.id.et4)
        val btn = findViewById<Button>(R.id.btn)


        btn.setOnClickListener {
            myclick()
        }
    }


    fun myclick(){
        var a:Int  = et1?.text.toString().toInt()
        var b:Int  = et2?.text.toString().toInt()
        var c:Int  = et3?.text.toString().toInt()
        var sum:Int = 0

        for(i:Int in a..b) {
            if (i % c == 0) {
                sum += i
            }
        }
        et4?.setText(sum.toString())

    }



}