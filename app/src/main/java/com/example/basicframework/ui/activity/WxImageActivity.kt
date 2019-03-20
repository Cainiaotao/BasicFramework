package com.example.basicframework.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.SharedElementCallback
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.basicframework.R
import com.example.basicframework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_wx_image.*

class WxImageActivity: BaseActivity() {

    private var imgs = ArrayList<String>()
    private var mReenterState: Bundle? = null
    private var viewGroup:ViewGroup?=null

    override fun setContentView(): Int = R.layout.activity_wx_image

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
    }

    override fun initData() {
        super.initData()
        imgs.add("https://pics1.baidu.com/feed/3b292df5e0fe992569ff1960c033d5db8cb171ab.jpeg?token=a2d090e6b1cdcf5c3d4378588b37b00f&s=9A8A336544D0CC65548060B300007082")
        imgs.add("https://pics1.baidu.com/feed/3b292df5e0fe992569ff1960c033d5db8cb171ab.jpeg?token=a2d090e6b1cdcf5c3d4378588b37b00f&s=9A8A336544D0CC65548060B300007082")
        viewGroup = findViewById<ViewGroup>(R.id.container)
        val cnt = Math.min(imgs.size, viewGroup!!.childCount)
        for (i in imgs.indices){
            val imageView:ImageView = viewGroup!!.getChildAt(i) as ImageView
            Glide.with(this).load(imgs[i]).into(imageView)
            imageView.setOnClickListener {
                PhotoBrowseActivity().startWithElement(this,imgs,i,imageView)
            }
        }
        setSharedElementCallback(this)
    }

    fun setSharedElementCallback(activity: Activity) {
        ActivityCompat.setExitSharedElementCallback(activity, object : SharedElementCallback() {
            override fun onMapSharedElements(names: List<String>?, sharedElements: MutableMap<String, View>?) {
                if (mReenterState != null) {
                    val index = mReenterState?.getInt("index", 0)
                    sharedElements!!.clear()
                    sharedElements["tansition_view"] = viewGroup!!.getChildAt(index!!)
                    mReenterState = null
                }
            }
        })

    }

    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)
        mReenterState = Bundle(data!!.extras)
    }


}