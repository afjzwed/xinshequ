package com.yxld.yxchuangxin.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.text.DecimalFormat;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/22
 * @descprition:
 */

public class ConfirmOrderSelectView extends AutoRelativeLayout {
    private TextView tvDesc;
    private TextView tvPrice;
    private SwitchCompat switchView;
    private AutoRelativeLayout layoutDianZiQuanContainer;
    private EditText tvDianZiQuanCount;
    private AutoFrameLayout layoutMinus;
    private AutoFrameLayout layoutPlus;

    private OnDianZiQuanCountChanged mChanged;
    private OnUseDianZiQuanListener mUseDianZiQuanListener;

    public ConfirmOrderSelectView(Context context) {
        this(context, null);
    }

    public ConfirmOrderSelectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ConfirmOrderSelectView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ConfirmOrderSelectView);
        String desc = ta.getString(R.styleable.ConfirmOrderSelectView_text_desc);
        boolean showPrice = ta.getBoolean(R.styleable.ConfirmOrderSelectView_show_price, true);
        boolean showSwitch = ta.getBoolean(R.styleable.ConfirmOrderSelectView_show_switch, false);
        ta.recycle();
        tvDesc.setText(desc);
        tvPrice.setVisibility(showPrice ? VISIBLE : GONE);
        switchView.setVisibility(showPrice ? GONE : showSwitch ? VISIBLE : GONE);
        layoutDianZiQuanContainer.setVisibility(showPrice ? GONE : showSwitch ? GONE : VISIBLE);


    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_confirm_order_select, this);
        tvDesc = (TextView) view.findViewById(R.id.tv_confirm_order_select_desc);
        tvPrice = (TextView) view.findViewById(R.id.tv_confirm_order_select_price);
        switchView = (SwitchCompat) view.findViewById(R.id.switch_confirm_order);
        layoutMinus = (AutoFrameLayout) view.findViewById(R.id.iv_dianziquan_jian);
        layoutPlus = (AutoFrameLayout) view.findViewById(R.id.iv_dianziquan_jia);
        layoutDianZiQuanContainer = (AutoRelativeLayout) view.findViewById(R.id.rl_dianziquan_container);
        tvDianZiQuanCount = (EditText) view.findViewById(R.id.tv_cart_count);

        layoutPlus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mChanged != null) {
                    mChanged.onDianZiQuanPlus();
                }
            }
        });

        layoutMinus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mChanged != null) {
                    mChanged.onDianZiQuanMinus();
                }
            }
        });


        tvDianZiQuanCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mChanged != null) {
                    mChanged.onDianZiQuanInput(s.toString());
                }
            }
        });


        ColorStateList list = getContext().getResources().getColorStateList(R.color.switch_selector);
        switchView.setThumbTintList(list);
        switchView.setTrackTintList(list);
        switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mUseDianZiQuanListener != null) {
                    mUseDianZiQuanListener.onUserDianZiQuanListener(switchView, isChecked);
                }
            }
        });
    }

    public void setGuangBiao() {
        tvDianZiQuanCount.setSelection(tvDianZiQuanCount.getText().length());
    }

    public void setPrice(double price) {
        DecimalFormat df = new DecimalFormat("######0.00");
        tvPrice.setText("¥ " + df.format(price));
    }

    public boolean switchIsChecked() {
        return switchView.isChecked();
    }

    public void setSwitchChecked(boolean checked) {
        switchView.setChecked(checked);
    }

    public void setDianZiQuanUesCount(int num) {
        tvDianZiQuanCount.setText(String.valueOf(num));
        setGuangBiao();
    }

    public String getDianZiQuanUseCount() {
        return tvDianZiQuanCount.getText().toString();
    }

    public void setDianZiQuanShengYuDesc(int num) {
        tvDesc.setText("剩余电子券" + "(" + num + "张)");
    }

    public void setuDesc(String num) {
        tvDesc.setText(num);
    }

    public void settvPrice(CharSequence num) {
        tvPrice.setText(num);
    }
    public void settvPriceColor(int res) {
        tvPrice.setTextColor(res);
    }
    public void setOnDianZiQuanCountChanged(OnDianZiQuanCountChanged changed) {
        mChanged = changed;
    }

    public void setOnUseDianZiQuanListener(OnUseDianZiQuanListener listener) {
        mUseDianZiQuanListener = listener;
    }

    public interface OnDianZiQuanCountChanged {
        void onDianZiQuanMinus();

        void onDianZiQuanPlus();

        void onDianZiQuanInput(String num);
    }

    public interface OnUseDianZiQuanListener {
        void onUserDianZiQuanListener(SwitchCompat view, boolean isChecked);
    }
}
