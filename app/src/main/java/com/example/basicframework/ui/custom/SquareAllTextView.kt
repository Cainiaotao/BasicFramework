package com.example.basicframework.ui.custom

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import com.example.basicframework.R
import com.example.basicframework.bean.NewsBean
import kotlinx.android.synthetic.main.custom_item_home_all_text_view.view.*
import kotlinx.android.synthetic.main.include_square_top_view.view.*

class SquareAllTextView : ConstraintLayout {

    private var tv_text: TextView?=null
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        LayoutInflater.from(context).inflate(R.layout.custom_item_home_all_text_view,this)
        tv_text = findViewById(R.id.tv_text)
    }

    fun setContent(info: NewsBean){
        tv_text?.text = info.textContent
        tv_name.text = info.user!!.name
    }
}