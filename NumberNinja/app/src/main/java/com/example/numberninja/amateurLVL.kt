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
import com.example.numberninja.databinding.ActivityAmateurLvlBinding
import kotlin.random.Random

class amateurLVL : AppCompatActivity() {

    lateinit var binding: ActivityAmateurLvlBinding
    var amateur_answers: Int = 0
    lateinit var amateur_btnAnimate: Button
    lateinit var amateur_btnSubmit: Button
    lateinit var amateur_btnRetry: Button
    lateinit var amateur_editTextAnswer: TextView
    var amateur_animationCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAmateurLvlBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_amateur_lvl)

        val amateur_btnBack3 = findViewById<Button>(R.id.amateur_btnBack3)

        amateur_btnBack3.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        val amateur_randomNumber1 = Random.nextInt(1, 101)
        val amateur_randomNumber2 = Random.nextInt(1, 101)
        val amateur_randomNumber3 = Random.nextInt(1, 101)
        val amateur_randomNumber4 = Random.nextInt(1, 101)
        val amateur_randomNumber5 = Random.nextInt(1, 101)

        val amateurtextView1: TextView = findViewById(R.id.amateur_firstRandom)
        val amateurtextView2: TextView = findViewById(R.id.amateur_secondRandom)
        val amateurtextView3: TextView = findViewById(R.id.amateur_thirdRandom)
        val amateurtextView4: TextView = findViewById(R.id.amateur_fourthRandom)
        val amateurtextView5: TextView = findViewById(R.id.amateur_fifthRandom)

        amateur_editTextAnswer = findViewById(R.id.amateur_editTextAnswer)
        amateur_editTextAnswer.visibility = View.INVISIBLE

        amateurtextView1.text = amateur_randomNumber1.toString()
        amateurtextView2.text = amateur_randomNumber2.toString()
        amateurtextView3.text = amateur_randomNumber3.toString()
        amateurtextView4.text = amateur_randomNumber4.toString()
        amateurtextView5.text = amateur_randomNumber5.toString()

        amateur_btnAnimate = findViewById(R.id.amateur_btnAnimate)
        amateur_btnSubmit = findViewById(R.id.amateur_btnSubmit)
        amateur_btnRetry = findViewById(R.id.amateur_btnRetry)
        val amateur_animation = AnimationUtils.loadAnimation(this, R.anim.fade_in_out)

        val amateur_bg1: ImageView = findViewById(R.id.amateurbg_1)
        val amateur_bg2: ImageView = findViewById(R.id.amateurbg_2)
        val amateur_bg3: ImageView = findViewById(R.id.amateurbg_3)
        val amateur_bg4: ImageView = findViewById(R.id.amateurbg_4)

        amateur_bg1.visibility = View.VISIBLE
        amateur_bg2.visibility = View.INVISIBLE
        amateur_bg3.visibility = View.INVISIBLE
        amateur_bg4.visibility = View.INVISIBLE

        val amateur_chooseLevelButton: Button = findViewById(R.id.amateur_btnBack3)
        amateur_chooseLevelButton.visibility = View.INVISIBLE

        amateur_btnAnimate.setOnClickListener {
            amateur_btnAnimate.isEnabled = false
            amateur_btnRetry.visibility = View.INVISIBLE
            amateur_answers = 0
            amateur_animationCount = 0

            amateur_animateTextViewWithDelay(amateurtextView1, amateur_animation, 1000)
            amateur_animateTextViewWithDelay(amateurtextView2, amateur_animation, 3000)
            amateur_animateTextViewWithDelay(amateurtextView3, amateur_animation, 5000)
            amateur_animateTextViewWithDelay(amateurtextView4, amateur_animation, 7000)
            amateur_animateTextViewWithDelay(amateurtextView5, amateur_animation, 9000) {
                amateur_editTextAnswer.visibility = View.VISIBLE
                amateur_btnSubmit.visibility = View.VISIBLE
                amateur_btnRetry.visibility = View.VISIBLE
                amateur_bg2.visibility = View.VISIBLE
                amateur_bg3.visibility = View.VISIBLE
                amateur_bg4.visibility = View.VISIBLE

                amateur_btnAnimate.visibility = View.INVISIBLE
                amateur_bg1.visibility = View.INVISIBLE

                amateur_chooseLevelButton.visibility = View.VISIBLE
            }
        }

        amateur_btnSubmit.setOnClickListener {
            val userAnswer = amateur_editTextAnswer.text.toString()
            if (userAnswer == amateur_answers.toString()) {
                amateur_showCorrectMessage()
            } else {
                amateur_showIncorrectMessage()
            }
        }
        amateur_btnRetry.setOnClickListener {
            amateur_resetGame()
        }
    }

    private fun amateur_animateTextViewWithDelay(
        textView: TextView,
        animation: Animation,
        delay: Long,
        onAnimationEnd: (() -> Unit)? = null
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            val number = textView.text.toString().toInt()
            amateur_answers += number
            textView.visibility = View.VISIBLE
            textView.startAnimation(animation)

            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}

                override fun onAnimationEnd(animation: Animation?) {
                    textView.visibility = View.GONE
                    amateur_animationCount++

                    if (amateur_animationCount == 5) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            amateur_editTextAnswer.visibility = View.VISIBLE
                            amateur_btnSubmit.visibility = View.VISIBLE
                            amateur_btnRetry.visibility = View.VISIBLE
                            findViewById<ImageView>(R.id.amateurbg_2).visibility = View.VISIBLE
                            findViewById<ImageView>(R.id.amateurbg_3).visibility = View.VISIBLE
                            findViewById<ImageView>(R.id.amateurbg_4).visibility = View.VISIBLE
                            onAnimationEnd?.invoke()
                        }, 100)
                    }
                }

                override fun onAnimationRepeat(animation: Animation?) {}
            })
        }, delay)
    }

    private fun amateur_showCorrectMessage() {
        findViewById<TextView>(R.id.amateur_tvCorrectMessage).visibility = View.VISIBLE
        findViewById<TextView>(R.id.amateur_tvIncorrectMessage).visibility = View.INVISIBLE
    }

    private fun amateur_showIncorrectMessage() {
        findViewById<TextView>(R.id.amateur_tvCorrectMessage).visibility = View.INVISIBLE
        findViewById<TextView>(R.id.amateur_tvIncorrectMessage).visibility = View.VISIBLE
    }

    private fun amateur_resetGame() {
        val randomNumber1 = Random.nextInt(1, 101)
        val randomNumber2 = Random.nextInt(1, 101)
        val randomNumber3 = Random.nextInt(1, 101)
        val randomNumber4 = Random.nextInt(1, 101)
        val randomNumber5 = Random.nextInt(1, 101)

        findViewById<TextView>(R.id.amateur_firstRandom).text = randomNumber1.toString()
        findViewById<TextView>(R.id.amateur_secondRandom).text = randomNumber2.toString()
        findViewById<TextView>(R.id.amateur_thirdRandom).text = randomNumber3.toString()
        findViewById<TextView>(R.id.amateur_fourthRandom).text = randomNumber4.toString()
        findViewById<TextView>(R.id.amateur_fifthRandom).text = randomNumber5.toString()

        findViewById<TextView>(R.id.amateur_editTextAnswer).text = ""
        findViewById<TextView>(R.id.amateur_editTextAnswer).visibility = View.INVISIBLE
        findViewById<Button>(R.id.amateur_btnAnimate).isEnabled = true
        findViewById<TextView>(R.id.amateur_tvCorrectMessage).visibility = View.INVISIBLE
        findViewById<TextView>(R.id.amateur_tvIncorrectMessage).visibility = View.INVISIBLE
        findViewById<Button>(R.id.amateur_btnSubmit).visibility = View.INVISIBLE
        findViewById<Button>(R.id.amateur_btnRetry).visibility = View.INVISIBLE
        findViewById<ImageView>(R.id.amateurbg_2).visibility = View.INVISIBLE
        findViewById<ImageView>(R.id.amateurbg_3).visibility = View.INVISIBLE
        findViewById<ImageView>(R.id.amateurbg_4).visibility = View.INVISIBLE
        findViewById<Button>(R.id.amateur_btnAnimate).visibility = View.VISIBLE
        findViewById<ImageView>(R.id.amateurbg_1).visibility = View.VISIBLE
        findViewById<Button>(R.id.amateur_btnBack3).visibility = View.INVISIBLE
    }
}
