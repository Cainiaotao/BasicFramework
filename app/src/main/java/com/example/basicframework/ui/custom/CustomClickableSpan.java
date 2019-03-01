package com.example.basicframework.ui.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Toast;
import com.example.basicframework.R;

public class CustomClickableSpan extends ClickableSpan {
    private static final int TOPIC_TAG = 0; //话题
    private static final int AT_USER = 1; //用户
    private static final int NORMAL_URL = 2; //普通http
    private static final int CUSTOM_TAG = 3; //自定义

    private String text;
    private int type;
    private Context mContext;


    public CustomClickableSpan(Context mContext,int type,String text) {
        this.text = text;
        this.type = type;
        this.mContext = mContext;
    }

    @Override
    public void onClick(@NonNull View widget) {
        switch (type){
            case TOPIC_TAG:
                Toast.makeText(mContext,"话题",Toast.LENGTH_SHORT).show();
                break;
            case AT_USER:
                Toast.makeText(mContext,"用户",Toast.LENGTH_SHORT).show();
                break;
            case NORMAL_URL:
                Toast.makeText(mContext,"http",Toast.LENGTH_SHORT).show();
                break;
            case CUSTOM_TAG:
                Toast.makeText(mContext,"自定义",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        //super.updateDrawState(ds);
        int color = ContextCompat.getColor(mContext,R.color.color_007efa);
        ds.setColor(color);//设置点击文字的颜色
        ds.setUnderlineText(false); //去掉下划线
    }
}
