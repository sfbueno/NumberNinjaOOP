package com.example.numberninja

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
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

        imageList.add(R.drawable.numbersbg)
        imageList.add(R.drawable.novicenumberbg)
        imageList.add(R.drawable.amateurnumberbg)
        imageList.add(R.drawable.expertnumberbg)

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
                        // Launch the beginnerLVL activity
                        startActivity(Intent(this@MainActivity2, begginerLVL ::class.java))
                    }
                    1 -> {
                        // Handle click for novicenumberbg
                        // Add your code here
                        startActivity(Intent(this@MainActivity2, noviceLVL ::class.java))
                    }
                    2 -> {
                        // Handle click for amateurnumberbg
                        // Add your code here
                        startActivity(Intent(this@MainActivity2, amateurLVL ::class.java))
                    }
                    3 -> {
                        // Handle click for expertnumberbg
                        // Add your code here
                        startActivity(Intent(this@MainActivity2, expertLVL ::class.java))
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
