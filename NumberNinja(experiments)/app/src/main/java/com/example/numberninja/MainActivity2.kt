package com.example.numberninja

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class MainActivity2 : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var imageList: ArrayList<Int>
    private lateinit var adapter: ImageAdapter

    private var lastPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Initialize ViewPager2 and set up other components
        init()
        setUpTransformer()
        setUpPageChangeListener()
        setUpImageClickListeners()
    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        viewPager2.setPageTransformer(transformer)
    }

    private fun init() {
        viewPager2 = findViewById(R.id.viewPager2)
        imageList = ArrayList()

        imageList.add(R.drawable.beginnerlevel)
        imageList.add(R.drawable.novicelevel)
        imageList.add(R.drawable.amateurlevel)
        imageList.add(R.drawable.expertlevel)

        adapter = ImageAdapter(imageList, viewPager2)

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 1
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    private fun setUpPageChangeListener() {
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                lastPage = position
            }
        })
    }

    private fun setUpImageClickListeners() {
        adapter.setOnItemClickListener(object : ImageAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                when (position) {
                    0 -> {
                        // Handle click for numbersbg
                        // Launch the transitioner1 activity first
                        val transitionIntent = Intent(this@MainActivity2, transitioner1::class.java)
                        startActivity(transitionIntent)

                        // Delay the launch of BeginnerLVL after transitioner1
                        val handler = Handler(Looper.getMainLooper())
                        handler.postDelayed({
                            val beginnerIntent = Intent(this@MainActivity2, begginerLVL::class.java)
                            startActivity(beginnerIntent)
                        }, 5000) // Delay for 2 seconds (adjust as needed)
                    }
                    // Handle other positions as needed
                    1 -> {
                        // Handle click for novicenumberbg
                        // Add your code here
                        val transitionIntent2 = Intent(this@MainActivity2, transitioner2::class.java)
                        startActivity(transitionIntent2)

                        // Delay the launch of BeginnerLVL after transitioner1
                        val handler = Handler(Looper.getMainLooper())
                        handler.postDelayed({
                            val beginnerIntent = Intent(this@MainActivity2, noviceLVL::class.java)
                            startActivity(beginnerIntent)
                        }, 5000) // Delay for 2 seconds (adjust as needed)
                    }
                    2 -> {
                        // Handle click for amateurnumberbg
                        // Add your code here
                        val transitionIntent3 = Intent(this@MainActivity2, transitioner3::class.java)
                        startActivity(transitionIntent3)

                        // Delay the launch of BeginnerLVL after transitioner1
                        val handler = Handler(Looper.getMainLooper())
                        handler.postDelayed({
                            val beginnerIntent = Intent(this@MainActivity2, amateurLVL::class.java)
                            startActivity(beginnerIntent)
                        }, 5000) // Delay for 2 seconds (adjust as needed)
                    }
                    3 -> {
                        // Handle click for expertnumberbg
                        // Add your code here
                        val transitionIntent4 = Intent(this@MainActivity2, transitioner4::class.java)
                        startActivity(transitionIntent4)

                        // Delay the launch of BeginnerLVL after transitioner1
                        val handler = Handler(Looper.getMainLooper())
                        handler.postDelayed({
                            val beginnerIntent = Intent(this@MainActivity2, expertLVL::class.java)
                            startActivity(beginnerIntent)
                        }, 5000) // Delay for 2 seconds (adjust as needed)
                    }
                }
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        viewPager2.isUserInputEnabled = true
    }

    override fun onBackPressed() {
        if (lastPage == imageList.size - 1) {
            viewPager2.isUserInputEnabled = false
        } else {
            super.onBackPressed()
        }
    }
}
