package com.example.basicframework.ui.fragment

import android.os.Bundle
import android.view.View
import com.example.basicframework.R
import com.example.basicframework.base.BaseFragment
import kotlinx.android.synthetic.main.include_bottom_sheet_view.*

class OtherFragment: BaseFragment() {
    override fun setContentView(): Int = R.layout.fragment_other

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
    }

    override fun initEvent() {
        super.initEvent()
        btn_test.setOnClickListener { showToast(activity!!,"test1") }
        btn_two.setOnClickListener { showToast(activity!!,"test2") }
        btn_test3.setOnClickListener { showToast(activity!!,"test3") }
    }
}