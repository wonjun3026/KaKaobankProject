package com.android.kakaobankproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.android.kakaobankproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            searchLinearLayout.setOnClickListener{
                val searchFragment = SearchFragment() // SearchFragment의 인스턴스 생성
                setFragment(searchFragment)
            }
            lockerLinearLayout.setOnClickListener {
                val lockerFragment = LockerFragment() // LockerFragment의 인스턴스 생성
                setFragment(lockerFragment)
            }
        }
    }

    private fun setFragment(frag: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, frag)
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commit()
    }
}