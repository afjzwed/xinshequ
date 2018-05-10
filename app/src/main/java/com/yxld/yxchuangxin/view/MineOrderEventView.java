package com.yxld.yxchuangxin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/19
 * @descprition:
 */

public class MineOrderEventView extends AutoLinearLayout {
    private TextView mDot;
    private TextView mDesc;
    private ImageView mIcon;
    public MineOrderEventView(Context context) {
        this(context, null);
    }

    public MineOrderEventView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MineOrderEventView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.MineOrderEventView,defStyle,0);
        String textDesc = ta.getString(R.styleable.MineOrderEventView_text_desc);
        int color = ta.getColor(R.styleable.MineOrderEventView_color_desc,0x646464);
        Drawable drawable = ta.getDrawable(R.styleable.MineOrderEventView_icon_src);
        ta.recycle();

        if(textDesc!=null){
            mDesc.setText(textDesc);
        }

        mDesc.setTextColor(color);
        mIcon.setImageDrawable(drawable);
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.mine_order_event_view, this);
        mDot = (TextView) view.findViewById(R.id.dot_mine_order_event);
        mDesc = (TextView) view.findViewById(R.id.tv_mine_order_event);
        mIcon = (ImageView) view.findViewById(R.id.iv_mine_order_event);
    }

    public void setDotNum(int num){
        if(num>0){
            mDot.setVisibility(VISIBLE);
            mDot.setText(String.valueOf(num));
        }else {
            mDot.setVisibility(INVISIBLE);
        }
    }

}
