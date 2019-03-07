package com.example.basicframework.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextView;
import com.example.basicframework.R;
import com.example.basicframework.utils.PixelUtils;

public class NewExpandTextView extends AppCompatTextView {


    private static final int MAX_COLLAPSED_LINES = 5;// The default number of lines 默认显示行数为8行
    private static final int DEFAULT_ANIM_DURATION = 300; // The default animation duration 默认动画时长为300ms
    private static final float DEFAULT_ANIM_ALPHA_START = 0.7f;// The default alpha value when the animation starts

    private int mMaxCollapsedLines = MAX_COLLAPSED_LINES;//最大显示行数
    private int mAnimationDuration = DEFAULT_ANIM_DURATION;
    private float mAnimAlphaStart = DEFAULT_ANIM_ALPHA_START;

    private int mCollapsedHeight;
    private int mTextHeightWithMaxLines;

    private boolean mCollapsed = true; // Show short version as default.标示现在所处的折叠状态
    private boolean mAnimating = false;
    private boolean needCollapse = true; //标示是否需要折叠已显示末尾的图标

    /* Listener for callback */
    private OnExpandStateChangeListener mListener;

    public NewExpandTextView(Context context) {
        super(context);
    }

    public NewExpandTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewExpandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NewExpandTextView, defStyleAttr, 0);
//        mMaxCollapsedLines = typedArray.getInt(R.styleable.NewExpandTextView_maxCollapsedLines, MAX_COLLAPSED_LINES);
//        mAnimationDuration = typedArray.getInt(R.styleable.NewExpandTextView_animDuration, DEFAULT_ANIM_DURATION);
//        mAnimAlphaStart = typedArray.getFloat(R.styleable.NewExpandTextView_animAlphaStart, DEFAULT_ANIM_ALPHA_START);
//        mExpandDrawable = typedArray.getDrawable(R.styleable.NewExpandTextView_expandDrawable);
//        mCollapseDrawable = typedArray.getDrawable(R.styleable.NewExpandTextView_collapseDrawable);
//        arrowAlign = typedArray.getInteger(R.styleable.NewExpandTextView_arrowAlign, ALIGN_RIGHT_BOTTOM);
//        arrowPosition = typedArray.getInteger(R.styleable.NewExpandTextView_arrowPosition, POSITION_RIGHT);
//        arrowDrawablePadding = (int) typedArray.getDimension(R.styleable.NewExpandTextView_arrowPadding, PixelUtils.INSTANCE.dp2px(context, 2f));
//
//        typedArray.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getVisibility() == GONE || mAnimating) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }

        //重置高度重新测量
        getLayoutParams().height = -2;//设置为wrap_content，重新measure
        setMaxLines(Integer.MAX_VALUE);
        //测量TextView总高度
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getLineCount() <= mMaxCollapsedLines) {
            needCollapse = false;
            return;
        }

        needCollapse = true;

        mTextHeightWithMaxLines = getRealTextViewHeight(this);
        if (mCollapsed) {
            setMaxLines(mMaxCollapsedLines);
        }


        //设置完成后重新测量
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mCollapsed) {
            mCollapsedHeight = getMeasuredHeight();
        }

    }


    @Override
    public void setText(CharSequence text, BufferType type) {
        setCollapsed(true);
        super.setText(text, type);
    }


    public void onClickStatus(){
        if (!needCollapse) {
            return;//行数不足,不响应点击事件
        }
        mCollapsed = !mCollapsed;

        // mark that the animation is in progress
        mAnimating = true;

        Animation animation;
        if (mCollapsed) {
            animation = new ExpandCollapseAnimation(this, getHeight(), mCollapsedHeight);
        } else {
            animation = new ExpandCollapseAnimation(this, getHeight(), mTextHeightWithMaxLines);
        }

        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (mListener != null) {
                    mListener.onChangeStateStart(!mCollapsed);
                }
                applyAlphaAnimation(NewExpandTextView.this, mAnimAlphaStart);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // clear animation here to avoid repeated applyTransformation() calls
                clearAnimation();
                // clear the animation flag
                mAnimating = false;

                // notify the listener
                if (mListener != null) {
                    mListener.onExpandStateChanged(NewExpandTextView.this, !mCollapsed);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        clearAnimation();
        startAnimation(animation);
    }

    private class ExpandCollapseAnimation extends Animation {
        private final View mTargetView;
        private final int mStartHeight;
        private final int mEndHeight;

        public ExpandCollapseAnimation(View view, int startHeight, int endHeight) {
            mTargetView = view;
            mStartHeight = startHeight;
            mEndHeight = endHeight;
            setDuration(mAnimationDuration);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            final int newHeight = (int) ((mEndHeight - mStartHeight) * interpolatedTime + mStartHeight);
            mTargetView.getLayoutParams().height = newHeight;
            setMaxHeight(newHeight);
            if (Float.compare(mAnimAlphaStart, 1.0f) != 0) {
                applyAlphaAnimation(NewExpandTextView.this, mAnimAlphaStart + interpolatedTime * (1.0f - mAnimAlphaStart));
            }
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void applyAlphaAnimation(View view, float alpha) {
        if (isPostHoneycomb()) {
            view.setAlpha(alpha);
        } else {
            AlphaAnimation alphaAnimation = new AlphaAnimation(alpha, alpha);
            // make it instant
            alphaAnimation.setDuration(0);
            alphaAnimation.setFillAfter(true);
            view.startAnimation(alphaAnimation);
        }
    }

    private boolean isPostHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    private boolean isPostLolipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    private int getRealTextViewHeight(TextView textView) {
        int textHeight = textView.getLayout().getLineTop(textView.getLineCount());
        int padding = textView.getCompoundPaddingTop() + textView.getCompoundPaddingBottom();
        return textHeight + padding;
    }


    public void setCollapsed(boolean isCollapsed) {
        mCollapsed = isCollapsed;
    }


    public interface OnExpandStateChangeListener {
        void onChangeStateStart(boolean willExpand);
        /**
         * Called when the expand/collapse animation has been finished
         *
         * @param textView   - TextView being expanded/collapsed
         * @param isExpanded - true if the TextView has been expanded
         */
        void onExpandStateChanged(TextView textView, boolean isExpanded);
    }

    public void setOnExpandStateChangeListener(OnExpandStateChangeListener listener) {
        mListener = listener;
    }

}
