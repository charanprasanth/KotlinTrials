package com.example.kotlintrials

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.kotlintrials.adapters.FragmentAdapter
import com.google.android.material.tabs.TabLayout

class TabbedActivity : AppCompatActivity(), TabbedActivityContract.View {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabbed)

        initView()
        customizeTabLayout()
    }

    override fun initView() {
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
    }

    override fun customizeTabLayout() {
//        tabLayout.addTab(tabLayout.newTab().setText("Widgets"))
//        tabLayout.addTab(tabLayout.newTab().setText("Actions"))

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val fragmentAdapter = FragmentAdapter(this, supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = fragmentAdapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}