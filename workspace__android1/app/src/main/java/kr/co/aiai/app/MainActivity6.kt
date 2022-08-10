package kr.co.aiai.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity6 : AppCompatActivity() {

    var etn1:EditText? = null
    var etn2:EditText? = null
    var etn3:EditText? = null
    var etn4:EditText? = null
    var etn5:EditText? = null
    var etn6:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        etn1 = findViewById<EditText>(R.id.etn1)
        etn2 = findViewById<EditText>(R.id.etn2)
        etn3 = findViewById<EditText>(R.id.etn3)
        etn4 = findViewById<EditText>(R.id.etn4)
        etn5 = findViewById<EditText>(R.id.etn5)
        etn6 = findViewById<EditText>(R.id.etn6)

        val btn = findViewById<Button>(R.id.btn)

        btn.setOnClickListener {
            myclcik()
        }
    }

    fun myclcik(){
        var arr = arrayOf(
            1,2,3,4,5, 6,7,8,9,10,
            11,12,13,14,15, 16,17,18,19,20,
            21,22,23,24,25, 26,27,28,29,30,
            31,32,33,34,35, 36,37,38,39,40,
            41,42,43,44,45
        )
        for(i: Int in 0..1000){
            var rnd:Int = ((Math.random()*arr.size).toInt())
            var a = arr.get(0)
            var b = arr.get(rnd)
            arr.set(0,b)
            arr.set(rnd,a)
        }

        etn1?.setText(arr.get(0).toString())
        etn2?.setText(arr.get(1).toString())
        etn3?.setText(arr.get(2).toString())
        etn4?.setText(arr.get(3).toString())
        etn5?.setText(arr.get(4).toString())
        etn6?.setText(arr.get(5).toString())




    }

}