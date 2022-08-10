package org.tensorflow.lite.examples.digitclassifier

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskCompletionSource
import java.io.FileInputStream
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import org.tensorflow.lite.Interpreter

class DigitClassifierHoll2(private val context: Context) {
  private var interpreter: Interpreter? = null
  var isInitialized = false
    private set

  /** Executor to run inference task in the background */
  private val executorService: ExecutorService = Executors.newCachedThreadPool()

  private var inputImageWidth: Int = 0 // will be inferred from TF Lite model
  private var inputImageHeight: Int = 0 // will be inferred from TF Lite model
  private var modelInputSize: Int = 0 // will be inferred from TF Lite model

  fun initialize(): Task<Void?> {
    val task = TaskCompletionSource<Void?>()
    executorService.execute {
      try {
        initializeInterpreter()
        task.setResult(null)
      } catch (e: IOException) {
        task.setException(e)
      }
    }
    return task.task
  }

  @Throws(IOException::class)
  private fun initializeInterpreter() {
    // Load the TF Lite model
    val assetManager = context.assets
    val model = loadModelFile(assetManager)



    // Initialize TF Lite Interpreter with NNAPI enabled
    val options = Interpreter.Options()
    options.setUseNNAPI(false)
    val interpreter = Interpreter(model, options)

    // Read input shape from model file
    val inputShape = interpreter.getInputTensor(0).shape()


    for(i in inputShape){
      Log.d("taekwon95_inputShape","${i}")
    }

    inputImageWidth = inputShape[1]
    inputImageHeight = inputShape[2]
    modelInputSize = FLOAT_TYPE_SIZE * inputImageWidth * inputImageHeight * PIXEL_SIZE

    Log.d("taekwon95",FLOAT_TYPE_SIZE.toString())
    Log.d("taekwon95",inputImageWidth.toString())
    Log.d("taekwon95",inputImageHeight.toString())
    Log.d("taekwon95",PIXEL_SIZE.toString())

    // Finish interpreter initialization
    this.interpreter = interpreter
    isInitialized = true
    Log.d(TAG, "Initialized TFLite interpreter.")
  }

  @Throws(IOException::class)
  private fun loadModelFile(assetManager: AssetManager): ByteBuffer {
    Log.d("taekwon95",MODEL_FILE)
    val fileDescriptor = assetManager.openFd(MODEL_FILE)
    val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
    val fileChannel = inputStream.channel
    val startOffset = fileDescriptor.startOffset
    val declaredLength = fileDescriptor.declaredLength
    return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
  }

  private fun classifyHoll(mine:String): String {
    if (!isInitialized) {
      throw IllegalStateException("TF Lite Interpreter is not initialized yet.")
    }

    var startTime: Long
    var elapsedTime: Long

    // Preprocessing: resize the input
    startTime = System.nanoTime()

    var byteBuffer:ByteBuffer? = null

    if(mine=="홀"){
      byteBuffer = getInputHoll()
    }
    if(mine=="짝"){
      byteBuffer = getInputJjak()
    }

    elapsedTime = (System.nanoTime() - startTime) / 1000000
    Log.d(TAG, "Preprocessing time = " + elapsedTime + "ms")

    startTime = System.nanoTime()
    val result = Array(1) { FloatArray(OUTPUT_CLASSES_COUNT) }
    interpreter?.run(byteBuffer, result)
    elapsedTime = (System.nanoTime() - startTime) / 1000000
    Log.d(TAG, "Inference time = " + elapsedTime + "ms")

    Log.d("taekwon95",result[0].toString())
    Log.d("taekwon95",getOutputString(result[0]))

    return getOutputString(result[0])
  }





  fun classifyAsyncHoll(mine:String): Task<String> {
    val task = TaskCompletionSource<String>()
    executorService.execute {
      val result = classifyHoll(mine)
      task.setResult(result)
    }
    return task.task
  }

  fun close() {
    executorService.execute {
      interpreter?.close()
      Log.d(TAG, "Closed TFLite interpreter.")
    }
  }


  private fun getInputHoll(): ByteBuffer {
    val byteBuffer = ByteBuffer.allocateDirect(modelInputSize)
    byteBuffer.order(ByteOrder.nativeOrder())
    val pixels = IntArray(inputImageWidth * inputImageHeight)
    for (pixelValue in pixels) {
      byteBuffer.putFloat(0f)
    }
    return byteBuffer
  }
  private fun getInputJjak(): ByteBuffer {
    val byteBuffer = ByteBuffer.allocateDirect(modelInputSize)
    byteBuffer.order(ByteOrder.nativeOrder())
    val pixels = IntArray(inputImageWidth * inputImageHeight)
    for (pixelValue in pixels) {
      byteBuffer.putFloat(1f)
    }
    return byteBuffer
  }


  private fun getOutputString(output: FloatArray): String {
    val maxIndex = output.indices.maxByOrNull { output[it] } ?: -1
    return maxIndex.toString()
  }

  companion object {
    private const val TAG = "DigitClassifierHoll2"

    private const val MODEL_FILE = "mnist_cnn_holl2.tflite"

    private const val FLOAT_TYPE_SIZE = 4
    private const val PIXEL_SIZE = 1

    private const val OUTPUT_CLASSES_COUNT = 2
  }
}
