package com.example.basicframework.ui.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.basicframework.R
import com.example.basicframework.base.BaseFragment
import com.example.basicframework.base.bus.OpenDrawerBus
import com.example.basicframework.constans.Constant
import com.example.basicframework.ui.adapter.HomePagerAdapter
import com.example.basicframework.ui.fragment.home.NewsListFragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.greenrobot.eventbus.EventBus

class HomeFragment :BaseFragment() {

    private var tablay: TabLayout?=null
    private var viewPager:ViewPager?=null
    private var titles = ArrayList<String>()
    private var homeAdapter: HomePagerAdapter?=null
    private var fragments = ArrayList<NewsListFragment>()

    override fun setContentView(): Int = R.layout.fragment_home

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
        tablay = view.findViewById(R.id.home_tab)
        viewPager = view.findViewById(R.id.viewPager)
    }

    override fun initData() {
        super.initData()
        initConfigData()
        initFragments()
    }

    private fun initConfigData(){
        //TODO 初始化配置
        titles.addAll(resources.getStringArray(R.array.squares))
    }

    private fun initFragments(){
        titles.forEach {
            val fragment = NewsListFragment()
            val bundle = Bundle()
            bundle.putString(Constant.SQUARES_CODE, it)
            fragment.arguments = bundle
            fragments.add(fragment)
        }
        homeAdapter = HomePagerAdapter(fragments,activity!!.supportFragmentManager,activity!!)
        viewPager?.adapter = homeAdapter
        tablay?.setupWithViewPager(viewPager)//自定义tab 出现tab空白
        for (item in titles.indices){
            val tab:TabLayout.Tab?= tablay?.getTabAt(item)
            val view = LayoutInflater.from(activity!!).inflate(R.layout.tab_custom_home, null)
            val tv_tab : TextView = view.findViewById(R.id.tv_tabHome)
            tv_tab.text = titles[item]
            tab?.customView =  view
            //tablay?.addTab(tab!!)
            if (item == 0){
                tv_tab.setTextColor(ContextCompat.getColor(activity!!,R.color.colorAccent))
            }else{
                tv_tab.setTextColor(ContextCompat.getColor(activity!!,R.color.colorBlack))
            }
        }

    }

    override fun initEvent() {
        super.initEvent()
        tablay?.addOnTabSelectedListener(onTabSelectedListener)
        tv_menu.setOnClickListener { openDrawerView() }
    }

    private val onTabSelectedListener:TabLayout.OnTabSelectedListener = object :TabLayout.OnTabSelectedListener{
        override fun onTabReselected(item: TabLayout.Tab?) {
        }

        override fun onTabUnselected(item: TabLayout.Tab?) {
            val tv_title:TextView? = item?.customView?.findViewById(R.id.tv_tabHome)
            tv_title?.setTextColor(ContextCompat.getColor(activity!!,R.color.colorBlack))
        }

        override fun onTabSelected(item: TabLayout.Tab?) {
            val tv_title:TextView? = item?.customView?.findViewById(R.id.tv_tabHome)
            tv_title?.setTextColor(ContextCompat.getColor(activity!!,R.color.colorAccent))
//            viewPager?.currentItem = item!!.position
        }

    }

    private fun openDrawerView(){
        EventBus.getDefault().post(OpenDrawerBus(true))
    }

    override fun onDestroy() {
        super.onDestroy()
        tablay?.removeOnTabSelectedListener(onTabSelectedListener)
    }
}