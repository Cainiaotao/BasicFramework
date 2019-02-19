package com.example.basicframework.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.basicframework.R
import com.example.basicframework.base.BaseFragment
import com.example.basicframework.bean.MeViewContent
import com.example.basicframework.bean.enums.MeItemType
import com.example.basicframework.bean.enums.MeViewType
import com.example.basicframework.ui.activity.CalendarActivity
import com.example.basicframework.ui.adapter.MeListAdapter

class MeFragment: BaseFragment() {

    private var meAdapter: MeListAdapter?=null
    private var recyclerView:RecyclerView?=null

    override fun setContentView(): Int = R.layout.fragment_me

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
    }

    override fun initData() {
        super.initData()
        val data = arrayListOf(
            MeViewContent(MeViewType.One,0,"Me",MeItemType.Info),
            MeViewContent(MeViewType.TWo,R.mipmap.btn_me_data,"支付",MeItemType.Other),
            MeViewContent(MeViewType.TWo,R.mipmap.btn_me_data_day,"收藏",MeItemType.Other),
            MeViewContent(MeViewType.TWo,R.mipmap.btn_me_faq,"相册",MeItemType.Other),
            MeViewContent(MeViewType.TWo,R.mipmap.btn_me_faq_new,"卡包",MeItemType.Other),
            MeViewContent(MeViewType.TWo,R.mipmap.btn_me_faq_new,"表情",MeItemType.Other),
            MeViewContent(MeViewType.TWo,R.mipmap.btn_me_settings,"日历",MeItemType.Calender),
            MeViewContent(MeViewType.TWo,R.mipmap.btn_me_settings,"设置",MeItemType.Setting))
        meAdapter = MeListAdapter(activity!!,data)
        meAdapter?.listener = object :MeListAdapter.OnItemClickListener{
            override fun OnItemClick(type: MeItemType) {
               when(type){
                   MeItemType.Calender->{startActivity(Intent(activity!!,CalendarActivity::class.java))}
                   MeItemType.Setting->{}
                   MeItemType.Other->{}
                   MeItemType.Info->{}
               }
            }
        }
        recyclerView?.layoutManager = LinearLayoutManager(activity!!)
        recyclerView?.adapter = meAdapter
    }

    override fun initEvent() {
        super.initEvent()
    }
}