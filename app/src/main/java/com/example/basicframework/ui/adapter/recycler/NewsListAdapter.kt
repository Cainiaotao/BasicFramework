package com.example.basicframework.ui.adapter.recycler

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.basicframework.R
import com.example.basicframework.bean.NewsBean
import com.example.basicframework.ui.custom.*
import com.example.basicframework.ui.widget.CompatExpandTextView

class NewsListAdapter(var mContext:Context,val list:ArrayList<NewsBean>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onItemListener:OnItemListener?=null

    private val ITEM_SEARCH = 110 //搜索
    private val ITEM_ALL_TEXT = 111 //纯文本
    private val ITEM_CENTER_PIC_TEXT =112 //居中大图 + 文本
    private val ITEM_PIC_MORE_TEXT = 113//多张大图+文本
    private val ITEM_VOICE_TEXT = 114 //语音 + 文本
    private val ITEM_VIDEO_TEXT = 115 //视频 + 文本

    override fun getItemViewType(position: Int): Int {
        val isVoice = list[position].voice
        return if (isVoice){
            ITEM_VOICE_TEXT
        }else{
            if (list[position].video){
                ITEM_VIDEO_TEXT
            }else{
                val imgSize = list[position].imgs?.size
                if (imgSize!=null && imgSize>0){
                    if (imgSize>1){
                        ITEM_PIC_MORE_TEXT
                    }else{
                        ITEM_CENTER_PIC_TEXT
                    }
                }else{
                    ITEM_ALL_TEXT
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
           //ITEM_SEARCH->SearchItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home_list_search_view,parent,false))
            ITEM_ALL_TEXT->AlltextItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_all_text_view_type,parent,false))
            ITEM_CENTER_PIC_TEXT->PicCenterItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home_list_pic_center_view,parent,false))
            ITEM_PIC_MORE_TEXT->PicMoreItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home_list_pic_more_view,parent,false))
            ITEM_VOICE_TEXT->VoiceItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home_list_voice_view,parent,false))
            ITEM_VIDEO_TEXT->VideoItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home_list_video_view,parent,false))
            else -> null!!
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item:NewsBean = list[position]
        when(holder){
//            is SearchItemHolder->{
//                holder.btn_search.setOnClickListener {
//                    //
//                }
//            }
            is  AlltextItemHolder->{
                //解决expandTextView 复用bug
                val txt = item.textContent
                holder.expandText.setChanged(item.isExpand)
                holder.expandText.setExpandText(txt,item.isExpand,object :CompatExpandTextView.OnExpandListener{
                    override fun onExpand() {
                        holder.btn_expand.text = "收起"
                        holder.btn_expand.visibility = View.VISIBLE
                    }

                    override fun onCollapse() {
                        holder.btn_expand.text = "展开"
                        holder.btn_expand.visibility = View.VISIBLE
                    }

                    override fun onLoss() {
                        holder.btn_expand.visibility = View.GONE
                    }
                })
                var isClicked = item.isExpand
                holder.btn_expand.setOnClickListener {
                    if (isClicked){
                        holder.expandText.setChanged(false)
                        onItemListener?.onExpandState(false,position)
                    }else{
                        holder.expandText.setChanged(true)
                        onItemListener?.onExpandState(true,position)
                    }

                }
//                val txtType = holder.itemView as SquareAllTextView
//                txtType.setContent(item)
//                txtType.setExpandState(item.isExpand)
//                txtType.setOnViewListener(object :SquareAllTextView.OnViewListener{
//                    override fun onSpannedLabel(str: String) {
//                        onItemListener?.onSelectLabel(str)
//                    }
//
//                    override fun onLabel(str: String) {
//                        onItemListener?.onSelectLabel(str)
//                    }
//
//                    override fun onExpandState(isExpand: Boolean) {
//                        item.isExpand = isExpand
//                        onItemListener?.onExpandState(isExpand,position)
//                    }
//                })
//                txtType.setExpandState(item.isExpand)
            }
            is PicCenterItemHolder->{
                val picType  = holder.itemView as SquarePicCenterView
                picType.setContent(item)
                picType.setCollState(item.isExpand)
                picType.listener = object :SquarePicCenterView.OnItemViewListener{
                    override fun onExpand(isCollapsed: Boolean) {
                        onItemListener?.onExpandState(isCollapsed,position)
                    }
                }
            }
            is PicMoreItemHolder->{
                val picType = holder.itemView as SquarePicMoreView
                picType.setContent(item)
            }
            is VoiceItemHolder->{
                val voiceType = holder.itemView as SquareVoiceView
                voiceType.setContent(item)
                holder.itemView.setOnLongClickListener {
                    onItemListener?.onItemLongClick(it)
                    return@setOnLongClickListener false
                }
            }
            is VideoItemHolder->{
                val videType = holder.itemView as SquareVideoView
                videType.setContent(item)
                videType.listener = object:SquareVideoView.OnClickListener{
                    override fun onThumb() {
                        onItemListener?.onVideoThumb()
                    }
                }
            }
        }
    }

    class SearchItemHolder(view:View):RecyclerView.ViewHolder(view){
        internal val btn_search: ConstraintLayout = view.findViewById(R.id.btn_search)
    }
    class AlltextItemHolder(view:View):RecyclerView.ViewHolder(view){
        internal val expandText:CompatExpandTextView = view.findViewById(R.id.tv_expand)
        internal val btn_expand:Button = view.findViewById(R.id.btn_expand)
    }
    class PicCenterItemHolder(view:View):RecyclerView.ViewHolder(view)
    class PicMoreItemHolder(view:View):RecyclerView.ViewHolder(view)
    class VoiceItemHolder(view:View):RecyclerView.ViewHolder(view)
    class VideoItemHolder(view:View):RecyclerView.ViewHolder(view)


    interface OnItemListener{
        fun onSelectLabel(label:String)
        fun onExpandState(isCollapsed:Boolean,position: Int)
        fun onItemLongClick(view: View)
        fun onVideoThumb()
    }

    fun setOnItemListener(onItemListener:OnItemListener){
        this.onItemListener = onItemListener
    }
}