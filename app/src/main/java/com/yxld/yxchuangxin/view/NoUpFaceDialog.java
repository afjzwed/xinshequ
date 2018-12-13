package com.yxld.yxchuangxin.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.yxld.yxchuangxin.R;

/**
 * 无权限上传人脸
 * Created by William on 2018/12/13.
 */

public class NoUpFaceDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private int mResId;
    private ImageButton ib1;

    public NoUpFaceDialog(Context context) {
        this(context, 0);
    }

    public NoUpFaceDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        this.mResId = themeResId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_noup_face);
        ib1 = (ImageButton) findViewById(R.id.iv_close_fase);
        ib1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close_fase:
                NoUpFaceDialog.this.dismiss();
                break;
        }
    }
}
