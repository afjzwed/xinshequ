package com.yxld.yxchuangxin.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import com.socks.library.KLog;

/**
 * @author xlei
 * @Date 2018/1/5.
 */

public class TranslucentScrollView extends NestedScrollView {
    //渐变的视图
    private View transView;
    //渐变颜色
    private int transColor = Color.WHITE;
    //渐变视图高度
    private int transViewHeight = 0;
    //渐变结束位置
    private int transEndY = 0;

    public TranslucentScrollView(Context context) {
        super(context);
    }

    public TranslucentScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TranslucentScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (transView != null) {
            transView.setBackgroundColor(ColorUtils.setAlphaComponent(transColor, getTransAlpha()));
        }
    }

    /**
     * 设置渐变视图
     *
     * @param transView       渐变的视图
     * @param transColor      渐变颜色
     * @param transViewHeight 渐变视图高度
     * @param transEndY       渐变结束位置
     */
    public void setTransView(View transView, @ColorInt int transColor, int transViewHeight, int transEndY) {
        this.transView = transView;
        //初始视图-透明
        this.transView.setBackgroundColor(ColorUtils.setAlphaComponent(transColor, 0));
        this.transViewHeight = transViewHeight;
        this.transEndY = transEndY;
        this.transColor = transColor;
        if (transViewHeight > transEndY) {
            throw new RuntimeException("transViewHeight 不得大于 transEndY .. ");
        }
    }

    /**
     * 获取透明度
     *
     * @return
     */
    private int getTransAlpha() {
        float scrollY = getScrollY();
        float viewOffsetY = transEndY - transViewHeight;
        float offset = 1 - Math.max((viewOffsetY - scrollY) / viewOffsetY, 0f);

        //透明度
        KLog.i("scrollY--"+scrollY+"viewOffsetY"+viewOffsetY+""+"offset-----"+offset+"trans------"+Math.abs((int) (offset * 255)));
        return Math.abs((int) (offset * 255));
    }

}
