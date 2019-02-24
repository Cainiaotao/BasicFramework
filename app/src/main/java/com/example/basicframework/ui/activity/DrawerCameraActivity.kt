package com.example.basicframework.ui.activity

import android.os.Bundle
import android.view.Gravity
import com.example.basicframework.R
import com.example.basicframework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_camera_main.*
import kotlinx.android.synthetic.main.activity_drawer_camera.*

class DrawerCameraActivity:BaseActivity() {
    override fun setContentView(): Int = R.layout.activity_drawer_camera

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

    }

    override fun initEvent() {
        super.initEvent()
        tv_menu.setOnClickListener {
            drawerLay.openDrawer(Gravity.LEFT)
        }
    }
}