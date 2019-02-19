package com.daigen.hyt.wedate.tools

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.support.annotation.ColorInt
import android.text.TextUtils
import java.nio.charset.StandardCharsets

class StringUtil {

    companion object {

        val space = " "

        /*字符串转换unicode*/
        fun stringToUnicode(string: String): String {
            if (TextUtils.isEmpty(string)) return ""
            val unicode = StringBuffer()
            for (i in 0 until string.length) {
                val c = string[i]
                unicode.append("\\u" + Integer.toHexString(c.toInt()))
            }
            return unicode.toString()
        }

        /*unicode转字符串*/
        fun unicodeToString(unicode: String): String {
            if (TextUtils.isEmpty(unicode)) return ""
            val string = StringBuffer()
            val hex = unicode.split("\\\\u".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (i in 1 until hex.size) {
                val data = Integer.parseInt(hex[i], 16)
                string.append(data.toChar())
            }
            return string.toString()
        }

        fun fromText(text: String, textSize: Float, @ColorInt color: Int): Bitmap {
            val paint = Paint()
            paint.textSize = textSize
            paint.textAlign = Paint.Align.LEFT
            paint.color = color
            val fm = paint.fontMetricsInt
            val width = paint.measureText(text).toInt()
            val height = fm.descent - fm.ascent
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444)
            val canvas = Canvas(bitmap)
            canvas.drawText(text, 0f, (fm.leading - fm.ascent).toFloat(), paint)
            canvas.save()
            return bitmap
        }

        fun transfromOctalToString(dataStr: String): String {
            if (!dataStr.contains("\\")) {
                return dataStr
            }
            //不属于八进制内容的字符
            val oldBuffer = StringBuilder()
            //属于八进制的内容，转成十六进制后缓存在这里
            var hexBuffer = StringBuilder()
            var i = 0
            while (i < dataStr.length) {
                val c = dataStr[i]
                if (c != '\\') {
                    oldBuffer.append(c)
                } else {
                    val c1 = dataStr[i + 1]
                    val c2 = dataStr[i + 2]
                    val c3 = dataStr[i + 3]
                    i += 3
                    //将八进制转换为十进制，再转换为十六进制
                    val hex = Integer.toHexString(Integer.valueOf("" + c1 + c2 + c3, 8))
                    //先缓存住，直到凑够三个字节
                    hexBuffer.append(hex)
                    val hexString = hexBuffer.toString()
                    //utf8编码中，三个字节为一个汉字
                    if (hexString.length == 6) {
                        //凑够三个字节了，转成汉字后放入oldBuffer中
                        oldBuffer.append(hexStr2Str(hexString))
                        //凑够一个汉字了，清空缓存
                        hexBuffer = StringBuilder()
                    }
                }
                i++
            }
            return oldBuffer.toString()
        }

        /**
         * 十六进制转换字符串
         */
        private fun hexStr2Str(hexStr: String): String {
            val str = "0123456789abcdef"
            val hexs = hexStr.toCharArray()
            val bytes = ByteArray(hexStr.length / 2)
            var n: Int
            for (i in bytes.indices) {
                n = str.indexOf(hexs[2 * i]) * 16
                n += str.indexOf(hexs[2 * i + 1])
                bytes[i] = (n and 0xff).toByte()
            }
            return String(bytes, StandardCharsets.UTF_8)
        }

    }


}