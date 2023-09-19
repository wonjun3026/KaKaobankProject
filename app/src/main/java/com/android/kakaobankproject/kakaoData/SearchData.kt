package com.android.kakaobankproject.kakaoData

object SearchData {
    var searchList = mutableListOf<Document>(
        Document(
            "news",
            "https://search2.kakaocdn.net/argon/130x130_85_c/36hQpoTrVZp",
            "http://t1.daumcdn.net/news/201706/21/kedtv/20170621155930292vyyx.jpg",
            540,
            457,
            "한국경제TV",
            "http://v.media.daum.net/v/20170621155930002",
            "2017-06-21T15:59:30.000+09:00"
        )
    )

    fun retrieveData(): List<Document> {
        return searchList
    }

}