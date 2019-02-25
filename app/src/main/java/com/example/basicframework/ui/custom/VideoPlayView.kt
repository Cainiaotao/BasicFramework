package com.example.basicframework.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import cn.jzvd.Jzvd
import cn.jzvd.JzvdStd
import com.example.basicframework.R

class VideoPlayView  : JzvdStd {

    private var mListener:VideoStateListener?=null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun init(context: Context?) {
        super.init(context)
        //TODO 初始化控件
    }

    fun setVideoStateListener(listener:VideoStateListener){
        this.mListener = listener
    }

    override fun getLayoutId(): Int {
        return R.layout.jz_layout_std
    }

    //取得各种按钮的点击事件
    override fun onClick(v: View?) {
        when(v?.id){
            cn.jzvd.R.id.start->{
                if (currentState == Jzvd.CURRENT_STATE_IDLE || currentState == Jzvd.CURRENT_STATE_NORMAL){
                    //闲置状态可点击播放
                    if (mListener != null) {
                        mListener!!.onStartClick()
                        return
                    }
                }
            }
        }
        super.onClick(v)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        //取得手势操作
        if (mListener!=null){
            mListener?.onTouch()
        }
        return super.onTouch(v, event)
    }

    override fun startVideo() {
        if (mListener!=null){
            mListener?.onStart()
        }
        super.startVideo()
    }

    override fun onStateNormal() {
        if (mListener!=null){
            mListener?.onStateNormal()
        }
        super.onStateNormal()
    }

    override fun onStatePreparing() {
        if (mListener!=null){
            mListener?.onPreparing()
        }
        super.onStatePreparing()
    }

    override fun onStatePlaying() {
        if (mListener!=null){
            mListener?.onPlaying()
        }
        super.onStatePlaying()
    }

    override fun onStatePause() {
        if (mListener!=null){
            mListener?.onPause()
        }
        super.onStatePause()
    }

    override fun onStateError() {
        super.onStateError()
    }

    override fun onStateAutoComplete() {
        if (mListener != null) {
            mListener?.onComplete()
        }
        super.onStateAutoComplete()
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (mListener != null) {
            mListener?.onProgressChanged(progress)
        }
        super.onProgressChanged(seekBar, progress, fromUser)
    }

    override fun onInfo(what: Int, extra: Int) {
        super.onInfo(what, extra)
       // KLog.i(Jzvd.TAG, "onInfo...")
    }

    override fun onError(what: Int, extra: Int) {
        super.onError(what, extra)
    }

    override fun startWindowFullscreen() {
        super.startWindowFullscreen()
    }

    override fun startWindowTiny() {
        super.startWindowTiny()
    }

    override fun startDismissControlViewTimer() {
        super.startDismissControlViewTimer()
        if (mListener != null) {
            mListener?.onStartDismissControlViewTimer()
        }
    }
}