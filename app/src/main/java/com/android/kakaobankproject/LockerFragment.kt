package com.android.kakaobankproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import com.android.kakaobankproject.databinding.FragmentLockerBinding
import com.android.kakaobankproject.kakaoData.Document
import com.android.kakaobankproject.recyclerView.LikedAdapter

class LockerFragment : Fragment() {
    private val binding by lazy { FragmentLockerBinding.inflate(layoutInflater) }
    var likedList = mutableListOf<com.android.kakaobankproject.kakaoData.Document>()
    private val likedAdapter = LikedAdapter(likedList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.likedRecyclerView.apply {
            adapter = likedAdapter
            layoutManager = GridLayoutManager(this@LockerFragment.context, 2)
        }
        likedAdapter.itemClick = object : LikedAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {

            }
        }
    }

    fun receiveList(list: List<Document>){
        likedList = list.toMutableList()
        likedAdapter.setLikeList(likedList)
        Log.d("LockerList", "${likedList.map { it.display_sitename }}")
    }
}