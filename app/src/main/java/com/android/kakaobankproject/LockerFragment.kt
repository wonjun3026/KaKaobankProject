package com.android.kakaobankproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.android.kakaobankproject.databinding.FragmentLockerBinding
import com.android.kakaobankproject.likedData.LikedData
import com.android.kakaobankproject.recyclerView.LikedAdapter

class LockerFragment : Fragment() {
    private val binding by lazy { FragmentLockerBinding.inflate(layoutInflater) }
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
        binding.likedRecyclerView.apply {
            adapter = likedAdapter
            layoutManager = GridLayoutManager(this@LockerFragment.context, 2)
        }
        likedAdapter.itemClick = object :LikedAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                LikedData.delLikedList(position)
                likedAdapter.notifyDataSetChanged()
            }

        }
    }

//    companion object {
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            LockerFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}