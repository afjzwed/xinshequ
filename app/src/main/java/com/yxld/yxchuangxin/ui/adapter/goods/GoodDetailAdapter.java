package com.yxld.yxchuangxin.ui.adapter.goods;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.view.AutoCardView;
import com.yxld.yxchuangxin.view.CardAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：hu on 2017/6/16
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class GoodDetailAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private float mBaseElevation;
    private Activity mActivity;
    private List<String> urlList;
    public GoodDetailAdapter(Activity mActivity, List<String> list) {
        this.mActivity = mActivity;
        urlList = list;
        mViews = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            mViews.add(null);
        }
    }

    public void addCardItem() {
//        mViews.add(null);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        KLog.i(mViews.size());
        return mViews.get(position);
//        return nearDriver.getData().get(position);
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_goods_detail, container, false);
        ((ViewPager)container).addView(view);
        AutoCardView cardView = (AutoCardView) view.findViewById(R.id.card_View);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_good);
        String url = API.PIC+ StringUitl.replaceEndFenHao(urlList.get(position));
        KLog.e(url);
        Glide.with(mActivity)
                .load(R.mipmap.fwcz_01)
                .into(imageView);
        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }
        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }
}
