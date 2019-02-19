package com.daigen.hyt.wedate.tools

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.location.LocationManager
import android.net.Uri
import android.os.Environment
import android.os.Vibrator
import android.provider.Settings
import android.support.annotation.StringRes
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.example.basicframework.app.APP
import com.example.basicframework.utils.MD5Helper
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.util.*
import java.util.regex.Pattern

class Visir {

    companion object {

        const val REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(16[6])|(17[0,1,3,5-8])|(18[0-9])|(19[8,9]))\\d{8}$"
        const val REGEX_USERNAME = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$"

        private fun isMatch(regex: String, input: CharSequence?): Boolean {
            return input != null && input.isNotEmpty() && Pattern.matches(regex, input)
        }

        fun isMobileExact(input: CharSequence): Boolean {

            return isMatch(REGEX_MOBILE_EXACT, input)
        }

        fun isUserNick(input: CharSequence): Boolean {
            return isMatch(REGEX_USERNAME, input)
        }

        /***
         * 判断Gps功能是否打开
         */
        fun isGpsOpen(locationManager: LocationManager): Boolean {
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        }

        fun getDeviceBrandModel(): String {
            return String.format("%s %s", android.os.Build.BRAND, android.os.Build.MODEL)
        }

        fun getUniqueId(): String {
            var androidId = Settings.Secure.getString(APP.appContext?.contentResolver, Settings.Secure.ANDROID_ID)
            androidId = MD5Helper.MD5(androidId)
            return androidId
        }

        /*获取设备品牌*/
        fun getDeviceBrand(): String {
            return android.os.Build.BRAND
        }

        /*获取设备制造商*/
        fun getDeviceManufacturer(): String {
            return android.os.Build.MANUFACTURER
        }

        /*获取设备名称*/
        fun getDeviceProduce(): String {
            return android.os.Build.PRODUCT
        }

        /*获取设备型号*/
        fun getDeviceModel(): String {
            return android.os.Build.MODEL
        }

        fun clearAllCache(context: Context): Boolean {
            deleteDir(context.cacheDir)
            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                return deleteDir(context.externalCacheDir)
            }
            return false
        }

        private fun deleteDir(dir: File?): Boolean {
            if (dir != null && dir.isDirectory) {
                val children: Array<String> = dir.list()
                children.indices
                        .map { deleteDir(File(dir, children[it])) }
                        .filterNot { it }
                        .forEach { return false }
            }
            return dir!!.delete()
        }

        /**
         * 判断邮箱是否合法
         */
        fun isEmail(email: String): Boolean {
            if ("" == email) return false
            val p = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\$")//复杂匹配
            val m = p.matcher(email)
            return m.matches()
        }

        /**
         *检查SD卡是否存在

         * @return
         */
        fun isSdcardExist(): Boolean {
            return android.os.Environment.getExternalStorageState() == android.os.Environment.MEDIA_MOUNTED
        }

        /**
         * 判断相对应的APP是否存在

         * @param context
         * *
         * @param packageName(包名)
         * *
         * @return
         */
        fun isAvilible(context: Context, packageName: String): Boolean {
            val packageManager: PackageManager = context.packageManager
            //获取手机系统的所有APP包名，然后进行一一比较
            val info: List<PackageInfo> = packageManager.getInstalledPackages(0)
            for (i: Int in info.indices) {
                if (info[i].packageName.equals(packageName, ignoreCase = true))
                    return true
            }
            return false
        }

        fun getAssetJson(context: Context, fileName: String): String {
            val stringBuilder = StringBuilder()
            try {
                val assetManager: AssetManager = context.assets
                val bf = BufferedReader(InputStreamReader(
                        assetManager.open(fileName)))
                var line: String
                while (true) {
                    line = bf.readLine()
                    if (line == null) break
                    stringBuilder.append(line)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return stringBuilder.toString()
        }

        /**
         * 随机获取一个UUID
         * @return 长度为32的字符串
         */
        fun getRandomUUIDString(): String {
            return UUID.randomUUID().toString().replace("-", "")
        }


        /**
         * 判断字符串是否是纯数字
         * @return str
         */
        fun isNumeric(str: String): Boolean {
            val pattern = Pattern.compile("[0-9]*")
            return pattern.matcher(str).matches()
        }

//        fun verifyFileUrl(str: String): String {
////            if (str.startsWith("http://") || str.startsWith("https://"))
////                return str
////            return FileUtil.getDownloadUrl(str)
////        }

        fun checkApkExist(context: Context, pkName: String): Boolean {
            if (TextUtils.isEmpty(pkName)) return false
            val packages = context.packageManager.getInstalledPackages(0)
            if (null == packages || packages.isEmpty()) return false
            for (info in packages) {
                if (info.packageName == pkName) return true
            }
            return false
        }


//
//        fun makeToast(context: Context, str: String, duration: Int) {
//            val view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null)
//            val tv = view.findViewById<TextView>(R.id.message)
//            tv.text = str
//            val toast = Toast(context)
//            toast.setGravity(Gravity.CENTER, 0, 0)
//            toast.duration = duration
//            toast.view = view
//            toast.show()
//        }

//        fun makeToast(context: Context, @StringRes res: Int, duration: Int) {
//            val str = context.getString(res)
//            makeToast(context, str, duration)
//        }

        fun getToMarketIntent(context: Context): Intent {
            val localStringBuilder = StringBuilder().append("market://details?id=")
            val str = context.packageName
            localStringBuilder.append(str)
            val localUri = Uri.parse(localStringBuilder.toString())

            val intent = Intent(Intent.ACTION_VIEW, localUri)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            return intent
        }

        fun gaoDeToBaidu(gd_lon: Double, gd_lat: Double): DoubleArray {
            val bd_lat_lon = DoubleArray(2)
            val PI = 3.14159265358979324 * 3000.0 / 180.0
            val z = Math.sqrt(gd_lon * gd_lon + gd_lat * gd_lat) + 0.00002 * Math.sin(gd_lat * PI)
            val theta = Math.atan2(gd_lat, gd_lon) + 0.000003 * Math.cos(gd_lon * PI)
            bd_lat_lon[0] = z * Math.cos(theta) + 0.0065
            bd_lat_lon[1] = z * Math.sin(theta) + 0.006
            return bd_lat_lon
        }


        fun parseQuery(text: String, map: HashMap<String, String>) {
            if (text.isEmpty()) return
            val url = URL(text)
            val query = url.query?: return
            if(TextUtils.isEmpty(query))return
            val str = query.split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (s in str) {
                val params = s.split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                if (params.size != 2) continue
                map[params[0]] = params[1]
            }
        }

    }


}