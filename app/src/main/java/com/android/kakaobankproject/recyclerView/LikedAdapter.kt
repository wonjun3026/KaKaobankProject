package com.android.kakaobankproject.recyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.kakaobankproject.SearchFragment
import com.android.kakaobankproject.databinding.ItemRecyclerViewGridLikeBinding
import com.bumptech.glide.Glide
import org.w3c.dom.Document

class LikedAdapter(private var likedList: MutableList<com.android.kakaobankproject.kakaoData.Document>) : RecyclerView.Adapter<LikedAdapter.Holder>() {

    interface  ItemClick{
        fun onClick(view: View, position: Int)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikedAdapter.Holder {
        val binding = ItemRecyclerViewGridLikeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d("asdfonCreateView", "${likedList.size}")
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: LikedAdapter.Holder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        val imageUrl = likedList[position].thumbnail_url
        Glide.with(holder.imageView.context)
            .load(imageUrl)
            .into(holder.imageView)
        holder.site.text = likedList[position].display_sitename
        holder.currentTime.text = likedList[position].datetime
        Log.d("asdfonBIndView", "${likedList.size}")
    }

    override fun getItemCount(): Int {
        return likedList.size
    }

    inner class Holder(binding: ItemRecyclerViewGridLikeBinding) : RecyclerView.ViewHolder(binding.root){
        var imageView = binding.imageView
        val site = binding.textView
        val currentTime = binding.currentTime
    }

    fun setLikeList(saveLike: MutableList<com.android.kakaobankproject.kakaoData.Document>){
        likedList.clear()
        likedList.addAll(saveLike)
        notifyDataSetChanged()
    }

}