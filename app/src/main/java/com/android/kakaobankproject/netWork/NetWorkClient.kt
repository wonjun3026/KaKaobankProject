package com.android.kakaobankproject.netWork

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetWorkClient {
    private const val SEARCH_BASE_URL = "https://dapi.kakao.com/v2/search/"

    private fun createOkHttPClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20,TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .build()
    }

    private val searchRetrofit = Retrofit.Builder()
        .baseUrl(SEARCH_BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(
            createOkHttPClient()
        ).build()

    val searchNetWork: NetWorkInterface = searchRetrofit.create(NetWorkInterface::class.java)
}