package com.example.latestnews.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.example.latestnews.R
import com.example.latestnews.model.ModelViewPager
import kotlinx.android.synthetic.main.item_view_pager.view.*


class PagerAdapter(val models: ArrayList<ModelViewPager>, val context: Context?,val onClickListener: (Int) -> Unit ) : PagerAdapter() {


    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = models.size

    fun updateList(list: List<ModelViewPager>) {
        models.clear()
        models.addAll(list)
        notifyDataSetChanged()
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) =
        container.removeView(
            `object` as View?
        )

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_view_pager, container, false)
        view.imgCard.setImageResource(models[position].image)
        view.tvDetails.text = models[position].description
        view.setOnClickListener {onClickListener(position)}
        container.addView(view, 0)
        return view
    }

}