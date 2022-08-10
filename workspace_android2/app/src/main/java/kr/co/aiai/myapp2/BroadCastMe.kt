package kr.co.aiai.myapp2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BroadCastMe:BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val toast = Toast.makeText(p0, "BroadCastMe onReceive", Toast.LENGTH_SHORT)
        toast.show()

    }

}