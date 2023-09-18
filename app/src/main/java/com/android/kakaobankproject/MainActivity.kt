package com.android.kakaobankproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.android.kakaobankproject.databinding.ActivityMainBinding
import com.android.kakaobankproject.kakaoData.Document
import com.android.kakaobankproject.kakaoData.KakaoDto
import com.android.kakaobankproject.kakaoData.SearchData
import com.android.kakaobankproject.netWork.NetWorkClient
import kotlinx.coroutines.launch

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