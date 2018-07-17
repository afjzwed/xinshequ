package com.yxld.yxchuangxin.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.widget.Button;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;

/**
 * @author xlei
 * @Date 2018/7/17.
 */

public class CommentDialog extends Dialog {
    private TextView tv_dialog_title;
    private TextView tv_dialog_message;
    private Button bt_dialog_cancel;
    private Button bt_dialog_confirm;
    public CommentDialog(@NonNull Context context) {
        super(context);
        initView();
    }

    public CommentDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        initView();
    }

    protected CommentDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }
    private void initView() {
        setContentView(R.layout.dialog_confirm);
        tv_dialog_title = (TextView) findViewById(R.id.tv_dialog_title);
        tv_dialog_message = (TextView) findViewById(R.id.tv_dialog_message);

        bt_dialog_cancel = (Button) findViewById(R.id.bt_dialog_cancel);
        bt_dialog_confirm = (Button) findViewById(R.id.bt_dialog_confirm);
    }

    public TextView getTv_dialog_title() {
        return tv_dialog_title;
    }

    public void setTv_dialog_title(TextView tv_dialog_title) {
        this.tv_dialog_title = tv_dialog_title;
    }

    public TextView getTv_dialog_message() {
        return tv_dialog_message;
    }

    public void setTv_dialog_message(TextView tv_dialog_message) {
        this.tv_dialog_message = tv_dialog_message;
    }

    public Button getBt_dialog_cancel() {
        return bt_dialog_cancel;
    }

    public void setBt_dialog_cancel(Button bt_dialog_cancel) {
        this.bt_dialog_cancel = bt_dialog_cancel;
    }

    public Button getBt_dialog_confirm() {
        return bt_dialog_confirm;
    }

    public void setBt_dialog_confirm(Button bt_dialog_confirm) {
        this.bt_dialog_confirm = bt_dialog_confirm;
    }
}
