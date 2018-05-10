package com.yxld.yxchuangxin.ui.adapter.camera;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.p2p.core.P2PHandler;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.Level0Item;
import com.yxld.yxchuangxin.entity.Level1Item;

import java.util.List;

/**
 * 作者：yishangfei on 2017/1/20 0020 09:27
 * 邮箱：yishangfei@foxmail.com
 */
public class LearnAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {
    private static final String TAG = LearnAdapter.class.getSimpleName();

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    private String deviceId,devicePwd;


    public LearnAdapter(List<MultiItemEntity> data, String deviceId, String devicePwd) {
        super(data);
        this.deviceId=deviceId;
        this.devicePwd=devicePwd;
        addItemType(TYPE_LEVEL_0, R.layout.item_lv0);
        addItemType(TYPE_LEVEL_1, R.layout.item_lv1);
    }

    @Override
    protected void convert(final BaseViewHolder holder, MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                final Level0Item lv0 = (Level0Item)item;
                holder.setImageResource(R.id.lv0_iv, lv0.isExpanded() ? R.mipmap.icon_arrow_b : R.mipmap.icon_arrow_r);
                switch (lv0.title){
                    case 0:
                        holder.setText(R.id.lv0_title,"遥控");
                        break;
                    case 1:
                        holder.setText(R.id.lv0_title, "大厅");
                        break;
                    case 2:
                        holder.setText(R.id.lv0_title, "窗户");
                        break;
                    case 3:
                        holder.setText(R.id.lv0_title, "阳台");
                        break;
                    case 4:
                        holder.setText(R.id.lv0_title, "卧室");
                        break;
                    case 5:
                        holder.setText(R.id.lv0_title, "厨房");
                        break;
                    case 6:
                        holder.setText(R.id.lv0_title, "庭院");
                        break;
                    case 7:
                        holder.setText(R.id.lv0_title, "门锁");
                        break;
                    case 8:
                        holder.setText(R.id.lv0_title, "其他");
                        break;
                }
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        if (lv0.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                break;
            case TYPE_LEVEL_1:
                final Level1Item lv1 = (Level1Item)item;
                if (lv1.title==1){
                    holder.setText(R.id.lv1_title, "未学习");
                }else {
                    holder.setText(R.id.lv1_title, "已学习");
                }
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (lv1.title==1){
                            Log.d("...", "1");
                            P2PHandler.getInstance().setDefenceAreaState(deviceId,devicePwd,lv1.lines,lv1.few,0);
                            P2PHandler.getInstance().getDefenceArea(deviceId,devicePwd);
                        }else {
                            Log.d("...", "0");
                            P2PHandler.getInstance().setDefenceAreaState(deviceId,devicePwd,lv1.lines,lv1.few,1);
                            P2PHandler.getInstance().getDefenceArea(deviceId, devicePwd);
                        }
                    }
                });
                break;
        }
    }
}
