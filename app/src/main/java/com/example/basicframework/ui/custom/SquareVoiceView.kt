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
import kotlinx.android.synthetic.main.custom_item_pic_center_view.view.*
import kotlinx.android.synthetic.main.custom_item_video_view.view.*

class SquareVoiceView: ConstraintLayout {
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        LayoutInflater.from(context).inflate(R.layout.custom_item_voice_view,this)
    }

    fun setContent(info:NewsBean){
        if (TextUtils.isEmpty(info.textContent)){
            tv_text.visibility = View.GONE
        }else{
            tv_text.text = info.textContent
            tv_text.visibility = View.VISIBLE
        }
        setEvent()
    }

    private fun setEvent(){
        videoPlay.setAllControlsVisiblity(GONE, GONE, VISIBLE, GONE, VISIBLE, GONE, GONE)
        videoPlay.tinyBackImageView.visibility = GONE
        videoPlay.titleTextView.text = ""//清除标题,防止复用的时候出现
        videoPlay.setVideoStateListener(object :VideoStateListener{
             var isVideoParsing = false //视频是否在解析的标识

            override fun onStartClick() {
                //解析视频URL 地址
                val videoUrl = "xxxx"

                if (!TextUtils.isEmpty(videoUrl)) {
                    //如果已经解析过
                    //KLog.e("取出对应的视频地址: $videoUrl")
                    videoPlay.setUp(videoUrl, "标题", JzvdStd.SCREEN_WINDOW_LIST)
                    videoPlay.startVideo()
                    return
                }

                parseVideo()//解析视频
            }

            private fun parseVideo() {
                //("title: " + news.title)

                if (isVideoParsing) {
                    //("视频正在解析，不重复调用...")
                    return
                } else {
                    isVideoParsing = true
                }

                //隐藏开始按钮 显示加载中
                videoPlay.setAllControlsVisiblity(GONE, GONE, GONE, VISIBLE, VISIBLE, GONE, GONE)
                //helper.setVisible(R.id.ll_duration, false)//隐藏时长
               // helper.setVisible(R.id.ll_title, false)//隐藏标题栏

                //TODO 解码视频地址 详情参考 TouTiao
//                val decoder = object : VideoPathDecoder() {
//                    fun onSuccess(url: String) {
//                        //KLog.i("Video url:$url")
//                        UIUtils.postTaskSafely(Runnable {
//                            //更改视频是否在解析的标识
//                            isVideoParsing = false
//
//                            //准备播放
//                            videoPlay.setUp(url, "标题", JzvdStd.SCREEN_WINDOW_LIST)
//
////                            if (news.video_detail_info != null) {
////                                news.video_detail_info.parse_video_url = url //赋值解析后的视频地址
////                                videoPlayer.seekToInAdvance = news.video_detail_info.progress //设置播放进度
////                            }
//
//                            //开始播放
//                            videoPlay.startVideo()
//                        })
//                    }
//
//                    fun onDecodeError(errorMsg: String) {
//                        isVideoParsing = false//更改视频是否在解析的标识
//                        //隐藏加载中 显示开始按钮
//                        videoPlay.setAllControlsVisiblity(GONE, GONE, VISIBLE, GONE, VISIBLE, GONE, GONE)
//                        //UIUtils.showToast(errorMsg)
//                    }
//                }
//                decoder.decodePath(news.url)
            }

            override fun onStateNormal() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onPreparing() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStart() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onPlaying() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onPause() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onProgressChanged(progress: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onComplete() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTouch() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStartDismissControlViewTimer() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

}