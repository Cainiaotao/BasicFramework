package com.example.basicframework.ui.custom.home

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import com.example.basicframework.R

class SearchHeaderView: ConstraintLayout {
    constructor(context: Context?) : super(context){init()}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){init()}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){init()}

    private fun init(){
        LayoutInflater.from(context).inflate(R.layout.item_header_search_view,this)
    }


}