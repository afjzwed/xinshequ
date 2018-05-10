package com.yxld.yxchuangxin.ui.adapter.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.AppCameraList;

import java.io.File;
import java.util.List;

import static com.yxld.yxchuangxin.ui.activity.camera.presenter.DeviceListPresenter.LoginID;


/**
 * Created by yishangfei on 2017/2/22 0022.
 * 邮箱：yishangfei@foxmail.com
 */
public class CameraListAdapter extends BaseQuickAdapter<AppCameraList, BaseViewHolder> {

    public CameraListAdapter(List<AppCameraList> list) {
        super(R.layout.cameralist_item_layout, list);
    }


    @Override
    protected void convert(BaseViewHolder viewHolder, AppCameraList item) {
        Button status=viewHolder.getView(R.id.camera_status);
        status.getBackground().setAlpha(102);
        viewHolder.setVisible(R.id.share, item.getJiashu() == -1? true : false);
        if (item.getSb_status()==1){
            viewHolder.setText(R.id.camera_status,"在线");
        }else {
            viewHolder.setText(R.id.camera_status,"离线");
        }
        if (item.getDefenceState()==1){
            viewHolder.setText(R.id.camera_bufang,"已布防");
        }else {
            viewHolder.setText(R.id.camera_bufang,"未布防");
        }


        String filepath= Environment.getExternalStorageDirectory() + "/screenshot/tempHead/" + getUserID() + "/" + item.getSb_ipc_id() + ".jpg";
        File file = new File(filepath);
        if (file.exists()) {
            Bitmap bm = BitmapFactory.decodeFile(filepath);
            //将图片显示到ImageView中
            viewHolder.setImageBitmap(R.id.camera_image,bm);
        }
        viewHolder.setText(R.id.camera_name, item.getSb_name())
                .addOnLongClickListener(R.id.camera_image)
                .addOnClickListener(R.id.camera_image)
                .addOnClickListener(R.id.share)
        .addOnClickListener(R.id.camera_video);
    }

    public String getUserID() {
        String usId;
        try {
            usId = "0" + String.valueOf((Integer.parseInt(LoginID) & 0x7fffffff));
            return usId;
        } catch (NumberFormatException e) {
            return LoginID;
        }
    }
}