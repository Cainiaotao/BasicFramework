package com.example.basicframework.ui.adapter

import android.content.Context
import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import com.example.basicframework.R
import com.example.basicframework.bean.MeViewContent
import com.example.basicframework.bean.enums.MeItemType
import com.example.basicframework.bean.enums.MeViewType


class MeListAdapter(var mContext:Context,val data:ArrayList<MeViewContent>) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val  ITEM_TYPE_ONE = 0
    private val  ITEM_TYPE_TWO = 1
    var listener:OnItemClickListener?=null

    override fun getItemViewType(position: Int): Int {
        return if (data[position].type == MeViewType.One){
            ITEM_TYPE_ONE
        }else{
            ITEM_TYPE_TWO
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ITEM_TYPE_ONE->HeadHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_me_item_head_view,parent,false))
            ITEM_TYPE_TWO->ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_me_item_view,parent,false))
            else->null!!
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        if (holder is HeadHolder){

        }else if(holder is ItemHolder){
            holder.icon.setImageResource(item.icon)
            holder.title.text = item.title
            holder.itemView.setOnClickListener {
                listener?.OnItemClick(item.itemType)
            }
        }
    }

    inner class HeadHolder(view: View) : RecyclerView.ViewHolder(view)
    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view){
        internal val icon:ImageView = view.findViewById(R.id.img_icon)
        internal val title:TextView = view.findViewById(R.id.tv_title)
    }

    interface OnItemClickListener{
        fun OnItemClick(type:MeItemType)
    }
}