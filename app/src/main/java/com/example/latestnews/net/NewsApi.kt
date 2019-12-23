package com.example.latestnews.net

import com.example.latestnews.model.News
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    // BASE URL https://newsapi.org/v2/everything?q=bitcoin&from=2019-11-21&sortBy=publishedAt&apiKey=4aff91ee10694dcbb8c4c61aaaa271df
    @GET("everything")
    fun getBitcoinNews(@Query("q") q:String,
                       @Query("from") from:String,
                       @Query("sortBy") sortBy:String,
                      @Query("apiKey") key:String):Single<News>

    //BASE URL https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=4aff91ee10694dcbb8c4c61aaaa271df
    @GET("top-headlines")
    fun getBusinessNews(@Query("country") country:String,
                        @Query("category") category:String,
                       @Query("apiKey") key:String):Single<News>

    //BASE URL https://newsapi.org/v2/everything?q=apple&from=2019-12-20&to=2019-12-20&sortBy=popularity&apiKey=4aff91ee10694dcbb8c4c61aaaa271df
    @GET("everything")
    fun getAppleNews(@Query("q") q:String,
                     @Query("from") from:String,
                     @Query("to") to:String,
                     @Query("sortBy") sortBy:String,
                    @Query("apiKey") key:String):Single<News>

    //BASE URL https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=4aff91ee10694dcbb8c4c61aaaa271df
    @GET("top-headlines")
    fun getTechNews(
        @Query("sources")source:String,
        @Query("apiKey") key:String):Single<News>

    //BASE URL https://newsapi.org/v2/everything?domains=wsj.com&apiKey=4aff91ee10694dcbb8c4c61aaaa271d
    @GET("everything")
    fun getWallStreetNews(@Query("domains") domains:String,
                         @Query("apiKey") key:String):Single<News>




}