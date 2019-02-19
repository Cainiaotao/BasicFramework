package com.example.basicframework.ui.activity


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import com.example.basicframework.R
import com.example.basicframework.base.BaseActivity
import com.example.basicframework.ui.fragment.DiscoverFragment
import com.example.basicframework.ui.fragment.HomeFragment
import com.example.basicframework.ui.fragment.MeFragment
import com.example.basicframework.ui.fragment.OtherFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :BaseActivity() {

    private var homeFragment: HomeFragment?=null
    private var otherFragment: OtherFragment?=null
    private var discoverFragment: DiscoverFragment?=null
    private var meFragment: MeFragment?=null
    private var currentShowFragment: Fragment? = null

    override fun setContentView(): Int = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        //setStatusBarColor()
        selectedTab(HomeFragment::class.java.name)
    }

    override fun initData() {
        super.initData()
    }

    override fun initEvent() {
        super.initEvent()
        navigation_home.setOnClickListener { selectedTab(HomeFragment::class.java.name) }
        navigation_other.setOnClickListener { selectedTab(OtherFragment::class.java.name) }
        navigation_discover.setOnClickListener { selectedTab(DiscoverFragment::class.java.name) }
        navigation_me.setOnClickListener { selectedTab(MeFragment::class.java.name) }
    }

    private fun selectedTab(name: String) {
        when (name) {
            HomeFragment::class.java.name -> {
                if (homeFragment == null) {
                    homeFragment = HomeFragment()
                    addFragment(homeFragment!!)
                } else {
                    if (homeFragment!!.isAdded) {
                        showFragment(homeFragment!!)
                    } else {
                        addFragment(homeFragment!!)
                    }
                }
            }
            OtherFragment::class.java.name -> {
                if (otherFragment == null) {
                    otherFragment = OtherFragment()
                    addFragment(otherFragment!!)
                } else {
                    if (otherFragment!!.isAdded) {
                        showFragment(otherFragment!!)
                    } else {
                        addFragment(otherFragment!!)
                    }
                }
            }
            DiscoverFragment::class.java.name -> {
                if (discoverFragment == null) {
                    discoverFragment = DiscoverFragment()
                    addFragment(discoverFragment!!)
                } else {
                    if (discoverFragment!!.isAdded) {
                        showFragment(discoverFragment!!)
                    } else {
                        addFragment(discoverFragment!!)
                    }
                }
            }
            MeFragment::class.java.name -> {
                if (meFragment == null) {
                    meFragment = MeFragment()
                    addFragment(meFragment!!)
                } else {
                    if (meFragment!!.isAdded) {
                        showFragment(meFragment!!)
                    } else {
                        addFragment(meFragment!!)
                    }
                }
            }
        }
        selectedColor(name)
    }

    private fun selectedColor(name: String) {
//        img_chat.setImageResource(R.mipmap.btn_home_comm)
////        img_contacts.setImageResource(R.mipmap.btn_home_contact)
////        img_discover.setImageResource(R.mipmap.btn_home_engag)
////        img_me.setImageResource(R.mipmap.btn_home_me)
////
////        tv_chat.setTextColor(ContextCompat.getColor(this, R.color.color_8a8a90))
////        tv_contacts.setTextColor(ContextCompat.getColor(this, R.color.color_8a8a90))
////        tv_discover.setTextColor(ContextCompat.getColor(this, R.color.color_8a8a90))
////        tv_me.setTextColor(ContextCompat.getColor(this, R.color.color_8a8a90))
////        when (name) {
////            ChatFragment::class.java.name -> {
////                img_chat.setImageResource(R.mipmap.btn_home_commed)
////                tv_chat.setTextColor(ContextCompat.getColor(this, R.color.color_f8ad2f))
////            }
////            ContactsFragment::class.java.name -> {
////                img_contacts.setImageResource(R.mipmap.btn_home_contacted)
////                tv_contacts.setTextColor(ContextCompat.getColor(this, R.color.color_f8ad2f))
////            }
////            DiscoverFragment::class.java.name -> {
////                img_discover.setImageResource(R.mipmap.btn_home_engaged)
////                tv_discover.setTextColor(ContextCompat.getColor(this, R.color.color_f8ad2f))
////            }
////            MeFragment::class.java.name -> {
////                img_me.setImageResource(R.mipmap.btn_home_meed)
////                tv_me.setTextColor(ContextCompat.getColor(this, R.color.color_f8ad2f))
////            }
////        }
    }

    private fun addFragment(fragment: Fragment) {
        if (currentShowFragment != null)
            supportFragmentManager.beginTransaction().hide(currentShowFragment!!).commitAllowingStateLoss()
        if (supportFragmentManager.findFragmentByTag(fragment.javaClass.name) != null)
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, fragment.javaClass.name).commitAllowingStateLoss()
        else
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment, fragment.javaClass.name).commitAllowingStateLoss()
        currentShowFragment = fragment
    }

    private fun showFragment(fragment: Fragment) {
        if (currentShowFragment != null)
            supportFragmentManager.beginTransaction().hide(currentShowFragment!!).commitAllowingStateLoss()
        if (fragment.isDetached)
            supportFragmentManager.beginTransaction().attach(fragment).commitAllowingStateLoss()
        else
            supportFragmentManager.beginTransaction().show(fragment).commitAllowingStateLoss()
        currentShowFragment = fragment
    }
}
