package com.example.basicframework.ui.custom

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.example.basicframework.R
import com.example.basicframework.bean.NewsBean
import kotlinx.android.synthetic.main.custom_item_home_all_text_view.view.*
import kotlinx.android.synthetic.main.include_square_top_view.view.*

class SquareAllTextView : ConstraintLayout {

    private var onLabelClickListener:OnLabelClickListener?=null
    private var onExpandStateListener:OnExpandStateListener?=null

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        LayoutInflater.from(context).inflate(R.layout.custom_item_home_all_text_view,this)
        init()
    }

    private fun init(){
        expand.setOnExpandListener(object :TopicExpandTextView.OnExpandListener{
            override fun onExpand() {
                onExpandStateListener?.onExpandState(true)
            }

            override fun onCollapse() {
                onExpandStateListener?.onExpandState(false)
            }

        })
    }


    fun setOnLabelClickListener(onLabelClickListener:OnLabelClickListener){
        this.onLabelClickListener = onLabelClickListener
    }

    fun setExpandStateListener(onExpandStateListener:OnExpandStateListener){
        this.onExpandStateListener = onExpandStateListener
    }

    fun setContent(info: NewsBean){
        tv_name.text = info.user!!.name
        expand.setContent(info.textContent)
        if (info.labels.size>0){
            info.labels.forEach {
                expand.setSpanContext(" #$it")
            }
            expand.setOnLabelClickListener(object :TopicExpandTextView.OnLabelClickListener{
                override fun onSelectLabel(label: String) {
                    onLabelClickListener?.onSelectLabel(label)
                }
            })
        }
    }

    fun setExpandState(isExpand:Boolean){
        expand.onExpandContent(isExpand)
    }

    interface OnLabelClickListener{
        fun onSelectLabel(label:String)
    }

    interface OnExpandStateListener{
        fun onExpandState(isExpand: Boolean)
    }
}