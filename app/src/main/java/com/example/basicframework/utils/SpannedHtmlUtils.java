package com.example.basicframework.utils;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.URLSpan;
import com.example.basicframework.ui.custom.CustomClickableSpan;
import org.xml.sax.XMLReader;

public class SpannedHtmlUtils {

    private static final int TOPIC_TAG = 0; //话题
    private static final int AT_USER = 1; //用户
    private static final int NORMAL_URL = 2; //普通http
    private static final int CUSTOM_TAG = 3; //自定义
//
//    private CharSequence getClickableHtml(String html, Context mContext) {
//        Spanned spannedHtml = Html.fromHtml(html, mImageGetter, new CustomTagHandler());
//        SpannableStringBuilder clickableHtmlBuilder = new SpannableStringBuilder(spannedHtml);
//        URLSpan[] urls = clickableHtmlBuilder.getSpans(0, spannedHtml.length(), URLSpan.class);
//        for (final URLSpan span : urls) {
//            int start = clickableHtmlBuilder.getSpanStart(span);
//            int end = clickableHtmlBuilder.getSpanEnd(span);
//            int flag = clickableHtmlBuilder.getSpanFlags(span);
//            final String url = span.getURL();
//            setUpUrl(mContext,clickableHtmlBuilder, span, start, end, flag, url);
//        }
//        return clickableHtmlBuilder;
//    }

    private void setUpUrl(Context mContext,SpannableStringBuilder clickableHtmlBuilder, URLSpan span, int start, int end, int flag, final String url) {
        CustomClickableSpan clickableSpan = null;
        if (url.startsWith("@")) {
            clickableSpan = new CustomClickableSpan(mContext,AT_USER, url);
        } else if (url.startsWith("#")) {
            clickableSpan = new CustomClickableSpan(mContext,TOPIC_TAG, url);
        } else if (url.startsWith("http") || url.startsWith("https")) {
            clickableSpan = new CustomClickableSpan(mContext,NORMAL_URL, url);
        }
        clickableHtmlBuilder.removeSpan(span);//清除当前span
        clickableHtmlBuilder.setSpan(clickableSpan, start, end, flag);
    }


}
