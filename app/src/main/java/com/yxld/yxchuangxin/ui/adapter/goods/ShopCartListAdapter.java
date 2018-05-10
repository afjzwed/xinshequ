package com.yxld.yxchuangxin.ui.adapter.goods;

import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.goods.ShopCart;
import com.yxld.yxchuangxin.ui.activity.goods.contract.ShopCartContract;

import java.util.ArrayList;
import java.util.List;

import cn.refactor.library.SmoothCheckBox;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/17
 * @descprition:
 */

public class ShopCartListAdapter extends BaseQuickAdapter<ShopCart.ShapCartBean, BaseViewHolder> {

    private List<SmoothCheckBox> mCheckBoxContainer;
    private ShopCartContract.View mView;

    public ShopCartListAdapter(@Nullable ShopCartContract.View view, ArrayList<ShopCart.ShapCartBean> data) {
        super(R.layout.item_shop_cart, data);
        mView = view;
        mCheckBoxContainer = new ArrayList<>();

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ShopCart.ShapCartBean dataBean) {
        baseViewHolder.setText(R.id.tv_cart_product_price, "¥ " + StringUitl.get2xiaoshu(dataBean.getCartSpdanjia()) + "")
                .setText(R.id.tv_cart_product_title, dataBean.getCartSpmingcheng())
                .setTag(R.id.cv_item, "Detail-Title")
                .addOnClickListener(R.id.cv_item)
                .setText(R.id.tv_cart_count, String.valueOf(dataBean.getCartNum()))
                .setTag(R.id.iv_cart_jian, "Minus")
                .addOnClickListener(R.id.iv_cart_jian)
                .setTag(R.id.iv_cart_jia, "Plus")
                .addOnClickListener(R.id.iv_cart_jia);

        ImageView iv = baseViewHolder.getView(R.id.iv_cart_product);
        if (dataBean.getCartSpzhutu() != null && !"".equals(dataBean.getCartSpzhutu())) {
            Uri uri = Uri.parse(API.PIC + StringUitl.replaceEndFenHao(dataBean.getCartSpzhutu().split(",")[0]));
            Glide.with(mContext)
                    .load(uri)
                    .into(iv);
        }
        if (dataBean.getCartIsShangjia()==-1){
            baseViewHolder.setBackgroundColor(R.id.cv_item, Color.rgb(666, 666, 666));
        }else {
            baseViewHolder.setBackgroundColor(R.id.cv_item, Color.rgb(255, 255, 255));
        }
        handlerCheckBox(baseViewHolder, dataBean);
    }

    /**
     * 与checkBox有关的
     */
    private void handlerCheckBox(BaseViewHolder baseViewHolder, ShopCart.ShapCartBean dataBean) {
        SmoothCheckBox checkBox = baseViewHolder.getView(R.id.check_cart_single_product);

        if (!mCheckBoxContainer.contains(checkBox)) {
            mCheckBoxContainer.add(checkBox);
        }
        checkBox.setChecked(dataBean.isChecked());
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox.setChecked(!checkBox.isChecked(), true);
                dataBean.setChecked(checkBox.isChecked());
                if (dataBean.isChecked()) {
                    //查看是否全选
                    if (isAllChecked()) {
                        //为了触发全选按钮
                        mView.onAllChecked(true);
                    } else {
                        mView.onItemChecked();
                    }

                } else {
                    mView.onItemCancelChecked();
                    //为了触发全选按钮
                    mView.onOneCheckBoxNotChecked();
                }
            }
        });
    }


    public void setAllChecked(boolean allChecked) {
        List<ShopCart.ShapCartBean> datas = getData();
        for (ShopCart.ShapCartBean dataBean : datas) {
            dataBean.setChecked(allChecked);
        }

        for (int i = 0; i < mCheckBoxContainer.size(); i++) {
            SmoothCheckBox box = mCheckBoxContainer.get(i);
            box.setChecked(allChecked, true);
        }
        mView.onAllChecked(allChecked);
    }

    private boolean isAllChecked() {
        List<ShopCart.ShapCartBean> datas = getData();
        for (ShopCart.ShapCartBean bean : datas) {
            if (!bean.isChecked()) {
                return false;
            }
        }
        return true;
    }

    public void notifyItemsRemoved(List<Integer> positions) {
        int index = 0;
        for (int pos : positions) {
            int position = pos - index;
            mData.remove(position);
            notifyItemRemoved(position);
            index++;
        }

        if (mData.size() == 0) {
            mView.onOneCheckBoxNotChecked();

            //Todo 有可能会造成内存泄漏
            mCheckBoxContainer.clear();
        }
    }

}
