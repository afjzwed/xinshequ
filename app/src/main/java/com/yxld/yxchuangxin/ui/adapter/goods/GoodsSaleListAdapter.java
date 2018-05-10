package com.yxld.yxchuangxin.ui.adapter.goods;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.goods.MallNewOrderDetails;
import com.yxld.yxchuangxin.ui.activity.goods.contract.GoodsSaleContract;

import java.util.ArrayList;
import java.util.List;

import cn.refactor.library.SmoothCheckBox;

/**
 * @author: xlei
 * @date: 2017/10/25
 * @descprition:
 */

public class GoodsSaleListAdapter extends BaseQuickAdapter<MallNewOrderDetails, BaseViewHolder> {

    private List<SmoothCheckBox> mCheckBoxContainer;
    private GoodsSaleContract.View mView;

    public GoodsSaleListAdapter(GoodsSaleContract.View view, List<MallNewOrderDetails> data) {
        super(R.layout.item_goods_sale, data);
        mView = view;
        mCheckBoxContainer = new ArrayList<>();

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MallNewOrderDetails dataBean) {
        baseViewHolder
                .setText(R.id.tv_cart_product_price1, "¥ " + dataBean.getShangpinShoujia() /* Double.parseDouble(dataBean.getCartNum())*/)
                .setText(R.id.tv_cart_product_title, dataBean.getShangpinMing())
                .setText(R.id.tv_shuliang, "× " + String.valueOf(dataBean.getShangpinShuliang()));
        ImageView iv = baseViewHolder.getView(R.id.iv_cart_product);
        Uri uri = Uri.parse(API.PIC + StringUitl.replaceEndFenHao(dataBean.getSuoluetu()));
        Glide.with(mContext)
                .load(uri)
                .into(iv);

        handlerCheckBox(baseViewHolder, dataBean);
    }

    /**
     * 与checkBox有关的
     */
    private void handlerCheckBox(BaseViewHolder baseViewHolder, MallNewOrderDetails dataBean) {
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
                    }

                } else {
                    //为了触发全选按钮
                    mView.onOneCheckBoxNotChecked();
                }
            }
        });
    }


    public void setAllChecked(boolean allChecked) {
        List<MallNewOrderDetails> datas = getData();
        for (MallNewOrderDetails dataBean : datas) {
            dataBean.setChecked(allChecked);
        }

        for (int i = 0; i < mCheckBoxContainer.size(); i++) {
            SmoothCheckBox box = mCheckBoxContainer.get(i);
            box.setChecked(allChecked, true);
        }
        mView.onAllChecked(allChecked);
    }

    private boolean isAllChecked() {
        List<MallNewOrderDetails> datas = getData();
        for (MallNewOrderDetails bean : datas) {
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
