package com.example.basicframework.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.basicframework.R
import com.example.basicframework.base.BaseActivity
import com.example.basicframework.ui.adapter.CalendarPagerAdapter
import kotlinx.android.synthetic.main.activity_calendar.*
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.widget.TextView
import com.example.basicframework.ui.fragment.calender.CalenderFragment
import com.example.basicframework.ui.fragment.calender.DingFragment
import com.example.basicframework.ui.fragment.calender.TaskFragment


class CalendarActivity :BaseActivity() {

    private var pagerAdapter: CalendarPagerAdapter?=null
    private var titles = ArrayList<String>()
    private var fragments = ArrayList<Fragment>()
    private var tabListener:TabLayout.OnTabSelectedListener?=null
    override fun setContentView(): Int = R.layout.activity_calendar

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        //setStatusBarColor()
    }

    override fun initData() {
        super.initData()
        initTableView()
    }

    private fun initTableView(){
        titles.add("日程")
        titles.add("DING")
        titles.add("任务")
        fragments.add(CalenderFragment())
        fragments.add(DingFragment())
        fragments.add(TaskFragment())
        pagerAdapter = CalendarPagerAdapter(fragments,supportFragmentManager,this)
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.tabMode = TabLayout.MODE_FIXED
        for (item in titles.indices) {
            val tab:TabLayout.Tab? = tabLayout.getTabAt(item)
            val view = LayoutInflater.from(this).inflate(R.layout.tab_custom_calender, null)
            val tv_tab :TextView= view.findViewById(R.id.tv_tab)
            tv_tab.text = titles[item]
            tab?.customView =  view

            if (item == 0){
                tv_tab.setTextColor(ContextCompat.getColor(this,R.color.colorAccent))
            }else{
                tv_tab.setTextColor(ContextCompat.getColor(this,R.color.colorWhite))
            }
        }
    }

    override fun initEvent() {
        super.initEvent()
        tabLayout.addOnTabSelectedListener(onTabSelectedListener)
    }

    private val onTabSelectedListener:TabLayout.OnTabSelectedListener = object :TabLayout.OnTabSelectedListener{
        override fun onTabReselected(item: TabLayout.Tab?) {
        }

        override fun onTabUnselected(item: TabLayout.Tab?) {
            val tv_title:TextView? = item?.customView?.findViewById(R.id.tv_tab)
            tv_title?.setTextColor(ContextCompat.getColor(this@CalendarActivity,R.color.colorWhite))
        }

        override fun onTabSelected(item: TabLayout.Tab?) {
            val tv_title:TextView? = item?.customView?.findViewById(R.id.tv_tab)
            tv_title?.setTextColor(ContextCompat.getColor(this@CalendarActivity,R.color.colorAccent))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        tabLayout.removeOnTabSelectedListener(onTabSelectedListener)
    }
}