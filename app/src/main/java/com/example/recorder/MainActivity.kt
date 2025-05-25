package com.example.recorder

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {

    private lateinit var audioRecorder : AudioRecorder

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()

        val buStart = findViewById<Button>(R.id.buStart)
        val buStop = findViewById<Button>(R.id.buStop)

        audioRecorder = AudioRecorder(this)

        buStart.setOnClickListener {
            audioRecorder.startRecording()
        }

        buStop.setOnClickListener {
            audioRecorder.stopRecording()
        }
    }

    private fun checkPermissionFromDevice(): Boolean {
        val recordAudioResult = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
        return recordAudioResult == PackageManager.PERMISSION_GRANTED
    }
}