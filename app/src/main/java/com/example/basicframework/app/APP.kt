package com.example.basicframework.app

import android.app.Application
import android.content.Context

class APP : Application() {

    companion object {
        //类似与java静态常量
        var app:APP?=null
        val appContext: Context? get() = APP.app

    }
    override fun onCreate() {
        super.onCreate()
        APP.app = this
        //初始化 应用程序级别 的资源，如全局对象、环境配置变量等
        //数据共享、数据缓存，如设置全局共享变量、方法等
        //获取应用程序当前的内存使用情况，及时释放资源，从而避免被系统杀死
        //监听 应用程序 配置信息的改变，如屏幕旋转等
        //监听应用程序内 所有Activity的生命周期
    }


}