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

class SquareVoiceView: ConstraintLayout {
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        LayoutInflater.from(context).inflate(R.layout.custom_item_voice_view,this)
    }

    fun setContent(info:NewsBean){
        if (TextUtils.isEmpty(info.textContent)){
            tv_text.visibility = View.GONE
        }else{
            tv_text.text = info.textContent
            tv_text.visibility = View.VISIBLE
        }
    }
}