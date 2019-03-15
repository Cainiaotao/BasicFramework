package com.example.basicframework.ui.widget

import android.content.Context
import android.content.pm.ActivityInfo
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import cn.jzvd.*
import com.example.basicframework.R
import com.example.basicframework.ui.custom.VideoStateListener
import kotlinx.android.synthetic.main.layout_std_custom_view.view.*




/**
 * JiaoZiVideoPlayer使用
 */
class CustomVideoPlayerView:JzvdStd {


    private var listener: VideoStateListener?=null

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    //复制JzvdStd UI 往其中添加自己的UI组件
    override fun getLayoutId(): Int = R.layout.layout_std_custom_view

    override fun init(context: Context) {
        super.init(context)
        //初始化自己添加的控件
    }

    /**
     * SCREEN_WINDOW_NORMAL 普通窗口（进入全屏之前的）
     * SCREEN_WINDOW_LIST 列表窗口（进入全屏之前）
     * SCREEN_WINDOW_FULLSCREEN 全屏
     * SCREEN_WINDOW_TINY 小窗
     */
    override fun onClick(v: View) {

        when(v.id){
            R.id.start->{
                if (currentState == Jzvd.CURRENT_STATE_IDLE ||currentState == Jzvd.CURRENT_STATE_NORMAL){
                    //开始播放
                    listener?.onStartClick()
                    return
                }
            }
            R.id.surface_container->{
//                if (currentState == Jzvd.CURRENT_STATE_PLAYING ||currentState == Jzvd.CURRENT_STATE_PAUSE){
//                    Toast.makeText(context,"thumb is clicked",Toast.LENGTH_SHORT).show()
//                    //修改UI
//                    onClickUiToggle()
//                    listener?.onThumb()
//                    return
//                }
                if (currentState == CURRENT_STATE_AUTO_COMPLETE) return
                if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
                    //quit fullscreen
                    backPress()
                } else {
                    Log.d(TAG, "toFullscreenActivity [" + this.hashCode() + "] ")
                    onEvent(JZUserAction.ON_ENTER_FULLSCREEN)
                    startWindowFullscreen()
                }
                return
            }

        }
        super.onClick(v)

    }

    override fun setUp(jzDataSource: JZDataSource?, screen: Int) {
        super.setUp(jzDataSource, screen)
        //隐藏原始组件
    }

    override fun onPrepared() {
        super.onPrepared()
        if (currentScreen == Jzvd.SCREEN_WINDOW_FULLSCREEN) {
            JZMediaManager.instance().jzMediaInterface.setVolume(1f, 1f)
        } else {
            JZMediaManager.instance().jzMediaInterface.setVolume(0f, 0f)
        }
    }

    /**
     * 进入全屏模式的时候关闭静音模式
     */
    override fun startWindowFullscreen() {
        super.startWindowFullscreen()
        JZMediaManager.instance().jzMediaInterface.setVolume(1f, 1f)
    }

    /**
     * 退出全屏模式的时候开启静音模式
     */
    override fun playOnThisJzvd() {
        super.playOnThisJzvd()
        JZMediaManager.instance().jzMediaInterface.setVolume(0f, 0f)
    }

    /**
     * 开始播放
     */
    override fun startVideo() {
        listener?.onStart()
        super.startVideo()
    }

    /**
     * 播放进度
     */
    override fun onProgress(progress: Int, position: Long, duration: Long) {
        super.onProgress(progress, position, duration)
    }

    //onState 代表了播放器引擎的回调，播放视频各个过程的状态的回调

    /**
     * 进入普通状态，通常指setUp之后
     */
    override fun onStateNormal() {
        listener?.onStateNormal()
        super.onStateNormal()
    }

    /**
     * 进入准备中状态，就是loading状态
     */
    override fun onStatePreparing() {
        listener?.onPreparing()
        super.onStatePreparing()
    }

    /**
     *  进入播放状态
     */
    override fun onStatePlaying() {
        listener?.onPlaying()
        super.onStatePlaying()
    }

    /**
     *  进入暂停状态
     */
    override fun onStatePause() {
        listener?.onPause()
        super.onStatePause()
    }

    /**
     * 进入错误状态
     */
    override fun onStateError() {
        listener?.onError()
        super.onStateError()
    }

    /**
     * 进入自动播放完成状态
     */
    override fun onStateAutoComplete() {
        listener?.onComplete()
        super.onStateAutoComplete()
    }

    override fun onAutoCompletion() {
        super.onAutoCompletion()
        //可设置播放完成不显示thumb image
        //可设置重复播放   startVideo()
    }

    //

    override fun changeUiToNormal() {
        super.changeUiToNormal()
    }

    override fun changeUiToPreparing() {
        super.changeUiToPreparing()
    }

    override fun changeUiToPlayingShow() {
        super.changeUiToPlayingShow()
        //thumbImageView.visibility = View.VISIBLE
        bottomContainer.visibility = View.GONE
    }

    override fun changeUiToPlayingClear() {
        super.changeUiToPlayingClear()
        bottomContainer.visibility = View.GONE

    }

    override fun changeUiToPauseShow() {
        super.changeUiToPauseShow()
        bottomContainer.visibility = View.GONE

    }

    override fun changeUiToPauseClear() {
        super.changeUiToPauseClear()
        bottomContainer.visibility = View.GONE

    }

    override fun changeUiToComplete() {
        super.changeUiToComplete()
    }

    override fun changeUiToError() {
        super.changeUiToError()
    }


    /**
     * 重力感应
     */
     fun setOrientation(){
        Jzvd.FULLSCREEN_ORIENTATION= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        Jzvd.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
     }

    /**
     * 是否保存播放进度
     */
    fun setSaveProgress(isSave:Boolean){
        Jzvd.SAVE_PROGRESS = isSave
    }

    /**
     * 清除某个URL进度
     */
    fun setClearUrlProgress(context: Context,url:String){
        Jzvd.clearSavedProgress(context, url)
    }

    /**
     * 加缩略载图
     */
    fun getThumbImageView():ImageView{
        return thumbImageView
    }

    /**
     * 开启小窗播放
     */
    fun openMinWindow(){
        startWindowTiny()
    }

    /**
     * 开启全屏播放
     */
    fun openFullScreen(context: Context,path:String){
        Jzvd.startFullscreen(context,JzvdStd::class.java,path,"")
    }

    /**
     * 视频旋转
     */
    fun setRotation(){
        //旋转90度
        Jzvd.setTextureViewRotation(90)
    }

    /**
     * 视频宽高
     */
    fun setVideoDisplayType(){
        //Jzvd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_PARENT)
        //Jzvd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_SCROP)
        //Jzvd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_ORIGINAL)  //原始大小
    }

    fun setVideoStateListener(listener:VideoStateListener){
        this.listener = listener
    }
}