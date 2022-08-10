package kr.co.aiai.myapp5

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity() ,LocationListener {

    var locationmanager: LocationManager? = null
    var textview: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textview = findViewById(R.id.textview01)
        locationmanager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

    }

    override fun onResume() {
        super.onResume()
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        locationmanager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 5f, this)

    }

    override fun onPause() {
        locationmanager?.removeUpdates(this)
        super.onPause()
    }

    override fun onLocationChanged(location: Location) {
        val text =
            "lat:" + location.getLatitude().toString() + "₩t" + "lng:" + location.getLongitude()
                .toString() + "₩n"
        textview!!.text = text + textview!!.text.toString()
    }


}