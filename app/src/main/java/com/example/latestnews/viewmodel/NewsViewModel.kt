package com.example.latestnews.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latestnews.model.News
import com.example.latestnews.net.NewsApiService
import com.example.latestnews.utils.TAG
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class NewsViewModel(application: Application) : AndroidViewModel(application) {
    val _modelLiveData: MutableLiveData<News> = MutableLiveData()
    val _loadingError by lazy { MutableLiveData<Boolean>() }
    val _loading by lazy { MutableLiveData<Boolean>() }
    var listFake: List<News> = arrayListOf()

    private val disposable = CompositeDisposable()
    private val api = NewsApiService(application)

    fun getBitcoin(q: String, from: String, sortBy: String, key: String) {
        getBitcoinNews(q, from, sortBy, key)
    }

    private fun getBitcoinNews(q: String, from: String, sortBy: String, key: String) {
        disposable.add(
            api.getBitcoinNews(q, from, sortBy, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { Log.d(TAG,it.printStackTrace().toString())}
                .subscribeWith(object : DisposableSingleObserver<News>(){
                    override fun onSuccess(news: News) {
                        Log.d(TAG,news.articles.toString())
                        loadValue(news)
                    }
                    override fun onError(e: Throwable) {
                        loadError()
                    }
                })
        )
    }

    fun getBusinessNews(country: String, category: String, key: String) {
        disposable.add(
            api.getBusinessNews(country, category, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<News>() {
                    override fun onSuccess(news: News) {
                        loadValue(news)
                    }
                    override fun onError(e: Throwable) {
                       loadError()
                    }
                })
        )
    }

    fun getAppleNews(q: String, from: String, to: String, sortBy: String, key: String) {
        disposable.addAll(
            api.getAppleNews(q, from, to, sortBy, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<News>() {
                    override fun onSuccess(news:News) {
                       loadValue(news)
                    }
                    override fun onError(e: Throwable) {
                      loadError()
                    }
                })
        )
    }

    fun getTechNews(source: String, key: String) {
        disposable.addAll(
            api.getTechNews(source, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<News>() {
                    override fun onSuccess(news: News) {
                       loadValue(news)
                    }
                    override fun onError(e: Throwable) {
                      loadError()
                    }
                })
        )
    }

    fun getWallStreetNews(domains: String, key: String) {
        disposable.add(
            api.getWallStreetNews(domains, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<News>() {
                    override fun onSuccess(news: News) {
                        loadValue(news)
                    }
                    override fun onError(e: Throwable) {
                       loadError()
                    }
                })
        )
    }

    private fun loadError() {
        _loadingError.value = true
        _loading.value = true
    }

    private fun loadValue(news: News) {
        _loading.value = false
        _modelLiveData.value = news
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}