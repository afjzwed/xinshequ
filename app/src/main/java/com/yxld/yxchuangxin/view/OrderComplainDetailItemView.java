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
 * @date: 2017/7/5
 * @descprition:
 */

public class OrderComplainDetailItemView extends AutoLinearLayout {
    private TextView tvDesc;
    private TextView tvContent;
    private View line;
    public OrderComplainDetailItemView(Context context) {
        this(context,null);
    }

    public OrderComplainDetailItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public OrderComplainDetailItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.OrderComplainDetailItemView);
        String desc = ta.getString(R.styleable.OrderComplainDetailItemView_text_desc);
        boolean showLine = ta.getBoolean(R.styleable.OrderComplainDetailItemView_show_line,true);
        ta.recycle();
        tvDesc.setText(desc);
        line.setVisibility(showLine?VISIBLE:INVISIBLE);
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_order_complain_detail_item,this);
        tvDesc = (TextView) view.findViewById(R.id.tv_desc);
        tvContent = (TextView) view.findViewById(R.id.tv_content);
        line = view.findViewById(R.id.line);
    }

    public void setContent(String content){
        tvContent.setText(content);
    }
}
