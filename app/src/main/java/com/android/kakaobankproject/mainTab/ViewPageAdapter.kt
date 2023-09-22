package com.android.kakaobankproject.mainTab

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.kakaobankproject.LockerFragment
import com.android.kakaobankproject.MainActivity
import com.android.kakaobankproject.SearchFragment

class ViewPageAdapter(
    fragmentActivity: MainActivity
) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = ArrayList<MainTabs>()
    val searchFragment = SearchFragment()

    init {
        fragments.add(MainTabs(searchFragment, "검색 결과"))
        fragments.add(MainTabs(LockerFragment(), "내 보관함"))
    }

    fun getTitle(position: Int): String {
        return fragments[position].title
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    private fun getFragment(position: Int): Fragment = fragments[position].fragment

    override fun createFragment(position: Int): Fragment {
        return getFragment(position)
    }

}