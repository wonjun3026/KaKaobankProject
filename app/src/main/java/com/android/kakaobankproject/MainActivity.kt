package com.android.kakaobankproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.kakaobankproject.databinding.ActivityMainBinding
import com.android.kakaobankproject.mainTab.ViewPageAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private val viewPagerAdapter by lazy {
        ViewPageAdapter(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.mainViewpager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.mainTab, binding.mainViewpager){ tab, position ->
            tab.text = viewPagerAdapter.getTitle(position)
        }.attach()
    }
}