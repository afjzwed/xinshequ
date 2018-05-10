package com.yxld.yxchuangxin.view;


import android.content.Context;
import android.view.View;

import com.yxld.yxchuangxin.R;
import com.zhy.autolayout.AutoLinearLayout;

public class CameraDialog extends BaseDialog {
    private AutoLinearLayout mAutoLinearLayoutOne;
    private AutoLinearLayout mAutoLinearLayoutTwo;
    private OnAutoListener mOnAutoListener;

    protected CameraDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public static void showDialog(Context context, OnAutoListener confirmListener) {
        CameraDialog dialog = new CameraDialog(context);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnAutoListener(confirmListener);
        dialog.show();
    }


    @Override
    public void initView() {
        setContentView(R.layout.dialog_camera);
        mAutoLinearLayoutOne = (AutoLinearLayout) findViewById(R.id.auto_ll_one);
        mAutoLinearLayoutTwo = (AutoLinearLayout) findViewById(R.id.auto_ll_two);

    }

    @Override
    public void initListener() {
        mAutoLinearLayoutOne.setOnClickListener(this);
        mAutoLinearLayoutTwo.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            //如果取消按钮按下时，侦听存在，那么调用侦听的onCancel
            case R.id.auto_ll_one:
                if (mOnAutoListener != null) {
                    mOnAutoListener.onAutoOne();
                }
                break;
            case R.id.auto_ll_two:
                if (mOnAutoListener != null) {
                    mOnAutoListener.onAutoTwo();
                }
                break;

        }
        //对话框消失
        dismiss();
    }

    public void setOnAutoListener(OnAutoListener confirmListener) {
        this.mOnAutoListener = confirmListener;
    }

    public interface OnAutoListener {
        void onAutoOne();

        void onAutoTwo();
    }
}
