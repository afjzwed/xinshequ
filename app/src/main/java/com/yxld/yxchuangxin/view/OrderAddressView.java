package com.yxld.yxchuangxin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/22
 * @descprition:
 */

public class OrderAddressView extends AutoRelativeLayout {
    private TextView mHint;
    private TextView mName;
    private TextView mAddress;
    private TextView mPhoneNum;
    private ImageView mBackIcon;
    private AutoLinearLayout mAddressContainer;
    public OrderAddressView(Context context) {
        this(context,null);
    }

    public OrderAddressView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public OrderAddressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.OrderAddressView);
        boolean showHint = ta.getBoolean(R.styleable.OrderAddressView_show_hint,true);
        boolean showArrow = ta.getBoolean(R.styleable.OrderAddressView_show_arrow,true);
        ta.recycle();
        mHint.setVisibility(showHint?VISIBLE:INVISIBLE);
        mBackIcon.setVisibility(showArrow?VISIBLE:INVISIBLE);
        mAddressContainer.setVisibility(showHint?INVISIBLE:VISIBLE);

    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_order_address,this);
        mHint = (TextView) view.findViewById(R.id.tv_confirm_order_hint_address);
        mBackIcon = (ImageView) view.findViewById(R.id.icon_confirm_order);
        mAddress = (TextView) view.findViewById(R.id.tv_confirm_order_address);
        mName = (TextView) view.findViewById(R.id.tv_confirm_order_address_name);
        mPhoneNum = (TextView) view.findViewById(R.id.tv_confirm_order_address_phone);
        mAddressContainer = (AutoLinearLayout) view.findViewById(R.id.ll_confirm_order_address_container);
    }

    public void setAddress(String name,String phoneNum,String address){
        if(name == null && phoneNum ==null && address == null){
            mAddressContainer.setVisibility(INVISIBLE);
            mHint.setVisibility(VISIBLE);
        }else {
            if(mHint.getVisibility() == VISIBLE){
                mHint.setVisibility(INVISIBLE);
            }
            if(mAddressContainer.getVisibility() != VISIBLE){
                mAddressContainer.setVisibility(VISIBLE);
            }

            mName.setText(name);
            mPhoneNum.setText(phoneNum);
            mAddress.setText(address);
        }

    }


}
