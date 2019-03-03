package com.example.basicframework.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.example.basicframework.R;
import com.example.basicframework.ui.custom.SquareVideoView;

public class SquareClickable extends ClickableSpan {

    private OnClickSpanListener onClickListener;
    private String label;
    private Context mContext;

    public SquareClickable(Context mContext,String label, OnClickSpanListener onClickListener){
        this.mContext = mContext;
        this.label = label;
        this.onClickListener = onClickListener;
    }

    @Override
    public void onClick(@NonNull View widget) {
        onClickListener.onClickSpan(label);
    }

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        //super.updateDrawState(ds);
        ds.setColor(ContextCompat.getColor(mContext, R.color.color_007efa));
    }

    public interface OnClickSpanListener{
        void onClickSpan(String span);
    }
}
