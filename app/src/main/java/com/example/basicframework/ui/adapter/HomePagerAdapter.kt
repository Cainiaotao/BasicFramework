package com.example.basicframework.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import com.example.basicframework.ui.fragment.home.NewsListFragment
import java.util.ArrayList

class HomePagerAdapter(val mlist: ArrayList<NewsListFragment>, fm: FragmentManager, context: Context) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return mlist[position]
    }

    override fun getCount(): Int {
        return mlist.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //super.destroyItem(container, position, `object`)
    }
}