package com.yxld.yxchuangxin.ui.adapter.camera;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.BuCheFang;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Android on 2017/9/4
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class TimeCheBuFangAdapter extends BaseQuickAdapter<BuCheFang.DataBean, BaseViewHolder> {

    public TimeCheBuFangAdapter(@Nullable List<BuCheFang.DataBean> data) {
        super(R.layout.time_buchefang_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BuCheFang.DataBean baseBack) {
        baseViewHolder.setText(R.id.tv_bufang_shijain, baseBack.getStartTime())
                .setText(R.id.tv_chefang_shijian, baseBack.getEndTime())
                .setText(R.id.tv_bufang_zhuangtai, baseBack.getBuchefangState().equals("0")? "撤防" : "留守布防");//0撤防 1留守布防
        RecyclerView recyclerView = baseViewHolder.getView(R.id.recycerView);
        GridLayoutManager gl = new GridLayoutManager(mContext, 3);
        recyclerView.setLayoutManager(gl);
        List<String> list = new ArrayList<>();
        String[] splitStr = baseBack.getWeeks().split(",");
        for(int i = 0; i < splitStr.length; i++){
            list.add(splitStr[i]);
        }
        WeekAdapter weekAdapter = new WeekAdapter(list);
        recyclerView.setAdapter(weekAdapter);
        if (baseBack.getStartTime().equals("00:00") || baseBack.getEndTime().equals("00:00") ||baseBack.getWeeks().equals("00")) {
            baseViewHolder.setText(R.id.tv_cacanl, "已撤销");
        } else {
            baseViewHolder.setText(R.id.tv_cacanl, "撤销");
            baseViewHolder.addOnClickListener(R.id.tv_cacanl);
        }
    }

    /**
     * 防区类型0-7
     * 			0:普通防区
     *			1:紧急防区
     *			2:留守防区
     *			3:智能防区
     *			4:关闭防区
     *			5:门铃防区
     *			6:迎宾防区
     *			7:求助防区

     * @param leixing
     * @return
     */
    private String parseFangqu(String leixing) {
        switch (leixing) {
            case "0":
                return "普通防区";
            case "1":
                return "紧急防区";
            case "2":
                return "留守防区";
            case "3":
                return "智能防区";
            case "4":
                return "关闭防区";
            case "5":
                return "门铃防区";
            case "6":
                return "迎宾防区";
            case "7":
                return "求助防区";
        }
        return "";
    }
}
