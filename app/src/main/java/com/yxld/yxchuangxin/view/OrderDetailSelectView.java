package com.yxld.yxchuangxin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/22
 * @descprition:
 */

public class OrderDetailSelectView extends AutoLinearLayout {
    private View viewLine;
    private TextView tvTitle;
    private TextView tvDesc;

    public OrderDetailSelectView(Context context) {
        this(context,null);
    }

    public OrderDetailSelectView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public OrderDetailSelectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.OrderDetailSelectView);
        String title = ta.getString(R.styleable.OrderDetailSelectView_text_title);
        String desc = ta.getString(R.styleable.OrderDetailSelectView_text_desc);
        boolean showLine = ta.getBoolean(R.styleable.OrderDetailSelectView_show_line,true);
        ta.recycle();

        viewLine.setVisibility(showLine?VISIBLE:INVISIBLE);
        tvTitle.setText(title);
        tvDesc.setText(desc);


    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_order_detail_text_view,this);
        viewLine = view.findViewById(R.id.line_order_detail_text);
        tvTitle = (TextView) view.findViewById(R.id.tv_order_detail_title);
        tvDesc = (TextView) view.findViewById(R.id.tv_order_detail_desc);
    }

    public void setDesc(CharSequence string){
        tvDesc.setText(string);
    }
    public TextView getTvDesc(){
        return tvDesc;
    }
//    public void setTvDescColor(int color) {
//        tvDesc.setTextColor(color);
//    }
}
