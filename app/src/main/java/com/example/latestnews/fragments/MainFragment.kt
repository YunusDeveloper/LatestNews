package com.example.latestnews.fragments

import android.animation.ArgbEvaluator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import com.example.latestnews.R
import com.example.latestnews.adapter.PagerAdapter
import com.example.latestnews.model.ModelViewPager
import com.example.latestnews.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var models: ArrayList<ModelViewPager>
    private lateinit var adapter: PagerAdapter
    private lateinit var argEvaluator: ArgbEvaluator
    private lateinit var colors: IntArray

    private lateinit var viewModel: MainViewModel
    private val loadingDetails: Observer<List<ModelViewPager>> = Observer { list ->
        list.let {
            adapter.updateList(list = list)
        }
    }

    private val loadingError: Observer<Boolean> = Observer {
    }

    private val loading: Observer<Boolean> = Observer {
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel._modelLiveData.observe(this, loadingDetails)
        viewModel._loadingError.observe(this, loadingError)
        viewModel._loading.observe(this, loading)
        viewModel.refresh()
        initDetails()//in it details
    }

    private fun initDetails() {
        // initializing arg evaluator
        argEvaluator = ArgbEvaluator()
        //initializing pager adapter
        adapter = PagerAdapter(arrayListOf(), context) { pos: Int -> onClickListener(pos) }
        viewPager.adapter = adapter
        viewPager.setPadding(130, 130, 130, 130)
        //setting page change listener to view pager

        colors = intArrayOf(
            ContextCompat.getColor(context!!, R.color.colorOne),
            ContextCompat.getColor(context!!, R.color.colorTwo),
            ContextCompat.getColor(context!!, R.color.colorThree),
            ContextCompat.getColor(context!!, R.color.colorFour),
            ContextCompat.getColor(context!!, R.color.colorFive)
        )
        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position < (adapter.count - 1) && position < (colors.size - 1)) {
                    viewPager.setBackgroundColor(
                        argEvaluator.evaluate(
                            positionOffset,
                            colors[position],
                            colors[position + 1]
                        ) as Int
                    )
                } else {
                    viewPager.setBackgroundColor(colors[colors.size - 1])
                }
            }

            override fun onPageSelected(position: Int) {}
        })
    }

    private fun onClickListener(pos: Int) {
        when (pos) {
            0 -> {
                navController.navigate(R.id.action_mainFragment_to_techCrunchNewsFragment)
            }
            1 -> {
                navController.navigate(R.id.action_mainFragment_to_appleNewsFragment)
            }
            2 -> {
                navController.navigate(R.id.action_mainFragment_to_bitcoinNewsFragment)
            }
            3 -> {
                navController.navigate(R.id.action_mainFragment_to_wallStreetNewsFragment)
            }
            4 -> {
                navController.navigate(R.id.action_mainFragment_to_businessNewsFragment)
            }

        }
    }
}


//    private fun bindingData() {
//        models = ArrayList()
//        // adding info to Model class
//        models.add(
//            ModelViewPager(
//                R.drawable.best_tech_news,
//                "The most important technology news, developments and trends with insightful analysis and commentary"
//            )
//        )
//        models.add(
//            ModelViewPager(
//                R.drawable.image_apple,
//                "Apple News is an iOS and macOS app that delivers articles from newspapers, magazines, websites, and other sources."
//            )
//        )
//        models.add(
//            ModelViewPager(
//                R.drawable.image_bitcoin,
//                "Read the latest news on Bitcoin along with real-time Bitcoin price, technical analysis, information, guides and breaking updates."
//            )
//        )
//        models.add(
//            ModelViewPager(
//                R.drawable.image_wall_street,
//                "Breaking news and latest headlines from Wall Street including articles, videos, photos, and blog."
//            )
//        )
//        models.add(ModelViewPager(R.drawable.image_business, "Hello"))
//        //initializing array of colors
//        colors = intArrayOf(
//            ContextCompat.getColor(context!!, R.color.colorOne),
//            ContextCompat.getColor(context!!, R.color.colorTwo),
//            ContextCompat.getColor(context!!, R.color.colorThree),
//            ContextCompat.getColor(context!!, R.color.colorFour),
//            ContextCompat.getColor(context!!, R.color.colorFive)
//        )
//
//    }


