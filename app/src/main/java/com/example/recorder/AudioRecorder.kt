package com.example.recorder

import android.content.Context
import android.media.MediaRecorder
import java.io.File
import java.io.IOException

class AudioRecorder(private val context: Context) {
    private var mediaRecorder: MediaRecorder? = null
    private var outputFile: File

    init {
        val directory = context.getExternalFilesDir(null)
        outputFile = File(directory, "recording.3gp")

    }

    fun startRecording() {
        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(outputFile.absolutePath)

            try {
                prepare()
                start()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun stopRecording() {
        mediaRecorder?.apply {
            stop()
            release()
        }
        mediaRecorder = null
    }

    fun getOutputFile(): File {
        return outputFile
    }
}