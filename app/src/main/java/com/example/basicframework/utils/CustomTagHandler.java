package com.example.basicframework.utils;

import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import com.example.basicframework.ui.custom.CustomClickableSpan;
import org.xml.sax.XMLReader;

public class CustomTagHandler implements Html.TagHandler {

    private int startIndex = 0;
    private int stopIndex = 0;
    private static final int CUSTOM_TAG = 3; //自定义


    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        if (tag.toLowerCase().equals("tag")) {//自定义标签处理
            if (opening) {//开始标签
                startTag(tag, output, xmlReader);
            } else {//结束标签
                endTag(tag, output, xmlReader);
            }
        }
    }

    public void startTag(String tag, Editable output, XMLReader xmlReader) {
        startIndex = output.length();
    }

    public void endTag(String tag, Editable output, XMLReader xmlReader) {
        stopIndex = output.length();
        String content = output.subSequence(startIndex, stopIndex).toString();//获取点击内容
//        output.setSpan(new CustomClickableSpan(CUSTOM_TAG, content), startIndex, stopIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}
