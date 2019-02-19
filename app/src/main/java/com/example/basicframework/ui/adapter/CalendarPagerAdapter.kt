package com.example.basicframework.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.ArrayList

class CalendarPagerAdapter(val mlist:ArrayList<Fragment>,fm: FragmentManager, context: Context) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return mlist[position]
    }

    override fun getCount(): Int {
        return mlist.size
    }
}