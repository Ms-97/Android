package kr.co.aiai.myapp3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView


class SurfaceViewMe(context: Context?) : SurfaceView(context), SurfaceHolder.Callback {
    var surfaceholder: SurfaceHolder
    var flagLoop = false



    fun draw() {
        val canvas: Canvas = surfaceholder.lockCanvas()
        val paint = Paint()
        paint.setColor(Color.RED)
        canvas.drawText("hello", 0f, 1f, paint)
        surfaceholder.unlockCanvasAndPost(canvas)
    }

    init {
        surfaceholder = holder
        surfaceholder.addCallback(this)
        isFocusable = true
        flagLoop = true
    }

    override fun surfaceCreated(p0: SurfaceHolder) {

    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {

    }
}