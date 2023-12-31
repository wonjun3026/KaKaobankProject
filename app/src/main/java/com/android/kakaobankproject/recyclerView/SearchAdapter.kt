package com.android.kakaobankproject.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.kakaobankproject.databinding.ItemRecyclerViewGridBinding
import com.android.kakaobankproject.kakaoData.Document
import com.bumptech.glide.Glide

class SearchAdapter(private val searchList: MutableList<Document>) : RecyclerView.Adapter<SearchAdapter.Holder>() {

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
        }
        val imageUrl = searchList[position].thumbnail_url
        Glide.with(holder.imageView.context)
            .load(imageUrl)
            .into(holder.imageView)
        holder.site.text = searchList[position].display_sitename
        holder.currentTime.text = searchList[position].datetime
        holder.like.visibility = if(searchList[position].like) View.VISIBLE else View.GONE
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    inner class Holder(binding: ItemRecyclerViewGridBinding) : RecyclerView.ViewHolder(binding.root){
        var imageView = binding.imageView
        val site = binding.textView
        val currentTime = binding.currentTime
        var like = binding.likeImage
    }
    fun setLikeList(url: String){
        for ((idx, i) in searchList.withIndex()){
            if (url == searchList[idx].thumbnail_url){
                searchList[idx].like = false
            }
        }
        notifyDataSetChanged()
    }
}