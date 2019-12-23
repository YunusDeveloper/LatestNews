package com.example.latestnews.fragments


import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latestnews.R
import com.example.latestnews.adapter.NewsAdapter
import com.example.latestnews.model.News
import com.example.latestnews.utils.API_KEY
import com.example.latestnews.utils.TAG
import com.example.latestnews.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_bitcoin_news.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class BitcoinNewsFragment : Fragment() {
    private lateinit var newsViewModel: NewsViewModel
    private val newsAdapter by lazy { NewsAdapter(arrayListOf(),activity!!.applicationContext) }
    private lateinit var currentDay:String

    private val loadDataNews: Observer<News> = Observer { list ->
        list.let {
            Log.d(TAG, list.articles.toString())
            newsAdapter.updateUsers(list.articles)
        }
    }

    private val loadingError: Observer<Boolean> = Observer {
        it.let {
            progressBar.visibility = View.GONE
        }
    }

    private val loading: Observer<Boolean> = Observer {
        it.let {
            progressBar.visibility = View.GONE
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bitcoin_news, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCurrentDate()
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsViewModel._modelLiveData.observe(this, loadDataNews)
        newsViewModel._loadingError.observe(this, loadingError)
        newsViewModel._loading.observe(this, loading)
        newsViewModel.getBitcoin("bitcoin", "2019-11-$currentDay", "publishedAt", API_KEY)
        rvBitcoin.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }

        bitcoinSwipe.setOnRefreshListener {
            bitcoinSwipe.isRefreshing = false
            newsViewModel.getBitcoin(
                "bitcoin",
                "2019-11-$currentDay",
                "publishedAt",
                API_KEY
            )
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun getCurrentDate() {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd")
        val formatted = current.format(formatter)
        currentDay = formatted.toString()
    }
}
