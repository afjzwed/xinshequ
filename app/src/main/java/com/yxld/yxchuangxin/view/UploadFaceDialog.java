package com.yxld.yxchuangxin.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.data.api.API;

/**
 * 上传人脸图片弹框
 * Created by William on 2018/12/13.
 */

public class UploadFaceDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private int mResId;
    private String faceurl;
    private ImageButton ib1;
    private Button bt_album;
    private Button bt_camera;
    private ImageView iv_face_shili;
    private ImageView iv_ic_shili;

    public UploadFaceDialog(Context context) {
        this(context, 0);
    }

    public UploadFaceDialog(Context context,String faceurl) {
        this(context, 0);
        this.faceurl = faceurl;
    }

    public UploadFaceDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        this.mResId = themeResId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_upload_face);

        ib1 = (ImageButton) findViewById(R.id.iv_close_fase);
        bt_album = (Button) findViewById(R.id.bt_album);
        bt_camera = (Button) findViewById(R.id.bt_camera);
        iv_face_shili = (ImageView) findViewById(R.id.iv_face_shili);
        iv_ic_shili = (ImageView) findViewById(R.id.iv_ic_shili);
        ib1.setOnClickListener(this);

        if (TextUtils.isEmpty(faceurl)) {
            iv_ic_shili.setVisibility(View.VISIBLE);
        } else {
            iv_ic_shili.setVisibility(View.GONE);
            Glide.with(mContext)
//                    .load(API.PIC + faceurl)
                    .load(faceurl)
                    .into(iv_face_shili);
        }
    }

    public Button getBt_album() {
        return bt_album;
    }

    public Button getBt_camera() {
        return bt_camera;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close_fase:
                UploadFaceDialog.this.dismiss();
                break;
        }
    }
}
