package com.example.recorder

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File


class MainActivity : ComponentActivity() {

    private lateinit var audioRecorder : AudioRecorder

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()

        val REQUEST_RECORD_AUDIO_PERMISSION = 200
        var recordedFile: File

        while (!checkPermissionFromDevice()) {
            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.RECORD_AUDIO),
                    REQUEST_RECORD_AUDIO_PERMISSION)
            }
        }

        val buStart = findViewById<Button>(R.id.buStart)
        val buStop = findViewById<Button>(R.id.buStop)
        val buProcess = findViewById<Button>(R.id.buProcess)

        audioRecorder = AudioRecorder(this)

        buStart.setOnClickListener {
            audioRecorder.startRecording()

        }

        buStop.setOnClickListener {
            audioRecorder.stopRecording()
            recordedFile = audioRecorder.getOutputFile()

            if(recordedFile.exists() && recordedFile.length() > 0) {
                buProcess.visibility = View.VISIBLE
            }
        }
    }

    private fun checkPermissionFromDevice(): Boolean {
        val recordAudioResult = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
        return recordAudioResult == PackageManager.PERMISSION_GRANTED
    }
}