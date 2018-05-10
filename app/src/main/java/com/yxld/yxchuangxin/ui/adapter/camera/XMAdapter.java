package com.yxld.yxchuangxin.ui.adapter.camera;

import android.os.Environment;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.XMsxt;

import java.util.List;

/**
 * 作者：Android on 2017/7/26
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class XMAdapter extends BaseQuickAdapter<XMsxt.DataBean.SxtBean, BaseViewHolder> {
    public XMAdapter(@Nullable List<XMsxt.DataBean.SxtBean> data) {
        super(R.layout.rv_activity_aisle_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, XMsxt.DataBean.SxtBean item) {
        helper.setText(R.id.aisle_item_name,item.getTongdaoname());
        String url = Environment.getExternalStorageDirectory().getPath() + "/chuangxin/CapturePicture/" + item.getShebeixuliehao()  + "" + item.getTongdaohao() +".jpg";
        ImageView img = helper.getView(R.id.aisle_item_icon);
        Glide.with(mContext)
                .load(url)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.mipmap.camera_aisle_item)
                .into(img);
    }

}
