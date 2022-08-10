package kr.co.aiai.myapp4


import android.hardware.SensorListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() , SensorListener  {
    var textview: TextView? = null
    var sensormanager: SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textview = findViewById<View>(R.id.tv01) as TextView
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
        if (sensor == SensorManager.SENSOR_ORIENTATION) {
            var text = "";
            for(i:Int in 0..5){
                text += "value[" + i + "]:" + values?.get(i).toString()+ "\n";
            }

            textview?.text = text
        }
    }
}