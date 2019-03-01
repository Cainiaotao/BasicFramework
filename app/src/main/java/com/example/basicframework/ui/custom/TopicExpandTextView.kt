package com.example.basicframework.ui.custom

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.text.Html
import android.util.AttributeSet
import android.view.LayoutInflater
import com.example.basicframework.R
import kotlinx.android.synthetic.main.custom_expand_text_view.view.*
import kotlinx.android.synthetic.main.include_other_expandlist_view.view.*

class TopicExpandTextView:ConstraintLayout {

    private var showLine:Int = 5
    private var isExpand:Boolean = false
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        LayoutInflater.from(context).inflate(R.layout.custom_expand_text_view,this)
        init()
    }

    private fun init(){
        tv_expand.text ="展开"
        tv_expand.setOnClickListener {
            if (isExpand){
                isExpand = false
                onExpandContent(isExpand)
            }else{
                isExpand = true
                onExpandContent(isExpand)
            }
        }
    }

    private fun onExpandContent(isExpand:Boolean){
        if (isExpand){
            tv_content.maxLines = Integer.MAX_VALUE
            tv_expand.text ="收起"
        }else{
            tv_content.maxLines = showLine
            tv_expand.text ="展开"
        }
    }

    fun setContent(str:String){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N){
            tv_content.text = Html.fromHtml(str,Html.FROM_HTML_MODE_LEGACY)
        }else{
            tv_content.text = Html.fromHtml(str)
        }
    }

}