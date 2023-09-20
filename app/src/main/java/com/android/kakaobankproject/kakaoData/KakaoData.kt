package com.android.kakaobankproject.kakaoData

import com.google.gson.annotations.SerializedName

data class KakaoDto(
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("documents")
    val documents: MutableList<Document>
)
data class Meta(
    val total_count: Int,
    val pageable_count: Int,
    val is_end: Boolean
)

data class Document(
    val collection: String,
    val thumbnail_url: String,
    val imaga_url: String,
    val width: Int,
    val height: Int,
    val display_sitename: String,
    val doc_url: String,
    val datetime: String,
    var like: Boolean = false
)
