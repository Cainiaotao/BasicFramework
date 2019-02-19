package com.example.basicframework.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.example.basicframework.R;
import com.necer.entity.NDate;
import com.necer.painter.CalendarPainter;

public class CustomPainter implements CalendarPainter {
    private Paint mCirclePaint;
    private Context context;

    public CustomPainter(Context context) {
        this.context = context;
        this.context = context;
        mCirclePaint = new Paint();
        mCirclePaint.setTextSize(PixelUtils.INSTANCE.sp2px(context,15));
        mCirclePaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public void onDrawToday(Canvas canvas, Rect rect, NDate nDate, boolean isSelect) {
        drawSolidCircle(canvas,rect);
        //canvas.drawText(nDate.localDate.getDayOfMonth() + "", rect.centerX(), getBaseLineY(rect), mCirclePaint);
    }

    @Override
    public void onDrawCurrentMonthOrWeek(Canvas canvas, Rect rect, NDate nDate, boolean isSelect) {

    }

    @Override
    public void onDrawNotCurrentMonth(Canvas canvas, Rect rect, NDate nDate) {

    }

    @Override
    public void onDrawDisableDate(Canvas canvas, Rect rect, NDate nDate) {

    }

    //实心圆
    private void drawSolidCircle(Canvas canvas, Rect rect) {
        mCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mCirclePaint.setStrokeWidth(50);//宽度
        mCirclePaint.setColor(context.getResources().getColor(R.color.color_007efa));
        mCirclePaint.setColor(Color.WHITE);
        canvas.drawCircle(rect.centerX(), rect.centerY(), 20, mCirclePaint);
    }

}
