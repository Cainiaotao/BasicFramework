package com.example.basicframework.ui.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.text.Layout;
import android.text.StaticLayout;
import android.util.AttributeSet;

public class CompatExpandTextView extends AppCompatTextView {

    private Boolean mExpanded;
    private String mText;
    private final int maxLineCount = 5;
    final String ellipsizeText = "";

    private OnExpandListener listener;

    public CompatExpandTextView(Context context) {
        super(context);
        init();
    }

    public CompatExpandTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CompatExpandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int totalCount = getStaticLayout().getLineCount();
        if (totalCount > maxLineCount){
            if (mExpanded){
                setText(mText);
                listener.onExpand();
            }else {
                totalCount = maxLineCount;
                int start = getStaticLayout().getLineStart(totalCount - 1);
                setText(mText.substring(0, start));
                listener.onCollapse();
            }
        }else {
            setText(mText);
            listener.onLoss();
        }
        int lineHeight = 0;
        for (int i = 0; i < totalCount; i++) {
            Rect lineBound = new Rect();
            getStaticLayout().getLineBounds(i, lineBound);
            lineHeight += lineBound.height();
        }
        lineHeight += getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(getMeasuredWidth(), lineHeight);

    }

    private StaticLayout getStaticLayout(){
        StaticLayout sl = new StaticLayout(mText, getPaint(), getMeasuredWidth() - getPaddingLeft() - getPaddingRight()
                , Layout.Alignment.ALIGN_CENTER, 1, 0, true);
        return sl;
    }

    public void setExpandText(String text, Boolean expanded, OnExpandListener listener){
        mText = text;
        mExpanded = expanded;
        this.listener = listener;
        setText(text);
    }

    public void setChanged(Boolean expanded){
        mExpanded = expanded;
        requestLayout();
    }

    public interface OnExpandListener{
        void onExpand();
        void onCollapse();
        void onLoss();
    }
}
