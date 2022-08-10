package kr.co.aiai.myapp3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread


var cnt = 3
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var vm:View = ViewMe(this)

        setContentView(vm)
        thread (start = true) {
            for(i: Int in 1..9){
                Thread.sleep(1000)
                Log.d("taekwon_view", "i = ${i}")
                vm.invalidate()
            }
        }
    }

    private class ViewMe(context: Context?) : View(context) {

        override fun onDraw(canvas: Canvas) {
            Log.d("taekwon","onDraw"+cnt)
            val paint = Paint()
            paint.setColor(Color.RED)
            // paint.setAlpha(125);
            val matrix = Matrix()
            canvas.setMatrix(matrix)
            canvas.drawText("Hello Graphic"+cnt, 100f, 100f, paint)
            cnt++
            super.onDraw(canvas)

        }
    }


}