package com.example.basicframework.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.basicframework.R
import com.example.basicframework.base.BaseFragment
import com.example.basicframework.bean.GroupBean
import com.example.basicframework.ui.adapter.expand.ExpandListAdapter
import com.example.basicframework.ui.custom.NestedExpandListView
import kotlinx.android.synthetic.main.include_bottom_sheet_view.*
import kotlinx.android.synthetic.main.include_other_expandlist_view.*

class OtherFragment: BaseFragment() {

    private var expandAdapter: ExpandListAdapter?=null
    private var mlist = ArrayList<GroupBean>()
    private val load_count = 20
    private var hasMore:Boolean = false

    override fun setContentView(): Int = R.layout.fragment_other

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
    }

    override fun initData() {
        super.initData()
        expandAdapter = ExpandListAdapter(activity!!,mlist)
        expand.setAdapter(expandAdapter)
        expand.setOnloadMoreListener(object :NestedExpandListView.OnLoadMoreListener{
            override fun onLoad() {
                onLoadData()
            }

            override fun onReload() {
                onNewLoadData()
            }
        })

        loadData()
    }

    override fun initEvent() {
        super.initEvent()
        btn_test.setOnClickListener { showToast(activity!!,"test1") }
        btn_two.setOnClickListener { showToast(activity!!,"test2") }
        btn_test3.setOnClickListener { showToast(activity!!,"test3") }
    }

    private fun loadData(){
        val data = ArrayList<GroupBean>()
        for (i in 0..20){
            val bean = GroupBean()
            bean.name = "父：$i"
            data.add(bean)
        }
        hasMore = data.size>= load_count
        mlist.addAll(data)

    }

    private fun onLoadData(){
        if (hasMore){
//            Handler().postAtTime({
//                val data = ArrayList<GroupBean>()
//                for (i in 0..10){
//                    val bean = GroupBean()
//                    bean.name = "load data：$i"
//                    mlist.add(bean)
//                }
//                hasMore = data.size>= load_count
//                expandAdapter?.notifyDataSetChanged()
//                expand.onloadCompleted()
//            },5000)
            expand.setNetworkError()
        }else{
            expand.setNoMore(true)
        }
    }

    private fun onNewLoadData(){
            Handler().postAtTime({
                val data = ArrayList<GroupBean>()
                for (i in 0..10){
                    val bean = GroupBean()
                    bean.name = "load data：$i"
                    mlist.add(bean)
                }
                hasMore = data.size>= load_count
                expandAdapter?.notifyDataSetChanged()
                expand.onloadCompleted()
            },5000)
    }
}