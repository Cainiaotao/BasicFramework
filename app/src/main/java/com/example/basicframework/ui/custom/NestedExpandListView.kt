
package com.example.basicframework.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AbsListView
import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE
import android.widget.ExpandableListView
import android.widget.TextView
import com.example.basicframework.R

class NestedExpandListView :ExpandableListView ,AbsListView.OnScrollListener{


    private var onLoadMoreListener:OnLoadMoreListener?=null

    private var mfooterView: View?=null
    private var tv_state:TextView?=null
    private var footerViewHeight:Int = 0

    private var lasstVisible:Int = 0
    private var totaItemCounts:Int = 0

    private var isNetWorkError:Boolean = false


    private var isLoading:Boolean = false
    constructor(context: Context?) : super(context){
        init(context!!)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init(context!!)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init(context!!)
    }

    private fun init(context: Context){
        initFooterView()
        this.setOnScrollListener(this)
    }

    private fun initFooterView(){
        mfooterView = LayoutInflater.from(context).inflate(R.layout.custom_footer_view,this,false)
        tv_state = mfooterView!!.findViewById(R.id.tv_state)
        tv_state?.setOnClickListener {
            if (isNetWorkError){
                isNetWorkError = false
                if (onLoadMoreListener!=null){
                    onLoadMoreListener?.onReload()
                }
            }
        }
        mfooterView!!.measure(0, 0)
        footerViewHeight = mfooterView!!.measuredHeight
        mfooterView!!.setPadding(0, -footerViewHeight, 0, 0);
        addFooterView(mfooterView)
    }

    override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        this.lasstVisible = firstVisibleItem + visibleItemCount
        this.totaItemCounts = totalItemCount
    }

    override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
        if (totaItemCounts == lasstVisible && scrollState == SCROLL_STATE_IDLE) {
            if (!isLoading) {
                isLoading=true
                //加载数据
                mfooterView!!.setPadding(0,0,0,0)
                if (onLoadMoreListener!=null){
                    onLoadMoreListener?.onLoad()
                }
            }
        }
    }

    fun setOnloadMoreListener(onLoadMoreListener:OnLoadMoreListener){
        this.onLoadMoreListener = onLoadMoreListener
    }

    fun onloadCompleted(){
        isLoading = false
        mfooterView!!.setPadding(0,-footerViewHeight,0,0)
    }

    fun setNoMore(boolean: Boolean){
        if (boolean){
            tv_state?.text = "没有更多的数据"
        }else{
            tv_state?.text = "正在加载..."
        }
    }

    fun setNetworkError(){
        isNetWorkError = true
        tv_state?.text = "网络错误，请点击重试"
    }

    interface OnLoadMoreListener{
        fun onLoad()
        fun onReload()
    }

}