package com.example.basicframework.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.example.basicframework.R
import com.example.basicframework.ui.statusbar.Eyes
import com.example.basicframework.utils.AppManager
import org.greenrobot.eventbus.EventBus

/**
 * @author tantao
 * @deprecated activity 基类
 * @date 2019-02-15
 */
abstract class BaseActivity : AppCompatActivity() {

    private var navigationBarColor: Int = R.color.color_007efa
    @LayoutRes
    protected abstract fun setContentView(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeSetContentView()
        setContentView(this.setContentView())//
        AppManager.app.addActivity(this)
        initView(savedInstanceState)
        initData()
        initEvent()
    }

    open fun beforeSetContentView(){}
    open fun initView(savedInstanceState: Bundle?){}
    open fun initData(){}
    open fun initEvent(){}

    override fun onDestroy() {
        super.onDestroy()
        AppManager.app.removeActivity(this)
    }


    fun setStatusBarTranslucent(boolean: Boolean){
        Eyes.translucentStatusBar(this, boolean)
    }

    fun setStatusBarColor(){
        Eyes.setStatusBarColor(this,navigationBarColor)
    }

    /**
     * use Event Bus add lib
     */
    fun isEventBusRegisted(subscribe:Object):Boolean{
        return EventBus.getDefault().isRegistered(subscribe)
    }

    fun registerEventBus(subscribe:Object){
        EventBus.getDefault().register(subscribe)
    }

    fun unRegisterEventBus(subscribe:Object){
        EventBus.getDefault().unregister(subscribe)
    }
}