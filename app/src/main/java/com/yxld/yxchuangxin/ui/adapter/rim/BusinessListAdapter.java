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

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.CxwyBusiness;
import com.yxld.yxchuangxin.entity.CxwyBusinessInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 89876 on 2017/4/25 0025.
 * 个人主页：http://yishangfei.me
 * Github:https://github.com/yishangfei
 * <p>
 */
public class BusinessListAdapter extends BaseQuickAdapter<CxwyBusiness.DataBean,BaseViewHolder> {

    public BusinessListAdapter(List<CxwyBusiness.DataBean> data) {
        super(R.layout.item_business_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CxwyBusiness.DataBean item) {
        helper.setText(R.id.rim_list_name,item.getBusiness().getBusinessName());
//        helper.setText(R.id.tv_sale_count, item.getBusiness().get)
      //  helper.setRating(R.id.rim_list_ratingBar, item.getScore());
        if (item.getActivity().size() != 0) {
            View line = helper.getView(R.id.rim_list_view);
            line.setVisibility(View.VISIBLE);
            List<CxwyBusinessInfo.DataBean.ActivityBean> list = new ArrayList<>();
            for (int i = 0; i < item.getActivity().size(); i++) {
                CxwyBusinessInfo.DataBean.ActivityBean activityBean = new CxwyBusinessInfo.DataBean.ActivityBean();
                activityBean.setActivityBusinessNumber(item.getActivity().get(i).getActivityBusinessNumber());
                activityBean.setActivityEndTime(item.getActivity().get(i).getActivityEndTime());
                activityBean.setActivityExplain(item.getActivity().get(i).getActivityExplain());
                activityBean.setActivityId(item.getActivity().get(i).getActivityId());
                activityBean.setActivityName(item.getActivity().get(i).getActivityName());
                activityBean.setActivityShare(item.getActivity().get(i).getActivityEndTime());
                activityBean.setActivityStartTime(item.getActivity().get(i).getActivityStartTime());
                activityBean.setActivityStatus(item.getActivity().get(i).getActivityStatus());
                list.add(activityBean);
            }
            RecyclerView recyclerview = helper.getView(R.id.recyclerview);
            recyclerview.setVisibility(View.VISIBLE);
            BusinessListSaleAdapter saleAdapter = new BusinessListSaleAdapter(list);
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerview.setAdapter(saleAdapter);
        } else {
            View line = helper.getView(R.id.rim_list_view);
            line.setVisibility(View.GONE);
            RecyclerView recyclerview = helper.getView(R.id.recyclerview);
            recyclerview.setVisibility(View.GONE);
        }
        TextView rim_list_fraction = helper.getView(R.id.rim_list_fraction);

        try {
          //  helper.setText(R.id.rim_list_fraction, item.getScore() + "分");
//            if (item.getScore() == 0) {
//                rim_list_fraction.setVisibility(View.INVISIBLE);
//            } else {
//                rim_list_fraction.setVisibility(View.VISIBLE);
//            }
        } catch (Exception e) {

        }
    }

}
