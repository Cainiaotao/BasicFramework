package com.example.basicframework.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.basicframework.R
import com.example.basicframework.base.BaseFragment
import com.example.basicframework.ui.activity.WxImageActivity
import com.example.basicframework.utils.FollowCountUtils
import kotlinx.android.synthetic.main.fragment_discover.*


class DiscoverFragment: BaseFragment() {
    override fun setContentView(): Int = R.layout.fragment_discover

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
    }

    override fun initData() {
        super.initData()
        btn1.setOnClickListener {
            startActivity(Intent(activity!!,WxImageActivity::class.java))
        }
    }
}