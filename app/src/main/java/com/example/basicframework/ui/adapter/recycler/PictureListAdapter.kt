package com.example.basicframework.ui.adapter.recycler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.basicframework.R
import com.example.basicframework.base.PictureMedia
import com.example.basicframework.utils.GlideUtils

class PictureListAdapter(var mContext: Context,val data:ArrayList<PictureMedia>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var listener:OnItemClickListener?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_picture_view,parent,false))
    }

    override fun getItemCount(): Int  = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ImageHolder){
            val item = data[position]
            GlideUtils.load(holder.itemView.context,item.path,holder.image)
            holder.itemView.setOnClickListener {
                listener?.onItemClick(holder.image,position,item.path)
            }
        }
    }

    inner class ImageHolder(view: View):RecyclerView.ViewHolder(view){
        internal val image:ImageView = view.findViewById(R.id.img_picture)
    }
    inner class VideoHolder(view: View):RecyclerView.ViewHolder(view){

    }

    interface OnItemClickListener{
        fun onItemClick(view:ImageView,position: Int,imageUrl: String)
    }
}