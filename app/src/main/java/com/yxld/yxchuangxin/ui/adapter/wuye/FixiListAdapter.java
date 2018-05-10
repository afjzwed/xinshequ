package com.yxld.yxchuangxin.ui.adapter.wuye;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.CxwyBaoxiu;
import com.yxld.yxchuangxin.ui.activity.base.ViewPagerActivity;

import java.util.Arrays;
import java.util.List;

/**
 * 作者：hu on 2017/6/15
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class FixiListAdapter extends BaseQuickAdapter<CxwyBaoxiu.RowsBean, BaseViewHolder> {

    public FixiListAdapter(@Nullable List<CxwyBaoxiu.RowsBean> data) {
        super(R.layout.item_fix_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CxwyBaoxiu.RowsBean item) {
        helper.setText(R.id.baoxiiu_danhao, item.getBaoxiu_danhao())
                .setText(R.id.baoxiu_shijian, item.getBaoxiu_lrtime())
                .setText(R.id.tv_detail, item.getBaoxiu_xiangmu())
                .setText(R.id.tv_address, item.getBaoxiu_didian());
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        List<String> imageList;
        String[] sourceStrArray = item.getBaoxiu_img().split(";");
        imageList = Arrays.asList(sourceStrArray);
        if (item.getBaoxiu_img().equals("")) {
            recyclerView.setVisibility(View.GONE);
            KLog.i("隐藏recyclerview");
        } else {
            recyclerView.setVisibility(View.VISIBLE);
        }
        FixListImageAdapter fixListImageAdapter = new FixListImageAdapter(imageList);
        recyclerView.setAdapter(fixListImageAdapter);
        fixListImageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent(mContext, ViewPagerActivity.class);
                intent.putExtra("url", item.getBaoxiu_img());
                intent.putExtra("item", i);
                mContext.startActivity(intent);
            }
        });
        if("1".equals(item.getBaoxiu_status())){
            helper.setText(R.id.tv_status, "待指派负责人");
        }else if("2".equals(item.getBaoxiu_status())){
            helper.setText(R.id.tv_status, "待指派维修人");
        }else if("3".equals(item.getBaoxiu_status())){
            helper.setText(R.id.tv_status, "查看现场中");
        }else if("4".equals(item.getBaoxiu_status())){
            helper.setText(R.id.tv_status, "维修中");
        }else if("5".equals(item.getBaoxiu_status())){
            helper.setText(R.id.tv_status, "查验中");
        }else if("6".equals(item.getBaoxiu_status())){
            helper.setText(R.id.tv_status, "回访中");
        }else if("7".equals(item.getBaoxiu_status())){
            helper.setText(R.id.tv_status, "关闭");
        }else{
            helper.setText(R.id.tv_status, "重新指派");
        }
    }
}
