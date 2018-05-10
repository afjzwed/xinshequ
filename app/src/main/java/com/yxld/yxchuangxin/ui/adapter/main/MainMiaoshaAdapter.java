package com.yxld.yxchuangxin.ui.adapter.main;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.GoodsKind;

import java.util.List;

/**
 * 作者：hu on 2017/6/2
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class MainMiaoshaAdapter extends BaseQuickAdapter<GoodsKind.RowsBean.XinpinListsBean, BaseViewHolder> {

    public MainMiaoshaAdapter(List<GoodsKind.RowsBean.XinpinListsBean> data) {
//        super(R.layout.adapter_main_miaosha, data);
        super(R.layout.item_xinping, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsKind.RowsBean.XinpinListsBean item) {
       /* SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append("¥ " + item.getPrice() + "");
        builder.setSpan(new StrikethroughSpan(), 0, builder.length(), 0);

        helper.setText(R.id.tv_saleprice, "¥ " + item.getSalePrice() + "")
                .setText(R.id.tv_price, builder);

        ImageView imageView = helper.getView(R.id.iv_miaosha);
        if(TextUtils.isEmpty(item.getImgUrl())){
            Glide.with(mContext)
                    .load(item.getUrl())
                    .into(imageView);
        }else {
            Uri uri = Uri.parse(API.PIC + item.getImgUrl().split(";")[0]);
            //Uri uri = Uri.parse(API.PIC + curProduct.getShangpinImgSrc1().split(";")[0] +"?imageView2/0/w/150/h/150");
            //  API.PIC+"/wygl/files/img/201605/empty_photo.png" 没有时
            Glide.with(mContext)
                    .load(uri)
                    .into(imageView);
        }*/
        helper.setText(R.id.name, item.getShangpinMing());
        helper.setText(R.id.price, "¥ " + StringUitl.get2xiaoshu(item.getShoujia()))
                .addOnClickListener(R.id.cart);
        helper.setText(R.id.count, item.getSelectCount() + "");
        if (item.getSelectCount() == 0) {
            helper.setVisible(R.id.count, false);
        } else {
            helper.setVisible(R.id.count, true);
        }
        ImageView imageView = helper.getView(R.id.iv_avater);
        String[] img = item.getZhutu().split(",");
        Glide.with(mContext)
                .load(API.PIC + img[0])
                .into(imageView);
    }
}
