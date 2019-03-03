package com.example.basicframework.ui.custom

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.example.basicframework.R
import com.example.basicframework.utils.SquareClickable
import kotlinx.android.synthetic.main.custom_expand_text_view.view.*
import kotlinx.android.synthetic.main.include_other_expandlist_view.view.*

class TopicExpandTextView:ConstraintLayout {

    private var onLabelListener:OnLabelClickListener?=null
    private var onExpandListener:OnExpandListener?=null
    private var showLine:Int = 5
    private var isExpand:Boolean = false
    private var content:String = ""
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
                onExpandListener?.onCollapse()
            }else{
                isExpand = true
                onExpandContent(isExpand)
                onExpandListener?.onExpand()
            }
        }
// ExpandTextView
//        tv_content.setOnExpandStateListener(object :ExpandTextView.OnExpandListener{
//            override fun onExpand() {
//
//            }
//
//            override fun onCollapse() {
//
//            }
//        })
    }

   fun onExpandContent(isExpand:Boolean){
        // recyclerView list 复用会无效
        if (isExpand){
            tv_content.maxLines = Integer.MAX_VALUE
            tv_expand.text ="收起"
            this.isExpand = isExpand
        }else{
            tv_content.maxLines = showLine
            tv_expand.text ="展开"
            this.isExpand = isExpand
        }
        // item bean 中添加 展开状态

        //解决 list 复用 item bean 中添加 展开状态 ExpandTextVIew
//        if (isExpand){
//            tv_expand.text ="收起"
//        }else{
//            tv_expand.text ="展开"
//        }
//        tv_content.setChanged(isExpand)
    }

    fun setContent(str:String){
        //设置部分字体颜色
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N){
//            tv_content.text = Html.fromHtml(str,Html.FROM_HTML_MODE_LEGACY)
//        }else{
//            tv_content.text = Html.fromHtml(str)
//        }
        content = str
        //tv_content.setContent(content,false) ExpandTextVIew
        tv_content.text = str
    }

    fun setSpanContext(str:String){
        val span = SpannableString(str)
        tv_content.setHintTextColor(ContextCompat.getColor(context,android.R.color.transparent))
        span.setSpan(SquareClickable(context,str,
            SquareClickable.OnClickSpanListener { span ->
                onLabelListener?.onSelectLabel(span)
            }),0,str.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_content.append(span)
        tv_content.movementMethod = LinkMovementMethod.getInstance()
    }

    fun setOnLabelClickListener(onLabelListener:OnLabelClickListener){
        this.onLabelListener = onLabelListener
    }

    fun setOnExpandListener(onExpandListener:OnExpandListener){
        this.onExpandListener = onExpandListener
    }

    interface OnLabelClickListener{
        fun onSelectLabel(label:String)
    }

    interface OnExpandListener{
        fun onExpand()
        fun onCollapse()
    }

}