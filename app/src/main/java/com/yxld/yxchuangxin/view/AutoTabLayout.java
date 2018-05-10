package com.yxld.yxchuangxin.view;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;

import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.utils.AutoLayoutHelper;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/15
 * @descprition:
 */

public class AutoTabLayout extends TabLayout {
    private final AutoLayoutHelper mHelper = new AutoLayoutHelper(this);

    public AutoTabLayout(Context context) {
        super(context);
    }

    public AutoTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new AutoFrameLayout.LayoutParams(getContext(),attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode()){
            mHelper.adjustChildren();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
