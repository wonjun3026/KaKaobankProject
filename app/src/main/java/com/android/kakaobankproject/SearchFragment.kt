package com.android.kakaobankproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.android.kakaobankproject.databinding.FragmentSearchBinding
import com.android.kakaobankproject.kakaoData.Document
import com.android.kakaobankproject.kakaoData.SearchData
import com.android.kakaobankproject.kakaoData.SearchData.liked
import com.android.kakaobankproject.kakaoData.SearchData.searchList
import com.android.kakaobankproject.likedData.LikedData
import com.android.kakaobankproject.netWork.NetWorkClient
import com.android.kakaobankproject.recyclerView.LikedAdapter
import com.android.kakaobankproject.recyclerView.SearchAdapter
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private val binding by lazy { FragmentSearchBinding.inflate(layoutInflater) }
    private var items = mutableListOf<Document>()
    private var search: String = "사과"
    private var searchAdapter = SearchAdapter()
    private val likedAdapter = LikedAdapter()

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

        binding.searchButton.setOnClickListener {
            search = binding.editSearch.text.toString()
            searchData(setupSearchParameter(search))
            searchAdapter.notifyDataSetChanged()
        }

        binding.searchRecyclerView.apply {
            adapter = searchAdapter
            layoutManager = GridLayoutManager(this@SearchFragment.context, 2)

        }
        searchAdapter.itemClick = object : SearchAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                if (liked[position] == "false") {
                    LikedData.addLiked(position)
                    likedAdapter.notifyDataSetChanged()
                }
            }

        }

    }

    //    companion object {
//
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            SearchFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
    private fun searchData(param: HashMap<String, String>) = lifecycleScope.launch {
        var i: Int = 0
        val kakaoDto =
            NetWorkClient.searchNetWork.getImage("KakaoAK 5e8528dc4a83dcfa1bc04bb05cec677c", param)
        Log.d("Parsing search ::", kakaoDto.toString())
        items = kakaoDto.documents

        while (80 >= i) {
            liked.add("false")
            i++
        }
        searchList = items
    }

    private fun setupSearchParameter(search: String): HashMap<String, String> {
        return hashMapOf(
            "query" to search,
            "sort" to "recency",
            "page" to "1",
            "size" to "80"
        )
    }
}