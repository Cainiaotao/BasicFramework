package com.example.basicframework.ui.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import com.example.basicframework.R
import com.example.basicframework.base.BaseFragment
import com.example.basicframework.bean.MeViewContent
import com.example.basicframework.bean.enums.MeItemType
import com.example.basicframework.bean.enums.MeViewType
import com.example.basicframework.ui.activity.CalendarActivity
import com.example.basicframework.ui.activity.DrawerCameraActivity
import com.example.basicframework.ui.activity.MeSettingActivity
import com.example.basicframework.ui.adapter.MeListAdapter
import java.text.SimpleDateFormat
import java.util.*

class MeFragment: BaseFragment() {

    private var meAdapter: MeListAdapter?=null
    private var recyclerView:RecyclerView?=null

    override fun setContentView(): Int = R.layout.fragment_me

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
    }

    override fun initData() {
        super.initData()
        val data = arrayListOf(
            MeViewContent(MeViewType.One,0,"Me",MeItemType.Info),
            MeViewContent(MeViewType.TWo,R.mipmap.btn_me_data,"支付",MeItemType.Other),
            MeViewContent(MeViewType.TWo,R.mipmap.btn_me_data_day,"收藏",MeItemType.Other),
            MeViewContent(MeViewType.TWo,R.mipmap.btn_me_faq,"相册",MeItemType.Camera),
            MeViewContent(MeViewType.TWo,R.mipmap.btn_me_faq_new,"时间弹窗",MeItemType.TimePicker),
            MeViewContent(MeViewType.TWo,R.mipmap.btn_me_faq_new,"日历弹窗",MeItemType.DatePicker),
            MeViewContent(MeViewType.TWo,R.mipmap.btn_me_settings,"日历",MeItemType.Calender),
            MeViewContent(MeViewType.TWo,R.mipmap.btn_me_settings,"设置",MeItemType.Setting))
        meAdapter = MeListAdapter(activity!!,data)
        meAdapter?.listener = object :MeListAdapter.OnItemClickListener{
            override fun OnItemClick(type: MeItemType) {
               when(type){
                   MeItemType.Calender->{startActivity(Intent(activity!!,CalendarActivity::class.java))}
                   MeItemType.Setting->{startActivity(Intent(activity!!,MeSettingActivity::class.java))}
                   MeItemType.DatePicker->{dateDialog()}
                   MeItemType.Info->{}
                   MeItemType.Other->{}
                   MeItemType.Camera->{startActivity(Intent(activity!!,DrawerCameraActivity::class.java))}
                   MeItemType.TimePicker->{timeDialog("")}
               }
            }
        }
        recyclerView?.layoutManager = LinearLayoutManager(activity!!)
        recyclerView?.adapter = meAdapter
    }

    private fun dateDialog(){
        val cal = Calendar.getInstance(Locale.CHINA)
        val mdialog = DatePickerDialog(activity!!,null,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal[Calendar.DAY_OF_MONTH])
        mdialog.setButton(DialogInterface.BUTTON_POSITIVE,"确定") { dialog, which ->
            val datePicker = mdialog.datePicker
            val year = datePicker.year
            val month = datePicker.month
            val day = datePicker.dayOfMonth
            showToast(activity!!,"测试：${year}年${month}月${day}日")
        }
        mdialog.setButton(DialogInterface.BUTTON_NEGATIVE,"关闭"){dialog, which ->
            showToast(activity!!,"xxxxx")
        }
        mdialog.setButton(DialogInterface.BUTTON_NEUTRAL,"设置时间"){dialog, which ->
            val datePicker = mdialog.datePicker
            val year = datePicker.year
            val month = datePicker.month
            val day = datePicker.dayOfMonth
            val str = "${year}年${month}月${day}日"
            timeDialog(str)
        }

        mdialog.show()
    }

    private val datePickerListener:DatePickerDialog.OnDateSetListener=
        DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val c = Calendar.getInstance()
            c.set(year, month, dayOfMonth)
            val sdf = SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA)
            val date = sdf.format(c.time)
            showToast(activity!!,date)
        }

    private fun timeDialog(str:String){
        val dialog =  TimePickerDialog(activity!!, AlertDialog.THEME_HOLO_LIGHT,
            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                val houre = hourOfDay
                if (minute < 10){
                    showToast(activity!!,"$str $houre:0$minute")
                }else {
                    showToast(activity!!,"$str $houre:$minute")
                }
            }, 0, 0, true)
        dialog.show()

    }


    override fun initEvent() {
        super.initEvent()
    }
}