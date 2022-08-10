package kr.co.aiai.myapp3


import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.FileOutputStream
import java.io.FileReader


class MainActivityFile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val textview = findViewById<View>(R.id.tv01) as TextView
        val textWrite = "babo₩nbabo₩nbabo"
        try {
            val fileoutputstream: FileOutputStream =
                openFileOutput("babo.txt", MODE_WORLD_WRITEABLE)
            fileoutputstream.write(textWrite.toByteArray())
            fileoutputstream.close()
        } catch (e: Exception) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        var textRead = ""

        try {
            val bufferedreader =
                BufferedReader(FileReader("/data/data/kr.co.aiai.myapp3/files/babo.txt"))
            var line = ""
            var i = 0
            while (bufferedreader.readLine().also { line = it } != null) {
                textRead += i.toString() + ":" + line + "₩n"
                i++
            }
        } catch (e: Exception) {
            // TODO Auto-generate
        }
        textview.text = textRead

    }


}