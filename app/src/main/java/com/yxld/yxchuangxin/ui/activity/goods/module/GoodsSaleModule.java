package com.yxld.yxchuangxin.ui.activity.goods.module;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.GoodsSaleActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.GoodsSaleContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.GoodsSalePresenter;
import com.yxld.yxchuangxin.ui.activity.index.util.Bimp;
import com.yxld.yxchuangxin.ui.activity.index.util.ImageItem;
import com.yxld.yxchuangxin.ui.activity.wuye.FixActivity;
import com.yxld.yxchuangxin.ui.adapter.wuye.GridAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of GoodsSaleActivity, provide field for GoodsSaleActivity
 * @date 2017/10/23 10:36:05
 */
@Module
public class GoodsSaleModule {
    private final GoodsSaleContract.View mView;


    public GoodsSaleModule(GoodsSaleContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public GoodsSalePresenter provideGoodsSalePresenter(HttpAPIWrapper httpAPIWrapper, GoodsSaleActivity mActivity) {
        return new GoodsSalePresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public GoodsSaleActivity provideGoodsSaleActivity() {
        return (GoodsSaleActivity) mView;
    }

    @Provides
    @ActivityScope
    public ArrayList<ImageItem> provideList(GoodsSaleActivity activity) {
        if (Bimp.tempShouhouSelectBitmap.size() == 0) {
            for (int i = 0; i < 3; i++) {
                ImageItem item = new ImageItem();
                BitmapDrawable bd = (BitmapDrawable) activity.getResources().getDrawable(R.mipmap.icon_addpic_unfocused);
                Bitmap bitmap = bd.getBitmap();
                item.setBitmap(bitmap);
                Bimp.tempShouhouSelectBitmap.add(i, item);
            }
        }
        return Bimp.tempShouhouSelectBitmap;
    }

    @Provides
    @ActivityScope
    public GridAdapter provideGridAdapter(ArrayList<ImageItem> list) {
        return new GridAdapter(list);
    }

}