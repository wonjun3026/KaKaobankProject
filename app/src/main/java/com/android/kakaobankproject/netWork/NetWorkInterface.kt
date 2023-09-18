package com.android.kakaobankproject.netWork

import com.android.kakaobankproject.kakaoData.KakaoDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NetWorkInterface {
    @GET("image")
    suspend fun getImage(@Header("Authorization") key : String, @QueryMap param: HashMap<String, String>): KakaoDto
}