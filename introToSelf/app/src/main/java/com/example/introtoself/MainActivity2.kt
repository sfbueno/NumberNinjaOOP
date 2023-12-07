package com.example.introtoself

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val btnAbout_me = findViewById<ImageButton>(R.id.btnAbout_me)
        val btnEducation = findViewById<ImageButton>(R.id.btnEducation)
        val btnFamily = findViewById<ImageButton>(R.id.btnFamily)
        val btnGaming = findViewById<ImageButton>(R.id.btnGaming)


        btnAbout_me.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
        btnEducation.setOnClickListener {
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
        }
        btnFamily.setOnClickListener {
            val intent = Intent(this, MainActivity5::class.java)
            startActivity(intent)
        }
        btnGaming.setOnClickListener {
            val intent = Intent(this, MainActivity6::class.java)
            startActivity(intent)
        }
    }
}
