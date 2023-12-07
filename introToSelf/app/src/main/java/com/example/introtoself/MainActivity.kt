package com.example.introtoself

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var soundtrip: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnEnter = findViewById<Button>(R.id.btnEnter)

        btnEnter.setOnClickListener {
            if (soundtrip == null) {
                soundtrip = MediaPlayer.create(this, R.raw.signsoflife)
            }
            soundtrip?.start()

            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}
