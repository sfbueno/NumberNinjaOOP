package com.example.numberninja

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.numberninja.databinding.ActivityExpertLvlBinding
import kotlin.random.Random

class expertLVL : AppCompatActivity() {

    lateinit var binding: ActivityExpertLvlBinding
    var expertAnswers: Int = 0
    lateinit var expertBtnAnimate: Button
    lateinit var expertBtnSubmit: Button
    lateinit var expertBtnRetry: Button
    lateinit var expertEditTextAnswer: TextView
    var expertAnimationCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpertLvlBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_expert_lvl)

        val expertBtnBack3 = findViewById<Button>(R.id.expert_btnBack3)

        expertBtnBack3.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        val expertRandomNumber1 = Random.nextInt(1, 101)
        val expertRandomNumber2 = Random.nextInt(1, 101)
        val expertRandomNumber3 = Random.nextInt(1, 101)
        val expertRandomNumber4 = Random.nextInt(1, 101)
        val expertRandomNumber5 = Random.nextInt(1, 101)
        val expertRandomNumber6 = Random.nextInt(1, 101)

        val expertTextView1: TextView = findViewById(R.id.expert_firstRandom)
        val expertTextView2: TextView = findViewById(R.id.expert_secondRandom)
        val expertTextView3: TextView = findViewById(R.id.expert_thirdRandom)
        val expertTextView4: TextView = findViewById(R.id.expert_fourthRandom)
        val expertTextView5: TextView = findViewById(R.id.expert_fifthRandom)
        val expertTextView6: TextView = findViewById(R.id.expert_sixthRandom)

        expertEditTextAnswer = findViewById(R.id.expert_editTextAnswer)
        expertEditTextAnswer.visibility = View.INVISIBLE

        expertTextView1.text = expertRandomNumber1.toString()
        expertTextView2.text = expertRandomNumber2.toString()
        expertTextView3.text = expertRandomNumber3.toString()
        expertTextView4.text = expertRandomNumber4.toString()
        expertTextView5.text = expertRandomNumber5.toString()
        expertTextView6.text = expertRandomNumber6.toString()

        expertBtnAnimate = findViewById(R.id.expert_btnAnimate)
        expertBtnSubmit = findViewById(R.id.expert_btnSubmit)
        expertBtnRetry = findViewById(R.id.expert_btnRetry)
        val expertAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_out)

        val expertBg1: ImageView = findViewById(R.id.expertbg_1)
        val expertBg2: ImageView = findViewById(R.id.expertbg_2)
        val expertBg3: ImageView = findViewById(R.id.expertbg_3)
        val expertBg4: ImageView = findViewById(R.id.expertbg_4)

        expertBg1.visibility = View.VISIBLE
        expertBg2.visibility = View.INVISIBLE
        expertBg3.visibility = View.INVISIBLE
        expertBg4.visibility = View.INVISIBLE

        val expertChooseLevelButton: Button = findViewById(R.id.expert_btnBack3)
        expertChooseLevelButton.visibility = View.INVISIBLE

        expertBtnAnimate.setOnClickListener {
            expertBtnAnimate.isEnabled = false
            expertBtnRetry.visibility = View.INVISIBLE
            expertAnswers = 0
            expertAnimationCount = 0

            expertAnimateTextViewWithDelay(expertTextView1, expertAnimation, 1000)
            expertAnimateTextViewWithDelay(expertTextView2, expertAnimation, 3000)
            expertAnimateTextViewWithDelay(expertTextView3, expertAnimation, 5000)
            expertAnimateTextViewWithDelay(expertTextView4, expertAnimation, 7000)
            expertAnimateTextViewWithDelay(expertTextView5, expertAnimation, 9000)
            expertAnimateTextViewWithDelay(expertTextView6, expertAnimation, 11000) {
                expertEditTextAnswer.visibility = View.VISIBLE
                expertBtnSubmit.visibility = View.VISIBLE
                expertBtnRetry.visibility = View.VISIBLE
                expertBg2.visibility = View.VISIBLE
                expertBg3.visibility = View.VISIBLE
                expertBg4.visibility = View.VISIBLE

                expertBtnAnimate.visibility = View.INVISIBLE
                expertBg1.visibility = View.INVISIBLE

                expertChooseLevelButton.visibility = View.VISIBLE
            }
        }

        expertBtnSubmit.setOnClickListener {
            val userAnswer = expertEditTextAnswer.text.toString()
            if (userAnswer == expertAnswers.toString()) {
                expertShowCorrectMessage()
            } else {
                expertShowIncorrectMessage()
            }
        }
        expertBtnRetry.setOnClickListener {
            expertResetGame()
        }
    }

    private fun expertAnimateTextViewWithDelay(
        textView: TextView,
        animation: Animation,
        delayMillis: Long,
        onAnimationEnd: (() -> Unit)? = null
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            val number = textView.text.toString().toInt()
            expertAnswers += number
            textView.visibility = View.VISIBLE
            textView.startAnimation(animation)

            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}

                override fun onAnimationEnd(animation: Animation?) {
                    textView.visibility = View.GONE
                    expertAnimationCount++

                    if (expertAnimationCount == 6) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            expertEditTextAnswer.visibility = View.VISIBLE
                            expertBtnSubmit.visibility = View.VISIBLE
                            expertBtnRetry.visibility = View.VISIBLE
                            findViewById<ImageView>(R.id.expertbg_2).visibility = View.VISIBLE
                            findViewById<ImageView>(R.id.expertbg_3).visibility = View.VISIBLE
                            findViewById<ImageView>(R.id.expertbg_4).visibility = View.VISIBLE
                            findViewById<TextView>(R.id.expert_sixthRandom).visibility = View.VISIBLE
                            onAnimationEnd?.invoke()
                        }, 100)
                    }
                }

                override fun onAnimationRepeat(animation: Animation?) {}
            })
        }, delayMillis)
    }

    private fun expertShowCorrectMessage() {
        findViewById<TextView>(R.id.expert_tvCorrectMessage).visibility = View.VISIBLE
        findViewById<TextView>(R.id.expert_tvIncorrectMessage).visibility = View.INVISIBLE
    }

    private fun expertShowIncorrectMessage() {
        findViewById<TextView>(R.id.expert_tvCorrectMessage).visibility = View.INVISIBLE
        findViewById<TextView>(R.id.expert_tvIncorrectMessage).visibility = View.VISIBLE
    }

    private fun expertResetGame() {
        val randomNumber1 = Random.nextInt(1, 101)
        val randomNumber2 = Random.nextInt(1, 101)
        val randomNumber3 = Random.nextInt(1, 101)
        val randomNumber4 = Random.nextInt(1, 101)
        val randomNumber5 = Random.nextInt(1, 101)
        val randomNumber6 = Random.nextInt(1, 101)

        findViewById<TextView>(R.id.expert_firstRandom).text = randomNumber1.toString()
        findViewById<TextView>(R.id.expert_secondRandom).text = randomNumber2.toString()
        findViewById<TextView>(R.id.expert_thirdRandom).text = randomNumber3.toString()
        findViewById<TextView>(R.id.expert_fourthRandom).text = randomNumber4.toString()
        findViewById<TextView>(R.id.expert_fifthRandom).text = randomNumber5.toString()
        findViewById<TextView>(R.id.expert_sixthRandom).text = randomNumber6.toString()

        findViewById<TextView>(R.id.expert_editTextAnswer).text = ""
        findViewById<TextView>(R.id.expert_editTextAnswer).visibility = View.INVISIBLE
        findViewById<Button>(R.id.expert_btnAnimate).isEnabled = true
        findViewById<TextView>(R.id.expert_tvCorrectMessage).visibility = View.INVISIBLE
        findViewById<TextView>(R.id.expert_tvIncorrectMessage).visibility = View.INVISIBLE
        findViewById<Button>(R.id.expert_btnSubmit).visibility = View.INVISIBLE
        findViewById<Button>(R.id.expert_btnRetry).visibility = View.INVISIBLE
        findViewById<ImageView>(R.id.expertbg_2).visibility = View.INVISIBLE
        findViewById<ImageView>(R.id.expertbg_3).visibility = View.INVISIBLE
        findViewById<ImageView>(R.id.expertbg_4).visibility = View.INVISIBLE
        findViewById<TextView>(R.id.expert_sixthRandom).visibility = View.INVISIBLE
        findViewById<Button>(R.id.expert_btnAnimate).visibility = View.VISIBLE
        findViewById<ImageView>(R.id.expertbg_1).visibility = View.VISIBLE
        findViewById<Button>(R.id.expert_btnBack3).visibility = View.INVISIBLE
    }
}
