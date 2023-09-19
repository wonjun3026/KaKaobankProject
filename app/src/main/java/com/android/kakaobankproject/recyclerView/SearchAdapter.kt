package com.android.kakaobankproject.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.kakaobankproject.kakaoData.KakaoDto
import com.android.kakaobankproject.kakaoData.SearchData.searchList
import com.android.kakaobankproject.databinding.ItemRecyclerViewGridBinding
import com.android.kakaobankproject.kakaoData.SearchData
import com.bumptech.glide.Glide

class SearchAdapter() : RecyclerView.Adapter<SearchAdapter.Holder>() {

    interface ItemClick{
        fun onClick(view: View, position: Int)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.Holder {
        val binding = ItemRecyclerViewGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: SearchAdapter.Holder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
            val imageUrl = searchList[position].imaga_url
            Glide.with(holder.imageView.context)
                .load(imageUrl)
                .into(holder.imageView)
            holder.site.text = SearchData.searchList[position].display_sitename
            holder.currentTime.text = SearchData.searchList[position].datetime
        }
    }

    override fun getItemCount(): Int {
        return SearchData.searchList.size
    }

    inner class Holder(binding: ItemRecyclerViewGridBinding) : RecyclerView.ViewHolder(binding.root){
        var imageView = binding.imageView
        val site = binding.textView
        val currentTime = binding.currentTime
    }
}