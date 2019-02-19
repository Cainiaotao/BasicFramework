package com.example.basicframework.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.basicframework.R
import com.example.basicframework.base.BaseActivity

/**
 * @deprecated 闪屏页
 */
class SplashActivity : BaseActivity() {
    override fun setContentView(): Int = R.layout.activity_splash
    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        setStatusBarTranslucent(true)
    }

    override fun initData() {
        super.initData()
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity,MainActivity::class.java))
            finish()
        },2000)
    }

    override fun initEvent() {
        super.initEvent()
    }
}