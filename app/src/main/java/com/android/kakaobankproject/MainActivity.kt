package com.android.kakaobankproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.kakaobankproject.databinding.ActivityMainBinding
import com.android.kakaobankproject.kakaoData.Document
import com.android.kakaobankproject.mainTab.ViewPageAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity() : AppCompatActivity(), SearchFragment.SaveLike {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private lateinit var viewModel: LikeViewModel
    private val lockerFragment = LockerFragment()
    private val viewPagerAdapter by lazy {
        ViewPageAdapter(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this@MainActivity)[LikeViewModel::class.java]

        binding.mainViewpager.adapter = viewPagerAdapter
        viewPagerAdapter.searchFragment.setSaveLike(this)
        TabLayoutMediator(binding.mainTab, binding.mainViewpager){ tab, position ->
            tab.text = viewPagerAdapter.getTitle(position)
        }.attach()

        viewModel.addList.observe(this, Observer{ newList ->
            lockerFragment.receiveList(newList)
        })
    }

    override fun add(list: Document) {
        viewModel.addItem(list)
    }

}