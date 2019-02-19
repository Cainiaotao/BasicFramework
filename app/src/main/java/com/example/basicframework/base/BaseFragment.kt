package com.example.basicframework.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

abstract class BaseFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(setContentView(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view, savedInstanceState)
        initData()
        initEvent()
    }

    @LayoutRes
    protected abstract fun setContentView(): Int

    open fun initView(view: View, savedInstanceState: Bundle?) {}

    open fun initData() {}

    open fun initEvent() {}

    fun showToast(context: Context,str:String){
        if (null!= context)Toast.makeText(context,str,Toast.LENGTH_SHORT).show()
    }
}