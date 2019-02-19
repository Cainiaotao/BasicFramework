package com.example.basicframework.utils

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import java.util.*

class AppManager private constructor(){
    init {
        if (AppManager.stack == null)
            AppManager.stack = LinkedList()
    }

    companion object {
        private var stack: LinkedList<AppCompatActivity>? = null
        private var instance: AppManager? = null

        val app: AppManager
            get() {
                if (AppManager.instance == null) {
                    synchronized(AppManager::class.java) {
                        if (AppManager.instance == null)
                            AppManager.instance = AppManager()
                    }
                }
                return AppManager.instance!!
            }
    }

    fun addActivity(activity: AppCompatActivity?){
        if (AppManager.stack == null)
            AppManager.stack = LinkedList()
        if (activity != null)
            AppManager.stack!!.add(activity)
    }

    fun removeActivity(activity: AppCompatActivity?){
        if (AppManager.stack == null || AppManager.stack!!.isEmpty()) return
        if (activity != null && AppManager.stack!!.contains(activity)) {
            AppManager.stack!!.remove(activity)
            return
        }
    }

    fun getCurrentActivity(): AppCompatActivity?{
        if (AppManager.stack == null || AppManager.stack!!.isEmpty()) return null
        return AppManager.stack!!.last
    }

    fun finishActivity(){
        if (AppManager.stack == null || AppManager.stack!!.isEmpty()) return
        AppManager.stack!!.last.finish()
    }

    fun finishActivity(activity: AppCompatActivity?):Boolean{
        if (AppManager.stack == null || AppManager.stack!!.isEmpty()) return false
        if (activity != null && AppManager.stack!!.contains(activity)) {
            activity.finish()
            AppManager.stack!!.remove(activity)
            return true
        }
        return false
    }

    fun finishActivity(clazz: Class<*>){
        if (null == AppManager.stack || AppManager.stack!!.isEmpty()) return
        AppManager.stack!!.indices
            .map { AppManager.stack!![it] }
            .filter { it.javaClass == clazz }
            .forEach { finishActivity(it) }
    }

    fun finishAllActivity(){
        if (null == AppManager.stack || AppManager.stack!!.isEmpty()) return

        val iterator = AppManager.stack?.iterator()
        if (null != iterator) {
            while (iterator.hasNext()) {
                val next = iterator.next()
                next.finish()
                iterator.remove()
            }
        }
        AppManager.stack!!.clear()
    }

    fun <T> findActivity(cls: Class<T>):T?{
        var ac: AppCompatActivity? = null
        if (null == AppManager.stack || AppManager.stack!!.isEmpty()) {
            ac = null
        }
        AppManager.stack!!.indices
            .map { AppManager.stack!![it] }
            .filter { it.javaClass == cls }
            .forEach { ac = it }
        return if (null == ac) {
            null
        } else {
            ac as T
        }
    }

    fun finishOtherActivity(cls: Class<*>){
        if (null == AppManager.stack || AppManager.stack!!.isEmpty()) return
        val iterator = AppManager.stack?.iterator()
        if (null != iterator) {
            while (iterator.hasNext()) {
                val next = iterator.next()
                if (next.javaClass != cls) {
                    next.finish()
                    iterator.remove()
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun appExit(context: Context){
        if (null == AppManager.stack || AppManager.stack!!.isEmpty()) {
            return
        }
        try {
            finishAllActivity()
            val activityMgr = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            activityMgr.killBackgroundProcesses(context.packageName)    //android.permission.KILL_BACKGROUND_PROCESSES
            System.exit(0)
            System.gc()
        } catch (e: Exception) {
            System.exit(0)
            System.gc()
        }
    }

}