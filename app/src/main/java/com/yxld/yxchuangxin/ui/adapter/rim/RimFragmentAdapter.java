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


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.CxwyBusiness;

import java.util.List;

/**
 * Created by 89876 on 2017/4/25 0025.
 * 个人主页：http://yishangfei.me
 * Github:https://github.com/yishangfei
 * <p>
 */
public class RimFragmentAdapter extends BaseQuickAdapter<CxwyBusiness.DataBean, BaseViewHolder> {

    public RimFragmentAdapter(List<CxwyBusiness.DataBean> data) {
        super(R.layout.item_rim_fragment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CxwyBusiness.DataBean item) {
        helper.setText(R.id.tv_name, item.getBusiness().getBusinessName());
        helper.setText(R.id.tv_content, item.getBusiness().getBusinessDetails());
        ImageView iv = helper.getView(R.id.iv_shangjia);
        ImageView ivimg = helper.getView(R.id.iv_img);
//        if (StringUitl.isNoEmpty(item.getBusiness().getBusinessDetails())) {
//            String[] split = item.getBusiness().getBusinessDetails().split(";");
//            Glide.with(mContext)
//                    .load(split[0])
//                    .into(iv);
//            if (split.length > 1 && StringUitl.isNoEmpty(split[1])) {
//                helper.setText(R.id.tv_content, split[1]);
//            }
//        }

        Glide.with(mContext)
//                .load(API.PIC+item.getBusiness().getBusinessLogo())
                .load(API.PIC + item.getBusiness().getBusinessServerIntroduce())
                .into(iv);

//        Log.e("wh",API.PIC +  item.getBusiness().getBusinessServerIntroduce());
        Glide.with(mContext)
                .load(API.PIC+item.getBusiness().getBusinessLogo())
//                .load(API.PIC + item.getBusiness().getBusinessServerIntroduce())
                .into(ivimg);
    }
}
