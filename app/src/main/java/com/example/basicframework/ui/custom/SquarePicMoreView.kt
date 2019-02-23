package com.example.basicframework.ui.custom

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.example.basicframework.R
import com.example.basicframework.bean.NewsBean
import kotlinx.android.synthetic.main.custom_item_pic_more_view.view.*
import kotlinx.android.synthetic.main.include_square_top_view.view.*

class SquarePicMoreView: ConstraintLayout {

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        LayoutInflater.from(context).inflate(R.layout.custom_item_pic_more_view,this)

    }

    fun setContent(info: NewsBean){
        tv_name.text = info.user!!.name
        if (TextUtils.isEmpty(info.textContent)){
            tv_text.visibility = View.GONE
        }else{
           tv_text.text = info.textContent
           tv_text.visibility = View.VISIBLE
        }

        if (info.imgs!!.size>3){
            img_three.visibility = View.VISIBLE
            img_four.visibility = View.VISIBLE
        }else{
            img_three.visibility = View.GONE
            img_four.visibility = View.GONE
        }

    }
}