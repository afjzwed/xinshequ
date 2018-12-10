package com.yxld.yxchuangxin.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.yxld.yxchuangxin.R;

/**
 * 示例弹框
 * Created by William on 2018/12/7.
 */

public class ShiliDialog  extends Dialog implements View.OnClickListener  {
    private ImageButton ib1;
    private Context mContext;
    private ImageView iv_shili;
    private int mResId;

    public ShiliDialog(Context context) {
        this(context, 0);

    }

    public ShiliDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        this.mResId = themeResId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_sign_success);
        ib1 = (ImageButton) findViewById(R.id.iv_delete);
        iv_shili = (ImageView) findViewById(R.id.iv_shili);
        ib1.setOnClickListener(this);
        iv_shili.setImageResource(mResId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_delete:
                ShiliDialog.this.dismiss();
                break;
        }
    }
}
