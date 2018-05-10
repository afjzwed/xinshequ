package com.yxld.yxchuangxin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/15
 * @descprition: 欣商城的ActionBar
 */

public class MarketActionBarView extends AutoLinearLayout {
    private View mStatusBarView;
    private AutoFrameLayout mViewContainer;
    public MarketActionBarView(Context context) {
        this(context,null);
    }

    public MarketActionBarView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MarketActionBarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.MarketActionBarView,0,defStyle);
        int color = ta.getColor(R.styleable.MarketActionBarView_action_bar_color,0xff9934);
        ta.recycle();
        mViewContainer.setBackgroundColor(color);
        mStatusBarView.setBackgroundColor(color);

    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_custom_action_bar,this);
        mStatusBarView = view.findViewById(R.id.view_status_bar);
        mViewContainer = (AutoFrameLayout) view.findViewById(R.id.action_bar_layout_root);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        super.onLayout(changed, l, t, r, b);
        int count = getChildCount();
        Log.e("View",count+"");
        View childView = null;
        for (int i = 0 ;i < count && i < 1 ;i++){
            childView = getChildAt(count);
            if(mViewContainer.getChildCount()>0){
                mViewContainer.removeAllViews();
            }
            mViewContainer.addView(childView);
        }
    }


    public void setStatusBarHeight(){
        int statusBarHeight = UIUtils.getStatusBarHeight(UIUtils.getContext());
        AutoLinearLayout.LayoutParams layoutParams = (LayoutParams) mStatusBarView.getLayoutParams();
        layoutParams.height = statusBarHeight;
        mStatusBarView.setLayoutParams(layoutParams);
    }
}
