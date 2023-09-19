package com.android.kakaobankproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.kakaobankproject.databinding.FragmentSearchBinding
import com.android.kakaobankproject.kakaoData.Document
import com.android.kakaobankproject.kakaoData.SearchData
import com.android.kakaobankproject.netWork.NetWorkClient
import com.android.kakaobankproject.recyclerView.SearchAdapter
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private val binding by lazy { FragmentSearchBinding.inflate(layoutInflater)}
    var items = mutableListOf<Document>()
    private var search: String = "사과"
    private var searchAdapter = SearchAdapter()
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
            layoutManager = LinearLayoutManager(this@SearchFragment.context)


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
        val kakaoDto = NetWorkClient.searchNetWork.getImage("KakaoAK 5e8528dc4a83dcfa1bc04bb05cec677c", param)
        Log.d("Parsing search ::", kakaoDto.toString())
        items = kakaoDto.documents
        SearchData.searchList = items
    }

    private fun setupSearchParameter(search: String): HashMap<String, String>{
        return hashMapOf(
            "query" to search,
            "sort" to "recency",
            "page" to "1",
            "size" to "80"
        )
    }
}