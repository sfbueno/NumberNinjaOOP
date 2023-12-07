package com.example.numberninja

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.numberninja.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isExpanded = false

    private val fromVolumeOffFab: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.from_volume_off_fab)
    }
    private val fromVolumeFab: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.from_volume_fab)
    }
    private val rotateClockwise: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise)
    }
    private val rotateCounterClockwise: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.rotate_counter_clockwise)
    }

    companion object {
        private var player: MediaPlayer? = null

        fun getPlayerInstance(): MediaPlayer? {
            return player
        }

        fun releasePlayerInstance() {
            player?.release()
            player = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabSettings.setOnClickListener {
            if (isExpanded) {
                shrinkFab()
            } else {
                expandFab()
            }
        }

        binding.btnStart.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        binding.btnHowToPlay.setOnClickListener {
            val intent = Intent(this, HOWTOPLAY::class.java)
            startActivity(intent)
        }
    }

    private fun shrinkFab() {
        binding.fabSettings.startAnimation(rotateCounterClockwise)
        binding.fabDown.startAnimation(fromVolumeFab)
        binding.fabVolumeUp.startAnimation(fromVolumeFab)

        binding.fabDown.visibility = View.VISIBLE
        binding.fabVolumeUp.visibility = View.VISIBLE

        isExpanded = !isExpanded
    }

    private fun expandFab() {
        binding.fabSettings.startAnimation(rotateClockwise)
        binding.fabDown.startAnimation(fromVolumeOffFab)
        binding.fabVolumeUp.startAnimation(fromVolumeOffFab)

        binding.fabDown.visibility = View.INVISIBLE
        binding.fabVolumeUp.visibility = View.INVISIBLE

        isExpanded = !isExpanded
    }

    fun play(view: View) {
        if (getPlayerInstance() == null) {
            player = MediaPlayer.create(this, R.raw.ninjamusic)
        } else {
            releasePlayerInstance()
            player = MediaPlayer.create(this, R.raw.ninjamusic)
        }
        getPlayerInstance()?.start()
    }

    fun stop(view: View) {
        releasePlayerInstance()
    }

    override fun onDestroy() {
        releasePlayerInstance()
        super.onDestroy()
    }
}