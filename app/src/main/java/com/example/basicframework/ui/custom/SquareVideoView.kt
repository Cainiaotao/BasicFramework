package com.example.basicframework.ui.custom

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import cn.jzvd.JzvdStd
import com.example.basicframework.R
import com.example.basicframework.bean.NewsBean
import com.example.basicframework.utils.GlideUtils
import kotlinx.android.synthetic.main.custom_item_video_view.view.*

class SquareVideoView: ConstraintLayout {
    var listener:OnClickListener?=null

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        LayoutInflater.from(context).inflate(R.layout.custom_item_video_view,this)
    }

    fun setContent(info: NewsBean){
        val url = "https://t2.hddhhn.com/uploads/tu/201810/9999/9139710e12.jpg"
        val videoUrl = "http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"
        GlideUtils.load(context,url,videoPlayer.getThumbImageView())
        videoPlayer.setAllControlsVisiblity(GONE, GONE, VISIBLE, GONE, VISIBLE, GONE, GONE)
        videoPlayer.tinyBackImageView.visibility = View.GONE
        videoPlayer.titleTextView.visibility = View.GONE
        videoPlayer.titleTextView.text = ""
        videoPlayer.setVideoStateListener(object :VideoStateListenerAdapter(){
            override fun onStartClick() {
                super.onStartClick()
                videoPlayer.setUp(videoUrl, "", JzvdStd.SCREEN_WINDOW_LIST)
                videoPlayer.startVideo()
            }

            override fun onThumb() {
                super.onThumb()
                listener?.onThumb()
            }
        })
    }

    interface OnClickListener{
        fun onThumb()
    }
}