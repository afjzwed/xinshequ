package com.yxld.yxchuangxin.entity.goods;

import com.yxld.yxchuangxin.base.BaseEntity;

/**
 * @author xlei
 * @Date 2017/10/25.
 */

public class MallNewProduct1 extends BaseEntity {
    private MallNewProduct rows;
    private int totle;

    public MallNewProduct getRows() {
        return rows;
    }

    public void setRows(MallNewProduct rows) {
        this.rows = rows;
    }

    public int getTotle() {
        return totle;
    }

    public void setTotle(int totle) {
        this.totle = totle;
    }
}
