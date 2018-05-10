package com.yxld.yxchuangxin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/21
 * @descprition:
 */

public class MallGoodsListRankView extends AutoRelativeLayout {
    public static final int STATUS_UP_SELECTED = 1; //从低到高
    public static final int STATUS_DOWN_SELECTED = 2; //从高到低
    private static final int STATUS_ALL_UNSELECTED = 0;

    private static final String INSTANCE_PARSE = "instance_parse";
    private static final String INSTANCE_CURRENT_STATUS = "instance_current_status";


    private ImageView mUp;
    private ImageView mDown;
    private TextView mDesc;
    private View mLine;

    private int mCurrentStatus;

    public MallGoodsListRankView(Context context) {
        this(context,null);
    }

    public MallGoodsListRankView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MallGoodsListRankView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.MallGoodsListRankView,defStyleAttr,0);
        String desc = ta.getString(R.styleable.MallGoodsListRankView_text_desc);
        boolean showLine = ta.getBoolean(R.styleable.MallGoodsListRankView_show_line,true);
        ta.recycle();
        mDesc.setText(desc);
        mCurrentStatus = STATUS_ALL_UNSELECTED;
        mLine.setVisibility(showLine?VISIBLE:INVISIBLE);
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_search_to_rank,this);
        mDesc = (TextView) view.findViewById(R.id.tv_goods_list_rank_desc);
        mUp = (ImageView) view.findViewById(R.id.iv_goods_list_rank_up);
        mDown = (ImageView) view.findViewById(R.id.iv_goods_list_rank_down);
        mLine = view.findViewById(R.id.view_goods_list_rank_line);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_PARSE,super.onSaveInstanceState());
        bundle.putInt(INSTANCE_CURRENT_STATUS, mCurrentStatus);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state!=null && state instanceof Bundle){
            Bundle bundle = (Bundle) state;
            mCurrentStatus = bundle.getInt(INSTANCE_CURRENT_STATUS);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_PARSE));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    public void onOtherViewClicked(){
        mCurrentStatus = STATUS_ALL_UNSELECTED;
        onClicked();
    }

    public void onViewClicked(){
        if(mCurrentStatus == STATUS_ALL_UNSELECTED || mCurrentStatus == STATUS_UP_SELECTED){
            mCurrentStatus = STATUS_DOWN_SELECTED;
        }else if(mCurrentStatus == STATUS_DOWN_SELECTED){
            mCurrentStatus = STATUS_UP_SELECTED;
        }
        onClicked();
    }

    public int getCurrentRankMethod(){
        return mCurrentStatus;
    }


    private void onClicked(){
        mUp.setSelected(mCurrentStatus == STATUS_UP_SELECTED);
        mDown.setSelected(mCurrentStatus == STATUS_DOWN_SELECTED);
    }
}
