package com.example.basicframework.ui.fragment.calender

import android.view.View
import com.example.basicframework.R
import com.example.basicframework.base.LazyLoadFragment
import com.example.basicframework.utils.DateUtils
import com.necer.entity.NDate
import com.necer.listener.OnCalendarChangedListener
import kotlinx.android.synthetic.main.fragment_calender.*

class CalenderFragment :LazyLoadFragment() {

    private val weeks = arrayOf("周一", "周二", "周三", "周四", "周五", "周六", "周日")
    override fun setContentView(): Int = R.layout.fragment_calender

    override fun onFragmentFirstVisible() {
        initCalenderView()
    }

    private fun initCalenderView(){
        nCalendar.setDateInterval("1901-01-01", "2099-12-30")//设置区间
        nCalendar.setOnCalendarChangedListener(object :OnCalendarChangedListener{
            override fun onCalendarStateChanged(isMonthSate: Boolean) {
                //周月切换
                //showToast(activity!!,"isMonthSate:$isMonthSate")
            }

            override fun onCalendarDateChanged(date: NDate, isClick: Boolean) {
                //选择回掉
                val month = "${date.localDate.monthOfYear}月"
                val day = "${date.localDate.dayOfMonth}日"
                val week = weeks[date.localDate.dayOfWeek - 1]
                tv_selectData.text = "$month$day $week"
                val mil:Long = DateUtils.getStringToDate(date.localDate.toString(),"yyyy-MM-dd")
                if (DateUtils.isToday(mil)){
                    tv_today.visibility = View.VISIBLE
                }else{
                    tv_today.visibility = View.INVISIBLE
                }
            }
        })
    }

    override fun initEvent() {
        super.initEvent()
    }
}