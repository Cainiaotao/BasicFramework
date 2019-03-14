package com.example.basicframework.ui.custom

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.basicframework.R
import com.example.basicframework.ui.widget.NewExpandTextView
import kotlinx.android.synthetic.main.widget_expand_text_view.view.*

class ExpandTextView:ConstraintLayout,NewExpandTextView.OnExpandStateChangeListener{

    private var listener:OnExpandStateListener?=null
    private var mCollapsed:Boolean = false
    constructor(context: Context?) : super(context){init()}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){init()}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){init()}

    private fun init(){
        LayoutInflater.from(context).inflate(R.layout.widget_expand_text_view,this)
        tv_expand.setExpandBtn(btn_expand)
        btn_expand.setOnClickListener {
            if (mCollapsed){
                btn_expand.text = "收起"
                mCollapsed = false
                tv_expand.onClickStatus()
                tv_expand.setCollapsed(false)
                listener?.onExpandStateChanged(false)
            }else{
                btn_expand.text = "展开"
                mCollapsed = true
                tv_expand.onClickStatus()
                tv_expand.setCollapsed(true)
                listener?.onExpandStateChanged(true)
            }
        }
        tv_expand.setOnExpandStateChangeListener(this)
    }

    fun setContext(string: String){
        tv_expand.text = string

    }

    fun expandState(boolean: Boolean){
        mCollapsed = boolean
        tv_expand.onClickStatus()
        tv_expand.setCollapsed(mCollapsed)
    }

    override fun onChangeStateStart(willExpand: Boolean) {

    }

    override fun onExpandStateChanged(textView: TextView?, isExpanded: Boolean) {

    }

    override fun onNeedCollapseChanged(needCollapse: Boolean) {
        if (needCollapse){
            btn_expand.visibility = View.VISIBLE
            tv_expand.setCollapsed(false)
            tv_expand.onClickStatus()
        }else{
            btn_expand.visibility = View.GONE
            tv_expand.setCollapsed(true)
            tv_expand.onClickStatus()
        }
    }

    fun setOnExpandStateListener(listener:OnExpandStateListener){
        this.listener = listener
    }

    interface OnExpandStateListener{
        fun onExpandStateChanged(isCollapsed: Boolean)
    }

}