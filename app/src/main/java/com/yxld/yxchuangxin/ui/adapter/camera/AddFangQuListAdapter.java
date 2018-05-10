package com.yxld.yxchuangxin.ui.adapter.camera;

import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.FangquEntity;

import java.util.List;

/**
 * 作者：Android on 2017/9/6
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class AddFangQuListAdapter extends BaseQuickAdapter <FangquEntity.DataBean, BaseViewHolder> {

    public AddFangQuListAdapter(@Nullable List<FangquEntity.DataBean> data) {
        super(R.layout.seven_week_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, FangquEntity.DataBean content) {
        CheckBox checkBox = baseViewHolder.getView(R.id.check);
        baseViewHolder.setOnCheckedChangeListener(R.id.check, new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    content.setCheck(true);
                } else {
                    content.setCheck(false);
                }
            }
        });
//        checkBox.setClickable(false);
        if (content.isCheck()) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        baseViewHolder.setText(R.id.check, content.getShebeiName());
    }
}

