package com.example.basicframework.utils


import android.content.Context
import android.util.TypedValue
import android.view.WindowManager
import com.example.basicframework.app.APP


/**
 * Created by visir on 2017/7/13.
 */
object PixelUtils {

    fun dp2px(context: Context, dpVal: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.resources.displayMetrics).toInt()


    fun dp2px(dpVal: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, APP.appContext?.resources?.displayMetrics).toInt()


    fun sp2px(context: Context, spVal: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, context.resources.displayMetrics).toInt()


    fun sp2px(spVal: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, APP.appContext?.resources?.displayMetrics).toInt()


    fun px2dp(context: Context, pxVal: Float): Float = pxVal / context.resources.displayMetrics.density


    fun px2sp(context: Context, pxVal: Float): Float = pxVal / context.resources.displayMetrics.scaledDensity


    fun getScreenWidth(context: Context): Int {
        val wm = context.applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return wm.defaultDisplay.width
    }

    fun getScreenHeight(context: Context): Int {
        val wm = context.applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return wm.defaultDisplay.height
    }

    fun getStatusBarHeight(context: Context): Int {
        var statusBarHeight = 0
        val res = context.resources
        val resourceId = res.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId)
        }
        return statusBarHeight
    }

    fun getActionBarHeight(context: Context): Float {
        val actionbarSizeTypedArray = context.obtainStyledAttributes(intArrayOf(android.R.attr.actionBarSize))
        val height = actionbarSizeTypedArray.getDimension(0, 0f)
        actionbarSizeTypedArray.recycle()
        return height
    }
}
