package com.android.kakaobankproject.likedData

import com.android.kakaobankproject.kakaoData.SearchData
import org.w3c.dom.Document

object LikedData {
    var likedList = mutableListOf<com.android.kakaobankproject.kakaoData.Document>()

    private fun addLikedList(liked: com.android.kakaobankproject.kakaoData.Document){
        likedList.add(liked)
    }
    fun delLikedList(position: Int){
        likedList.removeAt(position)
        SearchData.liked[position] = "false"
    }
    fun addLiked(position: Int) {
        addLikedList(SearchData.searchList[position])
        SearchData.liked[position] = "true"
    }
}