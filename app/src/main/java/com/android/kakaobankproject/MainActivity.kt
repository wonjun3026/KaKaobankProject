package com.android.kakaobankproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.kakaobankproject.databinding.ActivityMainBinding
import com.android.kakaobankproject.kakaoData.Document
import com.android.kakaobankproject.mainTab.ViewPageAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), SearchFragment.SaveLike, LockerFragment.DeleteLike{

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private lateinit var viewModel: LikeViewModel

    private val viewPagerAdapter by lazy {
        ViewPageAdapter(this)
    }
    private val lockerFragment by lazy {
        viewPagerAdapter.lockerFragment
    }
    private val searchFragment by lazy {
        viewPagerAdapter.searchFragment
    }
    private var url: String = "1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this@MainActivity)[LikeViewModel::class.java]

        binding.mainViewpager.adapter = viewPagerAdapter
        viewPagerAdapter.searchFragment.setSaveLike(this)
        viewPagerAdapter.lockerFragment.setDeleteLike(this)
        TabLayoutMediator(binding.mainTab, binding.mainViewpager){ tab, position ->
            tab.text = viewPagerAdapter.getTitle(position)
        }.attach()

        viewModel.addList.observe(this, Observer{ newList ->
            lockerFragment.receiveList(newList)
            searchFragment.removeLike(url)
        })
    }

    override fun add(list: Document) {
        viewModel.addItem(list)
    }

    override fun removeList(list: Document) {
        viewModel.removeItem(list)
        url = list.thumbnail_url
    }

}