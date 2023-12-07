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
import com.example.numberninja.databinding.ActivityNoviceLvlBinding
import kotlin.random.Random

class noviceLVL : AppCompatActivity() {

    lateinit var binding: ActivityNoviceLvlBinding
    var novice_answers: Int = 0
    lateinit var novice_btnAnimate: Button
    lateinit var novice_btnSubmit: Button
    lateinit var novice_btnRetry: Button
    lateinit var novice_editTextAnswer: TextView
    var novice_animationCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoviceLvlBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_novice_lvl)

        val novice_btnBack3 = findViewById<Button>(R.id.novice_btnBack3)

        novice_btnBack3.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        val novice_randomNumber1 = Random.nextInt(1, 101)
        val novice_randomNumber2 = Random.nextInt(1, 101)
        val novice_randomNumber3 = Random.nextInt(1, 101)
        val novice_randomNumber4 = Random.nextInt(1, 101)

        val novicetextView1: TextView = findViewById(R.id.novice_firstRandom)
        val novicetextView2: TextView = findViewById(R.id.novice_secondRandom)
        val novicetextView3: TextView = findViewById(R.id.novice_thirdRandom)
        val novicetextView4: TextView = findViewById(R.id.novice_fourthRandom)
        novice_editTextAnswer = findViewById(R.id.novice_editTextAnswer)
        novice_editTextAnswer.visibility = View.INVISIBLE

        novicetextView1.text = novice_randomNumber1.toString()
        novicetextView2.text = novice_randomNumber2.toString()
        novicetextView3.text = novice_randomNumber3.toString()
        novicetextView4.text = novice_randomNumber4.toString()

        novice_btnAnimate = findViewById(R.id.novice_btnAnimate)
        novice_btnSubmit = findViewById(R.id.novice_btnSubmit)
        novice_btnRetry = findViewById(R.id.novice_btnRetry)
        val novice_animation = AnimationUtils.loadAnimation(this, R.anim.fade_in_out)

        val novice_bg1: ImageView = findViewById(R.id.novicebg_1)
        val novice_bg2: ImageView = findViewById(R.id.novicebg_2)
        val novice_bg3: ImageView = findViewById(R.id.novicebg_3)
        val novice_bg4: ImageView = findViewById(R.id.novicebg_4)

        novice_bg1.visibility = View.VISIBLE
        novice_bg2.visibility = View.INVISIBLE
        novice_bg3.visibility = View.INVISIBLE
        novice_bg4.visibility = View.INVISIBLE

        val novice_chooseLevelButton: Button = findViewById(R.id.novice_btnBack3)
        novice_chooseLevelButton.visibility = View.INVISIBLE

        novice_btnAnimate.setOnClickListener {
            novice_btnAnimate.isEnabled = false
            novice_btnRetry.visibility = View.INVISIBLE
            novice_answers = 0
            novice_animationCount = 0

            novice_animateTextViewWithDelay(novicetextView1, novice_animation, 1000)
            novice_animateTextViewWithDelay(novicetextView2, novice_animation, 3000)
            novice_animateTextViewWithDelay(novicetextView3, novice_animation, 5000)
            novice_animateTextViewWithDelay(novicetextView4, novice_animation, 7000) {
                novice_editTextAnswer.visibility = View.VISIBLE
                novice_btnSubmit.visibility = View.VISIBLE
                novice_btnRetry.visibility = View.VISIBLE
                novice_bg2.visibility = View.VISIBLE
                novice_bg3.visibility = View.VISIBLE

                novice_btnAnimate.visibility = View.INVISIBLE
                novice_bg1.visibility = View.INVISIBLE

                novice_chooseLevelButton.visibility = View.VISIBLE
                novice_bg4.visibility = View.VISIBLE
            }
        }

        novice_btnSubmit.setOnClickListener {
            val userAnswer = novice_editTextAnswer.text.toString()
            if (userAnswer == novice_answers.toString()) {
                novice_showCorrectMessage()
            } else {
                novice_showIncorrectMessage()
            }
        }
        novice_btnRetry.setOnClickListener {
            novice_resetGame()
        }
    }

    private fun novice_animateTextViewWithDelay(
        textView: TextView,
        animation: Animation,
        delay: Long,
        onAnimationEnd: (() -> Unit)? = null
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            val number = textView.text.toString().toInt()
            novice_answers += number
            textView.visibility = View.VISIBLE
            textView.startAnimation(animation)

            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}

                override fun onAnimationEnd(animation: Animation?) {
                    textView.visibility = View.GONE
                    novice_animationCount++

                    if (novice_animationCount == 4) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            novice_editTextAnswer.visibility = View.VISIBLE
                            novice_btnSubmit.visibility = View.VISIBLE
                            novice_btnRetry.visibility = View.VISIBLE
                            findViewById<ImageView>(R.id.novicebg_2).visibility = View.VISIBLE
                            findViewById<ImageView>(R.id.novicebg_3).visibility = View.VISIBLE
                            findViewById<ImageView>(R.id.novicebg_4).visibility = View.VISIBLE
                            onAnimationEnd?.invoke()
                        }, 100)
                    }
                }

                override fun onAnimationRepeat(animation: Animation?) {}
            })
        }, delay)
    }

    private fun novice_showCorrectMessage() {
        findViewById<TextView>(R.id.novice_tvCorrectMessage).visibility = View.VISIBLE
        findViewById<TextView>(R.id.novice_tvIncorrectMessage).visibility = View.INVISIBLE
    }

    private fun novice_showIncorrectMessage() {
        findViewById<TextView>(R.id.novice_tvCorrectMessage).visibility = View.INVISIBLE
        findViewById<TextView>(R.id.novice_tvIncorrectMessage).visibility = View.VISIBLE
    }

    private fun novice_resetGame() {
        val randomNumber1 = Random.nextInt(1, 101)
        val randomNumber2 = Random.nextInt(1, 101)
        val randomNumber3 = Random.nextInt(1, 101)

        findViewById<TextView>(R.id.novice_firstRandom).text = randomNumber1.toString()
        findViewById<TextView>(R.id.novice_secondRandom).text = randomNumber2.toString()
        findViewById<TextView>(R.id.novice_thirdRandom).text = randomNumber3.toString()

        findViewById<TextView>(R.id.novice_editTextAnswer).text = ""
        findViewById<TextView>(R.id.novice_editTextAnswer).visibility = View.INVISIBLE
        findViewById<Button>(R.id.novice_btnAnimate).isEnabled = true
        findViewById<TextView>(R.id.novice_tvCorrectMessage).visibility = View.INVISIBLE
        findViewById<TextView>(R.id.novice_tvIncorrectMessage).visibility = View.INVISIBLE
        findViewById<Button>(R.id.novice_btnSubmit).visibility = View.INVISIBLE
        findViewById<Button>(R.id.novice_btnRetry).visibility = View.INVISIBLE
        findViewById<ImageView>(R.id.novicebg_2).visibility = View.INVISIBLE
        findViewById<ImageView>(R.id.novicebg_3).visibility = View.INVISIBLE
        findViewById<ImageView>(R.id.novicebg_4).visibility = View.INVISIBLE
        findViewById<Button>(R.id.novice_btnAnimate).visibility = View.VISIBLE
        findViewById<ImageView>(R.id.novicebg_1).visibility = View.VISIBLE
        findViewById<Button>(R.id.novice_btnBack3).visibility = View.INVISIBLE
    }
}
