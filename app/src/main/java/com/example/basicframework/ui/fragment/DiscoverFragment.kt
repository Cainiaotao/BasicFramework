package com.example.basicframework.ui.fragment

import android.os.Bundle
import android.view.View
import com.example.basicframework.R
import com.example.basicframework.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_discover.*


class DiscoverFragment: BaseFragment() {
    override fun setContentView(): Int = R.layout.fragment_discover

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
    }

    override fun initData() {
        super.initData()
        tv_expand.text = getString(R.string.dummy_text1)
//        btn_expand.setOnClickListener {
//            tv_expand.onClickStatus()
//        }
    }
}