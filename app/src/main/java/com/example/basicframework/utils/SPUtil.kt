package com.daigen.hyt.wedate.tools

import android.content.Context
import android.content.SharedPreferences
import com.example.basicframework.app.APP
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method


class SPUtil {

    companion object {


        private val TAG = SPUtil::class.java.simpleName
        var SharedPreferencedName = "settings"

        /**
         * 向ShredPreference写数据

         * @param context
         * *
         * @param Key
         * *
         * @param value
         * *
         * @return
         */
        fun writeShredPreference(Key: String, value: String): Boolean {
            val sharedPreferences = APP.appContext!!.getSharedPreferences(SharedPreferencedName, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(Key, value)
            return editor.commit()
        }

        /**
         * 从ShredPreference读数据

         * @param context
         * *
         * @param Key
         * *
         * @return
         */
        fun readShredPreference(Key: String): String {
            val sharedPreferences = APP.appContext!!.getSharedPreferences(SharedPreferencedName, Context.MODE_PRIVATE)
            return sharedPreferences.getString(Key, null)
        }


        fun SPUtil(): Throwable {
            /* cannot be instantiated */
            throw UnsupportedOperationException("cannot be instantiated")
        }

        /**
         * 保存在手机里面的文件名
         */
        val FILE_NAME = "share_user_data"


        /**
         * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法

         * @param context
         * *
         * @param key
         * *
         * @param object
         */
        public fun put(key: String, mObject: Any) {
            val sp = APP.appContext!!.getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE)
            val editor = sp.edit()
            if (mObject is String) {
                editor.putString(key, mObject)
            } else if (mObject is Int) {
                editor.putInt(key, mObject)
            } else if (mObject is Boolean) {
                editor.putBoolean(key, mObject)
            } else if (mObject is Float) {
                editor.putFloat(key, mObject)
            } else if (mObject is Long) {
                editor.putLong(key, mObject)
            } else {
                editor.putString(key, mObject.toString())
            }

            SharedPreferencesCompat.apply(editor)
        }


        /**
         * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值

         * @param context
         * *
         * @param key
         * *
         * @param defaultObject
         * *
         * @return
         */
        fun get(key: String, defaultObject: Any): Any {
            val sp = APP.appContext!!.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            return when (defaultObject) {
                is String -> sp.getString(key, defaultObject.toString())
                is Int -> sp.getInt(key, defaultObject)
                is Boolean -> sp.getBoolean(key, defaultObject)
                is Float -> sp.getFloat(key, defaultObject)
                is Long -> sp.getLong(key, defaultObject)
                else -> ""
            }
        }


        /**
         * 移除某个key值已经对应的值

         * @param context
         * *
         * @param key
         */
        fun remove(key: String) {
            if (contains(key)) {
                val sp = APP.appContext!!.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
                val editor = sp.edit()
                editor.remove(key)
                SharedPreferencesCompat.apply(editor)
            }
        }

        /**
         * 清除所有数据

         * @param context
         */
        fun clear() {
            val sp = APP.appContext!!.getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.clear()
            SharedPreferencesCompat.apply(editor)
        }

        /**
         * 查询某个key是否已经存在

         * @param context
         * *
         * @param key
         * *
         * @return
         */
        fun contains(key: String): Boolean {
            val sp = APP.appContext!!.getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE)
            return sp.contains(key)
        }

        /**
         * 返回所有的键值对

         * @param context
         * *
         * @return
         */
        fun getAll(): Map<String, *> {
            val sp = APP.appContext!!.getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE)
            return sp.all
        }


        /**
         * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
         */
        private object SharedPreferencesCompat {
            private val sApplyMethod = findApplyMethod()

            /**
             * 反射查找apply的方法

             * @return
             */
            private fun findApplyMethod(): Method? {
                try {
                    val clz = SharedPreferences.Editor::class.java
                    return clz.getMethod("apply")
                } catch (e: NoSuchMethodException) {
                }

                return null
            }

            /**
             * 如果找到则使用apply执行，否则使用commit

             * @param editor
             */
            fun apply(editor: SharedPreferences.Editor) {
                try {
                    if (sApplyMethod != null) {
                        sApplyMethod.invoke(editor)
                        return
                    }
                } catch (e: IllegalArgumentException) {
                } catch (e: IllegalAccessException) {
                } catch (e: InvocationTargetException) {
                }

                editor.commit()
            }
        }
    }


}