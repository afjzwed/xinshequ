package com.yxld.yxchuangxin.ui.adapter.camera;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.FangquUtil;
import com.yxld.yxchuangxin.entity.FangquList;

import java.util.List;

import cn.refactor.library.SmoothCheckBox;

/**
 * 作者：Android on 2017/9/6
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class CheckFangQuAdapter extends BaseQuickAdapter  <FangquList, BaseViewHolder> {

    public CheckFangQuAdapter(@Nullable List<FangquList> data) {
        super(R.layout.check_fangqu_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, FangquList content) {
        baseViewHolder.setText(R.id.tv_fangqu_leixing, FangquUtil.parseFangqu(content.getFangQuName()));
        RecyclerView recyclerView = baseViewHolder.getView(R.id.recycerView);
        GridLayoutManager gl = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(gl);
        AddFangQuListAdapter sevenDayAdapter = new AddFangQuListAdapter(content.getFangQuList());
        recyclerView.setAdapter(sevenDayAdapter);
        SmoothCheckBox smoothCheckBox = baseViewHolder.getView(R.id.cb_fangqu);
        smoothCheckBox.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox smoothCheckBox, boolean b) {
                if (b) {
                    for (int i = 0; i < content.getFangQuList().size(); i++) {
                        content.getFangQuList().get(i).setCheck(true);
                    }
                } else {
                    for (int i = 0; i < content.getFangQuList().size(); i++) {
                        content.getFangQuList().get(i).setCheck(false);
                    }
                }
                sevenDayAdapter.notifyDataSetChanged();
            }
        });
    }

    public class Fangqu {
        private String name;
        private boolean isCheck;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }
    }
}

