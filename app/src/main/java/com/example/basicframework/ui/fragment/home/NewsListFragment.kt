package com.example.basicframework.ui.fragment.home

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import com.example.basicframework.R
import com.example.basicframework.base.LazyLoadFragment
import com.example.basicframework.bean.NewsBean
import com.example.basicframework.bean.UserInfo
import com.example.basicframework.constans.Constant
import com.example.basicframework.ui.adapter.recycler.NewsListAdapter
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_news_list.*

class NewsListFragment:LazyLoadFragment(), SwipeRefreshLayout.OnRefreshListener {


    private var pagerModel:String=""
    private var newsAdapter: NewsListAdapter?=null
    private var luAdapter:LuRecyclerViewAdapter?=null
    private var newsList = ArrayList<NewsBean>()
    private val REQUEST_COUNT = 30


    override fun setContentView(): Int = R.layout.fragment_news_list

    override fun onFragmentFirstVisible() {
        initConfigData()
        initListView()
    }

    private fun initConfigData(){
        val arg = arguments?.getString(Constant.SQUARES_CODE)
        if (arg!=null && !TextUtils.isEmpty(arg)){
            pagerModel = arg
        }
    }

    private fun initListView(){
        newsAdapter = NewsListAdapter(activity!!,newsList)
        luAdapter = LuRecyclerViewAdapter(newsAdapter)
        recyclerView.layoutManager = LinearLayoutManager(activity!!)
        recyclerView.adapter = luAdapter
        recyclerView.setOnLoadMoreListener { onLoadMoreData() }
        onRefresh()
    }

    override fun initEvent() {
        super.initEvent()
        swipe.setOnRefreshListener(this)
    }

    private fun onRefreshFinish(){
        recyclerView.refreshComplete(REQUEST_COUNT)
        luAdapter?.notifyDataSetChanged()
        swipe.isRefreshing = false
    }

    override fun onRefresh() {
        swipe.isRefreshing = true
        for (i in 0 until 20){
            newsList.add(NewsBean(UserInfo("name:$i"),"jhsdhasdajsdhajsdasdh",null,false,false))
        }
        newsList.add(0, NewsBean(null,"",null,false,false))
        newsList.add(2,NewsBean(UserInfo("name:pic1"),"居中大图+文本", arrayListOf(1),false,false))
        newsList.add(4,NewsBean(UserInfo("name:pic2"),"",arrayListOf(1),false,false))
        newsList.add(6,NewsBean(UserInfo("name:pic3"),"居中大图先擦是大阿斯顿爱仕达稍等a",arrayListOf(1),false,false))

        newsList.add(3,NewsBean(UserInfo("name:More1"),"2图 + 文本",arrayListOf(1,2),false,false))
        newsList.add(5,NewsBean(UserInfo("name:More2"),"4图 + 文本",arrayListOf(1,2,3,4),false,false))
        newsList.add(7,NewsBean(UserInfo("name:More2"),"",arrayListOf(1,2,3,4),false,false))

        newsList.add(8,NewsBean(UserInfo("name:Voice1"),"",null,true,false))
        newsList.add(9,NewsBean(UserInfo("name:Voice2"),"语音 + 文本",null,true,false))

        newsList.add(10,NewsBean(UserInfo("name:Video1"),"",null,false,true))
        newsList.add(11,NewsBean(UserInfo("name:Video2"),"视频 + 文本",null,false,true))
        onRefreshFinish()
    }
    private fun onLoadMoreData(){

    }

}