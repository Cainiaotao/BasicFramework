package com.example.basicframework.ui.custom

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.example.basicframework.R
import com.example.basicframework.bean.NewsBean
import kotlinx.android.synthetic.main.custom_item_pic_center_view.view.*
import kotlinx.android.synthetic.main.include_square_top_view.view.*

class SquarePicCenterView: ConstraintLayout,ExpandTextView.OnExpandStateListener {

    var listener:OnItemViewListener?=null

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        LayoutInflater.from(context).inflate(R.layout.custom_item_pic_center_view,this)

    }

    fun setContent(info: NewsBean){
        tv_name.text = info.user!!.name
        if (TextUtils.isEmpty(info.textContent)){
            tv_text.visibility = View.GONE
        }else{
            tv_text.visibility = View.VISIBLE
            tv_text.setContext(info.textContent)
        }
        tv_text.setOnExpandStateListener(this)
    }

    fun setCollState(boolean: Boolean){
        tv_text.expandState(boolean)
    }

    override fun onExpandStateChanged(isCollapsed: Boolean) {
        listener?.onExpand(isCollapsed)
    }

    interface OnItemViewListener{
        fun onExpand(isCollapsed: Boolean)
    }
}