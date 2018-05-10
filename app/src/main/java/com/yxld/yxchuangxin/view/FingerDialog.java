package com.yxld.yxchuangxin.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;

import butterknife.BindView;

/**
 * @author xlei
 * @Date 2018/4/4.
 */

public class FingerDialog extends BaseDialog {
    private ImageView mImgFinger;
    private TextView mTvDialogTitle;
    private TextView mTvDialogMessage;
    private Button mBtDialogCancel;
    private Button mBtDialogConfirm;
    private View line;
    private OnConfirmListener onConfirmListener;

    private int confirmVisible;
    private String message;
    private String title;
    private Context mContext;

    public FingerDialog(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public void initView() {
        setContentView(R.layout.layout_finger_dialog);
        mBtDialogConfirm = (Button) findViewById(R.id.bt_dialog_confirm);
        mBtDialogCancel = (Button) findViewById(R.id.bt_dialog_cancel);
        mImgFinger = (ImageView) findViewById(R.id.img_finger);
        mTvDialogTitle = (TextView) findViewById(R.id.tv_dialog_title);
        mTvDialogMessage = (TextView) findViewById(R.id.tv_dialog_message);
        line = (View) findViewById(R.id.line);
    }

    @Override
    public void initListener() {
        mBtDialogConfirm.setOnClickListener(this);
        mBtDialogCancel.setOnClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            //如果取消按钮按下时，侦听存在，那么调用侦听的onCancel
            case R.id.bt_dialog_cancel:
                if (onConfirmListener != null) {
                    onConfirmListener.onCancel();
                }
                break;
            case R.id.bt_dialog_confirm:
                if (onConfirmListener != null) {
                    onConfirmListener.onConfirm();
                }
                break;

        }
    }

    public void setConfirmVisible() {
        mBtDialogConfirm.setVisibility(View.GONE);
        line.setVisibility(View.GONE);
    }

    public void setMessage(String message) {
        mTvDialogMessage.setText(message);
        mTvDialogMessage.setTextColor(mContext.getResources().getColor(R.color.black));

    }

    public void setErrMessageColor(String message) {
        mTvDialogMessage.setText(message);
        mTvDialogMessage.setTextColor(mContext.getResources().getColor(R.color.red));
    }

    public OnConfirmListener getOnConfirmListener() {
        return onConfirmListener;
    }

    public void setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
    }

    public interface OnConfirmListener {
        void onCancel();

        void onConfirm();
    }
}
