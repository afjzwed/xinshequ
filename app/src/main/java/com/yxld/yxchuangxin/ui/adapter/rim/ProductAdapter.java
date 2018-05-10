package com.yxld.yxchuangxin.ui.adapter.rim;
////////////////////////////////////////////////////////////////////
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//         佛祖保佑       永无BUG     永不修改                  //
////////////////////////////////////////////////////////////////////

import android.graphics.Paint;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.CxwyProductInfo;
import com.yxld.yxchuangxin.view.ShapedImageView;

import java.util.List;

/**
 * Created by yishangfei on 2017/4/21 0021.
 * 个人主页：http://yishangfei.me
 * Github:https://github.com/yishangfei
 */
public class ProductAdapter extends BaseQuickAdapter<CxwyProductInfo.DataBean, BaseViewHolder> {
    public ProductAdapter(List<CxwyProductInfo.DataBean> data) {
        super(R.layout.item_product_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CxwyProductInfo.DataBean item) {
        helper.setText(R.id.product_name, item.getProductName() + "");
        helper.setText(R.id.product_discount_price, "¥" + StringUitl.get2xiaoshu(item.getProductPreferentialPrice())+" / ");
        helper.setText(R.id.product_price, "¥" + StringUitl.get2xiaoshu(item.getProductPrice()));
        helper.setText(R.id.product_miaoshu, item.getProductDetails());
        helper.addOnClickListener(R.id.iv_add).setTag(R.id.iv_add, "add");
        helper.addOnClickListener(R.id.siv_product1).setTag(R.id.siv_product1, "image");
        TextView tv = helper.getView(R.id.product_price);
        tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        ShapedImageView siv = (ShapedImageView) helper.getView(R.id.siv_product);
        if (item.getProductImage().split(",").length > 0 && StringUitl.isNoEmpty(item.getProductImage().split(",")[0]))

        {
            Glide.with(mContext)
                    .load(API.PIC + item.getProductImage().split(",")[0])
                    .into(siv);
        }
    }
}
