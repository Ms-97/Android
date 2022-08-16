package org.tensorflow.lite.examples.digitclassifier

import android.content.Context
import android.content.res.AssetManager
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
import kotlin.math.log

class DigitClassifierStrike(private val context: Context) {
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

  private fun classifyStrike(mine_com:String): String {

    var byteBuffer:ByteBuffer? = null
    byteBuffer = getInput(mine_com)

    val result = Array(1) { FloatArray(OUTPUT_CLASSES_COUNT) }
    interpreter?.run(byteBuffer, result)

    Log.d("taekwon95",result[0].toString())
    Log.d("taekwon95",getOutputString(result[0]))

    return getOutputString(result[0])
  }


  fun classifyAsyncStrike(mine_com:String): Task<String> {
    val task = TaskCompletionSource<String>()
    executorService.execute {
      val result = classifyStrike(mine_com)
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


  private fun getInput(mine_com:String): ByteBuffer {
    val byteBuffer = ByteBuffer.allocateDirect(modelInputSize)
    byteBuffer.order(ByteOrder.nativeOrder())

    var num_first:String = mine_com.substring(0,1)
    var num_second:String = mine_com.substring(1,2)
    var num_third:String = mine_com.substring(2,3)
    var num_fourth:String = mine_com.substring(3,4)

    Log.d("taekwon",num_first)
    Log.d("taekwon",num_second)
    Log.d("taekwon",num_third)
    Log.d("taekwon",num_fourth)

    if(num_first.equals("0")){
      byteBuffer.putFloat(0f)
    }else if(num_first.equals("1")){
      byteBuffer.putFloat(1f)
    }
    if(num_second.equals("0")){
      byteBuffer.putFloat(0f)
    }else if(num_second.equals("1")){
      byteBuffer.putFloat(1f)
    }
    if(num_third.equals("0")){
      byteBuffer.putFloat(0f)
    }else if(num_third.equals("1")){
      byteBuffer.putFloat(1f)
    }
    if(num_fourth.equals("0")){
      byteBuffer.putFloat(0f)
    }else if(num_fourth.equals("1")){
      byteBuffer.putFloat(1f)
    }


    return byteBuffer
  }


  private fun getOutputString(output: FloatArray): String {
    val maxIndex = output.indices.maxByOrNull { output[it] } ?: -1
    return maxIndex.toString()
  }

  companion object {
    private const val TAG = "DigitClassifierStrike"

    private const val MODEL_FILE = "mnist_cnn_strike.tflite"

    private const val FLOAT_TYPE_SIZE = 4
    private const val PIXEL_SIZE = 1

    private const val OUTPUT_CLASSES_COUNT = 3
  }
}
