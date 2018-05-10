package com.yxld.yxchuangxin.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;

import com.zhy.autolayout.utils.AutoLayoutHelper;

/**
 * 作者：hu on 2017/6/22
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class AutoCoordinatorLayout extends CoordinatorLayout {
    private final AutoLayoutHelper mHelper = new AutoLayoutHelper(this);
    public AutoCoordinatorLayout(Context context) {
        super(context);
    }

    public AutoCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    public LayoutParams generateLayoutParams(AttributeSet attrs)
//    {
//        return new CoordinatorLayout.LayoutParams(UIUtils.getDisplayWidth((Activity) getContext()), UIUtils.getDisplayHeigh((Activity) getContext()));
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        if (!isInEditMode())
        {
            mHelper.adjustChildren();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
