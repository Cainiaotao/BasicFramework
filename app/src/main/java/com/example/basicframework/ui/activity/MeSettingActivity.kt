package com.example.basicframework.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.basicframework.R
import com.example.basicframework.base.BaseActivity
import com.example.basicframework.ui.custom.TopicExpandTextView
import com.example.basicframework.ui.flowlayout.FlowLayout
import com.example.basicframework.ui.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.activity_me_setting.*

class MeSettingActivity: BaseActivity() {


    private var mlist = arrayOf("Hello", "Android", "Weclome Hi ", "测试", "吉他", "日本",
        "中国", "Weclome", "Button ImageView", "抖音", "神曲",
        "四叶草", "甜甜的恋爱", "想恋爱了", "单身狗")

    private var tagAdapter:TagAdapter<String>?=null

    override fun setContentView(): Int = R.layout.activity_me_setting

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

    }

    override fun initData() {
        super.initData()
        showExpandView()
        showFlowlayout()
    }

    private fun showExpandView(){
        val tag = "<p><font color=\"#007EFA\">#测试</font></p>"
        tv_expandView.setContent(getString(R.string.new_tmp))
        tv_expandView.setSpanContext("#测试自定义标签点击")
        tv_expandView.setSpanContext("#北京")
        tv_expandView.setSpanContext("#吉他")
        tv_expandView.setOnLabelClickListener(object :TopicExpandTextView.OnLabelClickListener{
            override fun onSelectLabel(label: String) {
                showToast(this@MeSettingActivity,label)
            }
        })
    }

    private fun showFlowlayout(){
        val mInflater = LayoutInflater.from(this@MeSettingActivity)
        tagAdapter = object :TagAdapter<String>(mlist){
            override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
                val view= mInflater.inflate(R.layout.include_text_view,tag_fll,false)
                val tv:TextView = view.findViewById(R.id.tv_test)
                tv.text = t
                return view
            }
        }
        tag_fll.adapter = tagAdapter
        tag_fll.setOnTagClickListener { view, position, parent ->
            showToast(this,mlist[position])
            return@setOnTagClickListener true
        }
    }

}