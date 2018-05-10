package com.yxld.yxchuangxin.entity;


import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.yxld.yxchuangxin.ui.adapter.camera.LearnAdapter;

/**
 * 作者：yishangfei on 2017/1/20 0020 10:38
 * 邮箱：yishangfei@foxmail.com
 */
public class Level0Item extends AbstractExpandableItem<Level1Item> implements MultiItemEntity {
    public int title;

    public Level0Item(int title) {
        this.title = title;
    }

    @Override
    public int getItemType() {
        return LearnAdapter.TYPE_LEVEL_0;
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
