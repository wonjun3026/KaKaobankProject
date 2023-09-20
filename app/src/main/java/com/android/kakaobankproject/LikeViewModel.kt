package com.android.kakaobankproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.kakaobankproject.kakaoData.Document

class LikeViewModel : ViewModel() {
    val addList: MutableLiveData<List<Document>> by lazy {
        MutableLiveData<List<Document>>(emptyList())
    }

    fun addItem(item: Document) {
        val currentList = addList.value?.toMutableList() ?: mutableListOf()
        currentList.add(item)
        addList.value = currentList
    }

    fun removeItem(item: Document) {
        val currentList = addList.value?.toMutableList() ?: mutableListOf()
        currentList.remove(item)
        addList.value = currentList
    }
}
