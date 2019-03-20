package com.example.basicframework.ui.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.basicframework.R
import com.example.basicframework.base.BaseActivity

class WxImageActivity: BaseActivity() {

    private var imgs = ArrayList<String>()


    override fun setContentView(): Int = R.layout.activity_wx_image

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
    }

    override fun initData() {
        super.initData()
        val viewGroup = findViewById<ViewGroup>(R.id.container)
        val cnt = Math.min(imgs.size, viewGroup.childCount)
        for (i in 0..cnt){
            val imageView = viewGroup.getChildAt(i) as ImageView
            imageView.setOnClickListener {

            }
        }
    }


}