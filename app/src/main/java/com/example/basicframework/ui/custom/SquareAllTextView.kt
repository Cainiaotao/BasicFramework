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

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        LayoutInflater.from(context).inflate(R.layout.custom_item_home_all_text_view,this)
    }

    fun setContent(info: NewsBean){
        tv_expand.text = info.textContent
        tv_name.text = info.user!!.name
        tv_expand.setOnExpandStateChangeListener { textView, isExpanded ->
            Toast.makeText(context,"$isExpanded",Toast.LENGTH_SHORT).show()
        }

    }
}