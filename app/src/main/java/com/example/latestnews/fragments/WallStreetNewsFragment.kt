package com.example.latestnews.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.latestnews.R
import com.example.latestnews.adapter.NewsAdapter
import com.example.latestnews.model.News
import com.example.latestnews.utils.API_KEY
import com.example.latestnews.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_bitcoin_news.*
import kotlinx.android.synthetic.main.fragment_tech_crunch_news.*
import kotlinx.android.synthetic.main.fragment_wall_street_news.*

/**
 * A simple [Fragment] subclass.
 */
class WallStreetNewsFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel
    private val newsAdapter by lazy { NewsAdapter(arrayListOf(), activity!!.applicationContext) }

    private val loadDataNews: Observer<News> = Observer{ list->
        list.let {
            newsAdapter.updateUsers(list.articles)
        }
    }

    private val loadingError: Observer<Boolean> = Observer {
        it.let {
            pbWall.visibility = View.GONE
        }
    }

    private val loading: Observer<Boolean> = Observer {
        it.let {
            pbWall.visibility = View.GONE
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wall_street_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsViewModel._modelLiveData.observe(this, loadDataNews)
        newsViewModel._loadingError.observe(this, loadingError)
        newsViewModel._loading.observe(this, loading)
        newsViewModel.getWallStreetNews("wsj.com", API_KEY)

        rvWall.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }

        wallSwipe.setOnRefreshListener {
            wallSwipe.isRefreshing = false
            newsViewModel.getWallStreetNews("wsj.com", API_KEY)
        }
    }
}
