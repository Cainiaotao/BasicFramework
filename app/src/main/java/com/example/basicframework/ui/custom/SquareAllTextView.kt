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


    private var listener:OnViewListener?=null

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        LayoutInflater.from(context).inflate(R.layout.custom_item_home_all_text_view,this)
        init()
    }

    private fun init(){

        expand.setOnExpandViewListener(object :TopicExpandTextView.OnExpandViewListener{
            override fun onSelectLabel(label: String) {
                listener?.onSpannedLabel(label)
            }

            override fun onClickTag(str: String) {
                listener?.onLabel(str)
            }

            override fun onExpandState(isExpand: Boolean) {
                listener?.onExpandState(isExpand)
            }

        })

    }




    fun setContent(info: NewsBean){
        tv_name.text = info.user!!.name
        expand.setContent(info.textContent)
        expand.setflowLayout(info.labels)
        if (info.labels.size>0){
            info.labels.forEach {
                expand.setSpanContext(" #$it")
            }
        }
    }

    fun setExpandState(isExpand:Boolean){
        expand.setExpand(isExpand)
    }

    fun setOnViewListener(listener:OnViewListener){
        this.listener = listener
    }

    interface OnViewListener{
        fun onSpannedLabel(str:String)
        fun onLabel(str: String)
        fun onExpandState(isExpand: Boolean)
    }
}