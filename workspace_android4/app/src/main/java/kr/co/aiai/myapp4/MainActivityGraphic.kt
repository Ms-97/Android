package kr.co.aiai.myapp4


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


var mvalues = FloatArray(6)

class MainActivityGraphic : AppCompatActivity() , SensorListener  {
    var sensormanager: SensorManager? = null
    var viewme : ViewMe? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewme = ViewMe(this)
        setContentView(viewme)
        sensormanager = getSystemService(SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        sensormanager!!.registerListener(this, SensorManager.SENSOR_ALL)
        super.onResume()
    }

    override fun onPause() {
        sensormanager?.unregisterListener(this)
        super.onPause()
    }

    override fun onAccuracyChanged(sensor: Int, accuracy: Int) {
    }

    override fun onSensorChanged(sensor: Int, values: FloatArray?) {
        if (sensor == SensorManager.SENSOR_ALL) {
            var sensor0 :Float= values?.get(0)!!.toFloat()
            var sensor1 :Float= values?.get(1)!!.toFloat()
            var sensor2 :Float= values?.get(2)!!.toFloat()
            var sensor3 :Float= values?.get(3)!!.toFloat()
            var sensor4 :Float= values?.get(4)!!.toFloat()
            var sensor5 :Float= values?.get(5)!!.toFloat()

            mvalues.set(0,sensor0)
            mvalues.set(1,sensor1)
            mvalues.set(2,sensor2)
            mvalues.set(3,sensor3)
            mvalues.set(4,sensor4)
            mvalues.set(5,sensor5)

            Log.d("taekwon","sensor0"+mvalues?.get(0).toString())
            Log.d("taekwon","sensor1"+mvalues?.get(1).toString())
            Log.d("taekwon","sensor2"+mvalues?.get(2).toString())
            Log.d("taekwon","sensor3"+mvalues?.get(3).toString())
            Log.d("taekwon","sensor4"+mvalues?.get(4).toString())
            Log.d("taekwon","sensor5"+mvalues?.get(5).toString())

            viewme?.invalidate()
        }
    }

    class ViewMe(context: Context?) : View(context) {
        override fun onDraw(canvas: Canvas) {

            var fsensor0:Float = mvalues?.get(0).toFloat()/10
            var fsensor1:Float = mvalues?.get(1).toFloat()*10
            var fsensor2:Float = mvalues?.get(2).toFloat()*10
            var fsensor3:Float = mvalues?.get(3).toFloat()*10
            var fsensor4:Float = mvalues?.get(4).toFloat()*10
            var fsensor5:Float = mvalues?.get(5).toFloat()*10

            var sensor0:String = mvalues?.get(0).toString()
            var sensor1:String = mvalues?.get(1).toString()
            var sensor2:String = mvalues?.get(2).toString()
            var sensor3:String = mvalues?.get(3).toString()
            var sensor4:String = mvalues?.get(4).toString()
            var sensor5:String = mvalues?.get(5).toString()

            val paint = Paint()
            paint.setColor(Color.RED)
            canvas.drawLine(100f+50f*1, 100f, 100f+50f*1, 100f+fsensor0, paint)
            canvas.drawLine(100f+50f*2, 100f, 100f+50f*2, 100f+fsensor1, paint)
            canvas.drawLine(100f+50f*3, 100f, 100f+50f*3, 100f+fsensor2, paint)
            canvas.drawLine(100f+50f*4, 100f, 100f+50f*4, 100f+fsensor3, paint)
            canvas.drawLine(100f+50f*5, 100f, 100f+50f*5, 100f+fsensor4, paint)
            canvas.drawLine(100f+50f*6, 100f, 100f+50f*6, 100f+fsensor5, paint)

            paint.setColor(Color.BLACK)
            canvas.drawText(sensor0, 50f, 200f+50*1, paint)
            canvas.drawText(sensor1, 50f, 200f+50*2, paint)
            canvas.drawText(sensor2, 50f, 200f+50*3, paint)
            canvas.drawText(sensor3, 50f, 200f+50*4, paint)
            canvas.drawText(sensor4, 50f, 200f+50*5, paint)
            canvas.drawText(sensor5, 50f, 200f+50*6, paint)

            super.onDraw(canvas)
        }
    }

}