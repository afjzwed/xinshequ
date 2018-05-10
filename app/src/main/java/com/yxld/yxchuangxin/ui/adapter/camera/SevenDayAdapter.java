package com.yxld.yxchuangxin.ui.adapter.camera;

import android.support.annotation.Nullable;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;

import java.util.List;

/**
 * 作者：Android on 2017/9/6
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class SevenDayAdapter extends BaseQuickAdapter <SevenDayAdapter.xingqi, BaseViewHolder> {

    public SevenDayAdapter(@Nullable List<xingqi> data) {
        super(R.layout.seven_week_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, xingqi content) {
        baseViewHolder.setText(R.id.check, content.getName());
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
    }

    public static class xingqi{
        public xingqi(String xingqi) {
            this.name = xingqi;
            isCheck = false;
        }
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

