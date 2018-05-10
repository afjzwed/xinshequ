package com.yxld.yxchuangxin.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.yxld.yxchuangxin.ui.adapter.camera.LearnAdapter;

/**
 * 作者：yishangfei on 2017/1/20 0020 14:55
 * 邮箱：yishangfei@foxmail.com
 */
public class Level1Item implements MultiItemEntity {
    public int title;
    public int lines;
    public int few;

    public Level1Item(int title,int lines,int few) {
        this.title = title;
        this.lines = lines;
        this.few = few;
    }


    @Override
    public int getItemType() {
        return LearnAdapter.TYPE_LEVEL_1;
    }
}