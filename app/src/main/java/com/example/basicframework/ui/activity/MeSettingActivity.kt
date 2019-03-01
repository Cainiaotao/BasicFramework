package com.example.basicframework.ui.activity

import android.os.Bundle
import com.example.basicframework.R
import com.example.basicframework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_me_setting.*

class MeSettingActivity: BaseActivity() {
    override fun setContentView(): Int = R.layout.activity_me_setting

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        val tag = "<p><font color=\"#007EFA\">#测试</font></p>"
        tv_expandView.setContent(getString(R.string.new_tmp)+tag)
    }
}