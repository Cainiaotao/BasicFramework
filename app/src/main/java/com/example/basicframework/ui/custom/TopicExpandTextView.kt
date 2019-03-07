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
import android.widget.LinearLayout
import android.widget.TextView
import com.example.basicframework.R
import com.example.basicframework.ui.flowlayout.FlowLayout
import com.example.basicframework.ui.flowlayout.TagAdapter
import com.example.basicframework.ui.widget.NewExpandTextView
import com.example.basicframework.utils.SquareClickable
import kotlinx.android.synthetic.main.activity_me_setting.*
import kotlinx.android.synthetic.main.custom_expand_text_view.view.*
import kotlinx.android.synthetic.main.include_other_expandlist_view.view.*

class TopicExpandTextView: LinearLayout {

    private var onExpandViewListener:OnExpandViewListener?=null
    private var isExpand:Boolean = false
    private var content:String = ""
    private var tagAdapter: TagAdapter<String>?=null
    private var labels:ArrayList<String>?= ArrayList()
    private var isState:Boolean = false


    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        LayoutInflater.from(context).inflate(R.layout.custom_expand_text_view,this)
        init()
    }

    private fun init(){
        tv_expand.setOnClickListener {
            if (isExpand){
                isExpand = false
                tv_expand.text ="展开"
                tag_fll.visibility = View.GONE
                tv_content.onClickStatus()
                onExpandViewListener?.onExpandState(false)
            }else{
                isExpand = true
                tv_expand.text ="收起"
                tv_content.onClickStatus()
                tag_fll.visibility = View.VISIBLE
                onExpandViewListener?.onExpandState(true)
            }

        }

        tag_fll.setOnTagClickListener { view, position, parent ->
            onExpandViewListener?.onClickTag(this.labels!![position])
            return@setOnTagClickListener true
        }

        tv_content.setOnExpandStateChangeListener(object :NewExpandTextView.OnExpandStateChangeListener{
            override fun onChangeStateStart(willExpand: Boolean) {

            }

            override fun onExpandStateChanged(textView: TextView?, isExpanded: Boolean) {
                isState = isExpand
            }
        })

    }

    fun setExpand(state:Boolean){
        isExpand = state
        if (isExpand){
            tv_content.onClickStatus()
            tv_expand.text ="收起"
            tag_fll.visibility = View.VISIBLE
        }else{
            tv_expand.text ="展开"
            tag_fll.visibility = View.GONE
        }

    }

    fun setflowLayout(labels:ArrayList<String>){
        this.labels = labels
        tagAdapter = object :TagAdapter<String>(labels){
            override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
                val view=  LayoutInflater.from(context).inflate(R.layout.include_text_view,tag_fll,false)
                val tv: TextView = view.findViewById(R.id.tv_test)
                tv.text = "#$t"
                return view
            }
        }
        tag_fll.adapter = tagAdapter
    }


    fun setContent(str:String){
        //设置部分字体颜色
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N){
//            tv_content.text = Html.fromHtml(str,Html.FROM_HTML_MODE_LEGACY)
//        }else{
//            tv_content.text = Html.fromHtml(str)
//        }
        content = str
        tv_content.text = str
    }

    fun setSpanContext(str:String){
        val span = SpannableString(str)
        tv_content.setHintTextColor(ContextCompat.getColor(context,android.R.color.transparent))
        span.setSpan(SquareClickable(context,str,
            SquareClickable.OnClickSpanListener { span ->
                onExpandViewListener?.onSelectLabel(span)
            }),0,str.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_content.append(span)
        tv_content.movementMethod = LinkMovementMethod.getInstance()
    }

    fun setOnExpandViewListener(onExpandViewListener:OnExpandViewListener){
        this.onExpandViewListener = onExpandViewListener
    }

    interface OnExpandViewListener{
        fun onSelectLabel(label:String)
        fun onClickTag(str: String)
        fun onExpandState(isExpand:Boolean)
    }



}