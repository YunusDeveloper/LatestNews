package com.example.latestnews.net

import android.content.Context
import com.example.latestnews.model.News
import com.example.latestnews.utils.BASE_URL
import com.readystatesoftware.chuck.ChuckInterceptor
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiService(context: Context) {

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckInterceptor(context))
        .build()

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private val newsApi: NewsApi = getRetrofit().create(NewsApi::class.java)


    fun getBitcoinNews(q: String, from: String, sortBy: String, key: String): Single<News> {
        return newsApi.getBitcoinNews(q, from, sortBy, key)
    }

    fun getBusinessNews(country: String, category: String, key: String): Single<News> {
        return newsApi.getBusinessNews(country, category, key)
    }

    fun getAppleNews(
        q: String,
        from: String,
        to: String,
        sortBy: String,
        key: String
    ): Single<News> {
        return newsApi.getAppleNews(q, from, to, sortBy, key)
    }

    fun getTechNews(source: String, key: String): Single<News> {
        return newsApi.getTechNews(source, key)
    }

    fun getWallStreetNews(domains: String, key: String): Single<News> {
        return newsApi.getWallStreetNews(domains, key)
    }
}