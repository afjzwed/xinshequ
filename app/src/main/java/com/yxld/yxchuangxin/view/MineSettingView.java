package com.yxld.yxchuangxin.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/19
 * @descprition:
 */

public class MineSettingView extends AutoRelativeLayout {
    private TextView mDesc;
    private ImageView mIcon;
    private View mLine;
    public MineSettingView(Context context) {
        this(context,null);
    }

    public MineSettingView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MineSettingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MineSettingView,defStyle,0);
        String desc = ta.getString(R.styleable.MineSettingView_text_setting);
        Drawable drawable = ta.getDrawable(R.styleable.MineSettingView_src_icon);
        boolean showLine = ta.getBoolean(R.styleable.MineSettingView_show_line,true);
        ta.recycle();

        mDesc.setText(desc);
        mIcon.setImageDrawable(drawable);
        mLine.setVisibility(showLine?VISIBLE:INVISIBLE);

    }

    private void init() {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.mine_setting,this);
        mDesc = (TextView) root.findViewById(R.id.tv_mine_setting);
        mIcon = (ImageView) root.findViewById(R.id.iv_mine_setting);
        mLine = root.findViewById(R.id.view_mine_setting_line);
    }

    public void setText(String text){
        mDesc.setText(text);
    }

    public void setIcon(Drawable drawable){
        mIcon.setImageDrawable(drawable);
    }
    public void setIcon(int resourcesId){
        mIcon.setImageResource(resourcesId);
    }
    public ImageView getIconView(){
        return mIcon;
    }
    public void setGrayLineVisible(boolean visible){
        mLine.setVisibility(visible?VISIBLE:INVISIBLE);
    }
}
