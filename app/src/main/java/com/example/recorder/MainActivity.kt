package com.example.recorder

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : ComponentActivity() {

    private lateinit var audioRecorder : AudioRecorder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()

        val REQUEST_RECORD_AUDIO_PERMISSION = 200
        var permissionToRecordAccepted = false

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                REQUEST_RECORD_AUDIO_PERMISSION)
        } else {
            permissionToRecordAccepted = true
        }

        val buStart = findViewById<Button>(R.id.buStart)
        val buStop = findViewById<Button>(R.id.buStop)

        if(!checkPermissionFromDevice()) {
            // AlertDialog erstellen
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Dies ist eine wichtige Nachricht!")
                .setTitle("Wichtige Information")
                .setCancelable(false)
                .setPositiveButton("OK") { dialog, id -> // Aktion, die ausgeführt wird, wenn auf "OK" geklickt wird
                    dialog.dismiss()
                }


            // AlertDialog anzeigen
            val alert = builder.create()
            alert.show()
        }

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