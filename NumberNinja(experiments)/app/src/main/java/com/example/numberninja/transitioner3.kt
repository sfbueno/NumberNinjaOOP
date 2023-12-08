package com.example.numberninja

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class transitioner3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transitioner1)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this, amateurLVL::class.java)
            startActivity(intent)
            finish()
        }, 5000) // Delay for 2 seconds (adjust as needed)
    }
}
