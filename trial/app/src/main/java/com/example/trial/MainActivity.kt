package com.example.trial

import CarouselRVAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)

        val demoData = listOf(
            R.drawable.novicebtnframe,
            R.drawable.expertbtnframe,
            R.drawable.amateurbtnframe
        )


        viewPager.adapter = CarouselRVAdapter(demoData)

        val marginPageTransformer = MarginPageTransformer((40 * resources.displayMetrics.density).toInt())
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(marginPageTransformer)
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.80f + r * 0.20f)
        }
        viewPager.setPageTransformer(compositePageTransformer)

        viewPager.apply {
            clipChildren = false
            clipToPadding = false
            offscreenPageLimit = 3
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
    }
}
