package com.example.numberninja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val imgBtn_beginner = findViewById<Button>(R.id.imgBtn_beginner)
        val imgBtn_novice = findViewById<Button>(R.id.imgBtn_novice)
        val imgBtn_amateur = findViewById<Button>(R.id.imgBtn_amateur)
        val imgBtn_expert = findViewById<Button>(R.id.imgBtn_expert)
        val btnBack1 = findViewById<Button>(R.id.btnBack1)

        imgBtn_beginner.setOnClickListener {
            val intent = Intent(this, begginerLVL::class.java)
            startActivity(intent)
        }
        imgBtn_novice.setOnClickListener {
            val intent = Intent(this, noviceLVL::class.java)
            startActivity(intent)
        }
        imgBtn_amateur.setOnClickListener {
            val intent = Intent(this, amateurLVL::class.java)
            startActivity(intent)
        }
        imgBtn_expert.setOnClickListener {
            val intent = Intent(this, expertLVL::class.java)
            startActivity(intent)
        }
        btnBack1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}