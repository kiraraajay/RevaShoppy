package com.reva.revashoppy.common

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.reva.revashoppy.databinding.ActivityCameraBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class CameraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraBinding

    var photoFile:File?=null

    private var imageCapture: ImageCapture? = null
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var cameraProvider: ProcessCameraProvider
    private var mLastClickTime: Long = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)


        onBackPressedDispatcher()
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        binding.cameraCaptureButton.setOnClickListener {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
            } else {
                mLastClickTime = SystemClock.elapsedRealtime()
                takePhoto()
            }
        }

        binding.cameraBackButton.setOnClickListener {
            cameraExecutor.shutdown()
            cameraProvider.unbindAll()
            onBackPressed()
            //finish()
        }

        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
    }



    private fun createPhotoFile(): File {
        // Create a unique file name for the captured photo
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        )
    }


    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        photoFile = createPhotoFile()

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile!!).build()

        imageCapture.takePicture(
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageCapturedCallback() {
                @OptIn(ExperimentalGetImage::class)
                override fun onCaptureSuccess(image: ImageProxy) {
                    super.onCaptureSuccess(image)

                    // Create a file to save the captured image
                    val photoFile = File(
                        outputDirectory,
                        SimpleDateFormat(
                            FILENAME_FORMAT,
                            Locale.US
                        ).format(System.currentTimeMillis()) + ".jpg"
                    )


                    // Create output options to save the image to the file
                    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

                    // Save the captured image to the file
                    image.use { imageProxy ->
                        try {
                            imageProxy.image?.let { image ->
                                val buffer = image.planes[0].     buffer
                                val bytes = ByteArray(buffer.remaining())
                                buffer.get(bytes)

                                FileOutputStream(photoFile).use { output ->
                                    output.write(bytes)
                                }

                                val bitmap = BitmapFactory.decodeFile(photoFile.absolutePath)
                                //val intent =Intent(applicationContext,CustomerSamplingActivity::class.java)
                               // intent.putExtra("imageFile", photoFile.absolutePath) // Pass file path
                              //  startActivity(intent)


                              //  onBackPressedDispatcher()

                                val intent = Intent().apply {
                                    putExtra("result", photoFile?.absolutePath)
                                }
                                setResult(Activity.RESULT_OK, intent)
                                finish()
                            }
                        } catch (ex: IOException) {
                            ex.printStackTrace()
                            // Display a toast to indicate failure
                            Toast.makeText(
                                this@CameraActivity,
                                "Failed to save image",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

                override fun onError(exception: ImageCaptureException) {
                    super.onError(exception)
                    // Display a toast to indicate error
                    Toast.makeText(
                        this@CameraActivity,
                        "Failed to capture image",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        )
    }


    private fun imageProxyToBitmap(image: ImageProxy): Bitmap {
        val buffer = image.planes[0].buffer
        val bytes = ByteArray(buffer.remaining())
        buffer.get(bytes)
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(Runnable {
            cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )

            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this))
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun getOutputDirectory(): File {
        val downloadDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        if (!downloadDir.exists()) {
            downloadDir.mkdirs()
        }

        return downloadDir
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                // startCamera()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    REQUIRED_PERMISSIONS,
                    REQUEST_CODE_PERMISSIONS
                )
            }
        }
    }


    private fun onBackPressedDispatcher() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val resultIntent = Intent().apply {
                    putExtra("imageFile", photoFile?.absolutePath)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
        cameraProvider.unbindAll()
    }


    companion object {
        private const val TAG = "Sarathi"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 20
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

}
