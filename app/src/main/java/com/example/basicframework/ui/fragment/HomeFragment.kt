package com.example.basicframework.ui.fragment

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.TableLayout
import com.example.basicframework.R
import com.example.basicframework.base.BaseFragment
import com.github.jdsjlzx.recyclerview.LuRecyclerView
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter

class HomeFragment :BaseFragment() {

    private var tablay:TableLayout?=null
    private var viewPager:ViewPager?=null
//    private var luRecyclerView:LuRecyclerView?=null
//    private var luAdapter:LuRecyclerViewAdapter?=null

    override fun setContentView(): Int = R.layout.fragment_home

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
        tablay = view.findViewById(R.id.home_tab)
        viewPager = view.findViewById(R.id.viewPager)
    }

    override fun initData() {
        super.initData()
    }
}