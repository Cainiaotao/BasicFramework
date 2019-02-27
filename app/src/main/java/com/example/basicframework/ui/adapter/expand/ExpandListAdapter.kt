package com.example.basicframework.ui.adapter.expand

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.basicframework.R
import com.example.basicframework.bean.GroupBean

class ExpandListAdapter(var mContent: Context,val list:ArrayList<GroupBean>):BaseExpandableListAdapter() {

    override fun getGroup(groupPosition: Int): Any {
        return list[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var convert = convertView
        var holder:GroupHolder
        if (convert == null){
            convert = LayoutInflater.from(mContent).inflate(R.layout.item_group_view,parent,false)
            holder = GroupHolder()
            holder.name = convert.findViewById(R.id.tv_group)
            convert.tag = holder
        }else{
            holder =  convert.tag as GroupHolder
        }
        val item = list[groupPosition]
        holder.name?.text = item.name
        return convert!!
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return list[groupPosition].childs.size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return list[groupPosition].childs[childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(groupPosition: Int,childPosition: Int, isLastChild: Boolean,convertView: View?,parent: ViewGroup?): View {
        var convert = convertView
        var holder:ChildHolder
        if (convert == null){
            convert = LayoutInflater.from(mContent).inflate(R.layout.item_child_view,parent,false)
            holder = ChildHolder()
            holder.name = convert.findViewById(R.id.tv_child)
            convert.tag = holder
        }else{
            holder =  convert.tag as ChildHolder
        }
        val item = list[groupPosition].childs[childPosition]
        holder.name?.text = item
        return convert!!
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return list.size
    }

    class GroupHolder{
        var name:TextView?=null
    }

    class ChildHolder{
        var name:TextView?=null
    }

}