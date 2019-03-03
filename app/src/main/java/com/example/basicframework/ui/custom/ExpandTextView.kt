package com.example.basicframework.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import android.text.Layout
import android.text.StaticLayout



class ExpandTextView:AppCompatTextView {
    private var onExpandListener:OnExpandListener?=null
    private var isExpand:Boolean = false
    private var content:String = ""
    private val maxLineCount:Int = 5

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //文字计算
        val sl = StaticLayout( content,paint,
            measuredWidth - paddingLeft - paddingRight,
            Layout.Alignment.ALIGN_CENTER, 1f, 0f, true)
        var lineCount = sl.lineCount //获取总行数
        if (lineCount > maxLineCount){
            if (isExpand){
                //展开
                this.text = content
                onExpandListener?.onExpand()
            }else{
                //收起
                lineCount = maxLineCount
                this.text = content
                onExpandListener?.onCollapse()
            }
        }else{
            this.text = content
        }
        var lineHeight = 0
        for (i in 0 until lineCount){
            val lineBound = Rect()
            sl.getLineBounds(i,lineBound)
            lineHeight += lineBound.height()
        }
        lineHeight += paddingTop + paddingBottom
        setMeasuredDimension(measuredWidth,lineHeight)
    }

    interface OnExpandListener{
        fun onExpand()
        fun onCollapse()
    }

    fun setContent(str:String,isExpand: Boolean){
        this.content = str
        this.isExpand = isExpand
        this.text = str
    }

    fun setChanged(expanded :Boolean){
        isExpand = expanded
        requestLayout()
    }

    fun setOnExpandStateListener(onExpandListener:OnExpandListener){
        this.onExpandListener = onExpandListener
    }
}