package com.example.basicframework.ui.custom

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.ScaleAnimation
import com.example.basicframework.R
import kotlinx.android.synthetic.main.custom_vioce_animation_view.view.*

class VioceAnimationView : ConstraintLayout {

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        LayoutInflater.from(context).inflate(R.layout.custom_vioce_animation_view,this)
    }

    fun startAnimation(){
        val scaleAnimation = ScaleAnimation(1f,0.5f,1f,0.5f)//默认从（0,0）
        scaleAnimation.duration = 3000
        v_one.startAnimation(scaleAnimation)
    }
}