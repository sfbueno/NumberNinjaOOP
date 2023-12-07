package com.example.numberninja

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.numberninja.databinding.ActivityBegginerLvlBinding
import kotlin.random.Random

class begginerLVL : AppCompatActivity() {

    lateinit var binding: ActivityBegginerLvlBinding
    var answers: Int = 0
    lateinit var btnAnimate: Button
    lateinit var btnSubmit: Button
    lateinit var btnRetry: Button
    lateinit var editTextAnswer: TextView
    var animationCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBegginerLvlBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnBack3 = findViewById<Button>(R.id.btnBack3)

        btnBack3.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        val randomNumber1 = Random.nextInt(1, 101)
        val randomNumber2 = Random.nextInt(1, 101)
        val randomNumber3 = Random.nextInt(1, 101)

        val textView1: TextView = findViewById(R.id.firstRandom)
        val textView2: TextView = findViewById(R.id.secondRandom)
        val textView3: TextView = findViewById(R.id.thirdRandom)
        editTextAnswer = findViewById(R.id.editTextAnswer)
        editTextAnswer.visibility = View.INVISIBLE

        textView1.text = randomNumber1.toString()
        textView2.text = randomNumber2.toString()
        textView3.text = randomNumber3.toString()

        btnAnimate = findViewById(R.id.btnAnimate)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnRetry = findViewById(R.id.btnRetry)
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in_out)

        val bg1: ImageView = findViewById(R.id.bg_1)
        val bg2: ImageView = findViewById(R.id.bg_2)
        val bg3: ImageView = findViewById(R.id.bg_3)
        val bg4: ImageView = findViewById(R.id.bg_4)

        bg1.visibility = View.VISIBLE
        bg2.visibility = View.INVISIBLE
        bg3.visibility = View.INVISIBLE
        bg4.visibility = View.INVISIBLE

        val chooseLevelButton: Button = findViewById(R.id.btnBack3)
        chooseLevelButton.visibility = View.INVISIBLE

        btnAnimate.setOnClickListener {
            btnAnimate.isEnabled = false
            btnRetry.visibility = View.INVISIBLE
            answers = 0
            animationCount = 0

            animateTextViewWithDelay(textView1, animation, 1000)
            animateTextViewWithDelay(textView2, animation, 3000)
            animateTextViewWithDelay(textView3, animation, 5000) {
                editTextAnswer.visibility = View.VISIBLE
                btnSubmit.visibility = View.VISIBLE
                btnRetry.visibility = View.VISIBLE
                bg2.visibility = View.VISIBLE
                bg3.visibility = View.VISIBLE

                btnAnimate.visibility = View.INVISIBLE
                bg1.visibility = View.INVISIBLE

                chooseLevelButton.visibility = View.VISIBLE
                bg4.visibility = View.VISIBLE
            }
        }

        btnSubmit.setOnClickListener {
            val userAnswer = editTextAnswer.text.toString()
            if (userAnswer == answers.toString()) {
                showCorrectMessage()
            } else {
                showIncorrectMessage()
            }
        }

        btnRetry.setOnClickListener {
            resetGame()
        }
    }

    private fun animateTextViewWithDelay(
        textView: TextView,
        animation: Animation,
        delay: Long,
        onAnimationEnd: (() -> Unit)? = null
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            val number = textView.text.toString().toInt()
            answers += number
            textView.visibility = View.VISIBLE
            textView.startAnimation(animation)

            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}

                override fun onAnimationEnd(animation: Animation?) {
                    textView.visibility = View.GONE
                    animationCount++

                    if (animationCount == 3) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            editTextAnswer.visibility = View.VISIBLE
                            btnSubmit.visibility = View.VISIBLE
                            btnRetry.visibility = View.VISIBLE
                            findViewById<ImageView>(R.id.bg_2).visibility = View.VISIBLE
                            findViewById<ImageView>(R.id.bg_3).visibility = View.VISIBLE
                            findViewById<ImageView>(R.id.bg_4).visibility = View.VISIBLE
                            onAnimationEnd?.invoke()
                        }, 100)
                    }
                }

                override fun onAnimationRepeat(animation: Animation?) {}
            })
        }, delay)
    }

    private fun showCorrectMessage() {
        findViewById<TextView>(R.id.tvCorrectMessage).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvIncorrectMessage).visibility = View.INVISIBLE
    }

    private fun showIncorrectMessage() {
        findViewById<TextView>(R.id.tvCorrectMessage).visibility = View.INVISIBLE
        findViewById<TextView>(R.id.tvIncorrectMessage).visibility = View.VISIBLE
    }

    private fun resetGame() {
        val randomNumber1 = Random.nextInt(1, 101)
        val randomNumber2 = Random.nextInt(1, 101)
        val randomNumber3 = Random.nextInt(1, 101)

        findViewById<TextView>(R.id.firstRandom).text = randomNumber1.toString()
        findViewById<TextView>(R.id.secondRandom).text = randomNumber2.toString()
        findViewById<TextView>(R.id.thirdRandom).text = randomNumber3.toString()

        findViewById<EditText>(R.id.editTextAnswer).text.clear()
        findViewById<EditText>(R.id.editTextAnswer).visibility = View.INVISIBLE
        findViewById<Button>(R.id.btnAnimate).isEnabled = true
        findViewById<TextView>(R.id.tvCorrectMessage).visibility = View.INVISIBLE
        findViewById<TextView>(R.id.tvIncorrectMessage).visibility = View.INVISIBLE
        btnSubmit.visibility = View.INVISIBLE
        btnRetry.visibility = View.INVISIBLE
        findViewById<ImageView>(R.id.bg_2).visibility = View.INVISIBLE
        findViewById<ImageView>(R.id.bg_3).visibility = View.INVISIBLE
        findViewById<ImageView>(R.id.bg_4).visibility = View.INVISIBLE
        btnAnimate.visibility = View.VISIBLE
        findViewById<ImageView>(R.id.bg_1).visibility = View.VISIBLE
        findViewById<Button>(R.id.btnBack3).visibility = View.INVISIBLE
    }
}
