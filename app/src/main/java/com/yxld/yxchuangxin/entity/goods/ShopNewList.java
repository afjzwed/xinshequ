package com.yxld.yxchuangxin.entity.goods;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * @author xlei
 * @Date 2017/10/24.
 */

public class ShopNewList extends BaseEntity {
    private int total;
    public List<MallNewProduct> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<MallNewProduct> getRows() {
        return rows;
    }

    public void setRows(List<MallNewProduct> rows) {
        this.rows = rows;
    }


}
