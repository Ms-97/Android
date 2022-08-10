package kr.co.aiai.myapp2

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class ServiceMe : Service() {

    override fun onCreate() {
        val toast = Toast.makeText(this, "serviceme:onCreate", Toast.LENGTH_SHORT)
        toast.show()
        super.onCreate()
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var param = intent?.getStringExtra("ddit")
        val toast = Toast.makeText(this, "serviceme:onStartCommand"+param, Toast.LENGTH_SHORT)
        toast.show()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        val toast = Toast.makeText(this, "serviceme:onDestroy", Toast.LENGTH_SHORT)
        toast.show()
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        var param = p0?.getStringExtra("ddit")
        val toast = Toast.makeText(this, "serviceme:onBind"+param, Toast.LENGTH_SHORT)
        toast.show()
        TODO("Not yet implemented")
    }


}