package com.example.latestnews.fragments

import android.os.Bundle
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
import com.example.latestnews.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_business_news.*

class BusinessNewsFragment : Fragment() {
    private lateinit var newsViewModel: NewsViewModel
    private val newsAdapter by lazy { NewsAdapter(arrayListOf(), activity!!.applicationContext) }

    private val loadDataNews: Observer<News> = Observer { list ->
        list.let {
            newsAdapter.updateUsers(list.articles)
        }
    }

    private val loadingError: Observer<Boolean> = Observer {
        it.let {
            pbBusiness.visibility = View.GONE
        }
    }

    private val loading: Observer<Boolean> = Observer {
        it.let {
            pbBusiness.visibility = View.GONE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsViewModel._modelLiveData.observe(this, loadDataNews)
        newsViewModel._loadingError.observe(this, loadingError)
        newsViewModel._loading.observe(this, loading)
        newsViewModel.getBusinessNews("us", "business", API_KEY)

        rvBusiness.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }

        businessSwipe.setOnRefreshListener {
            businessSwipe.isRefreshing = false
            newsViewModel.getBusinessNews("us", "business", API_KEY)
        }
    }
}
