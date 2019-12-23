package com.example.latestnews.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latestnews.R
import com.example.latestnews.model.ModelViewPager

class MainViewModel : ViewModel() {
    val _modelLiveData: MutableLiveData<List<ModelViewPager>> = MutableLiveData()
    val _loadingError by lazy { MutableLiveData<Boolean>() }
    val _loading by lazy { MutableLiveData<Boolean>() }

    fun refresh() {
        getData()
    }

    fun getData() {
        val models = ArrayList<ModelViewPager>()

        models.add(
            ModelViewPager(
                R.drawable.best_tech_news,
                "The most important technology news, developments and trends with insightful analysis and commentary"
            )
        )
        models.add(
            ModelViewPager(
                R.drawable.image_apple,
                "Apple News is an iOS and macOS app that delivers articles from newspapers, magazines, websites, and other sources."
            )
        )
        models.add(
            ModelViewPager(
                R.drawable.image_bitcoin,
                "Read the latest news on Bitcoin along with real-time Bitcoin price, technical analysis, information, guides and breaking updates."
            )
        )
        models.add(
            ModelViewPager(
                R.drawable.image_wall_street,
                "Breaking news and latest headlines from Wall Street including articles, videos, photos, and blog."
            )
        )
        models.add(ModelViewPager(R.drawable.image_business, "A business description gives a snapshot of the business you plan to run or are already running."))
        _modelLiveData.value = models
    }

}