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
import com.android.kakaobankproject.netWork.NetWorkClient
import com.android.kakaobankproject.recyclerView.SearchAdapter
import kotlinx.coroutines.launch

class SearchFragment() : Fragment() {

    var searchList = mutableListOf<Document>()
    private val binding by lazy { FragmentSearchBinding.inflate(layoutInflater) }
    private var items = mutableListOf<Document>()
    private var search: String = "사과"
    private var searchAdapter = SearchAdapter(searchList)

    private lateinit var saveLiked: SaveLike


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
                if (!searchList[position].like) {
                    saveLiked.add(searchList[position])
                }
            }
        }

    }

    companion object {
        fun newInstance() = SearchFragment()
    }

    private fun searchData(param: HashMap<String, String>) = lifecycleScope.launch {
        val kakaoDto =
            NetWorkClient.searchNetWork.getImage("KakaoAK 5e8528dc4a83dcfa1bc04bb05cec677c", param)
        Log.d("Parsing search ::", kakaoDto.toString())
        items = kakaoDto.documents

        searchList.addAll(items)
    }

    private fun setupSearchParameter(search: String): HashMap<String, String> {
        return hashMapOf(
            "query" to search,
            "sort" to "recency",
            "page" to "1",
            "size" to "80"
        )
    }

    interface SaveLike{
        fun add(list: Document)
    }

    fun setSaveLike(callback: SaveLike){
        saveLiked = callback
    }

}