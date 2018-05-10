package com.yxld.yxchuangxin.ui.activity.wuye.module;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.index.util.Bimp;
import com.yxld.yxchuangxin.ui.activity.index.util.ImageItem;
import com.yxld.yxchuangxin.ui.activity.wuye.FixActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.FixContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.FixPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.GridAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of FixActivity, provide field for FixActivity
 * @date 2017/06/15
 */
@Module
public class FixModule {
    private final FixContract.View mView;


    public FixModule(FixContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FixPresenter provideFixPresenter(HttpAPIWrapper httpAPIWrapper, FixActivity activity) {
        return new FixPresenter(httpAPIWrapper, mView, activity);
    }

    @Provides
    @ActivityScope
    public ArrayList<ImageItem> provideList(FixActivity activity) {
        if (Bimp.tempSelectBitmap.size() == 0) {
            for (int i = 0; i < 3; i++) {
                ImageItem item = new ImageItem();
                BitmapDrawable bd = (BitmapDrawable) activity.getResources().getDrawable(R.mipmap.icon_addpic_unfocused);
                Bitmap bitmap = bd.getBitmap();
                item.setBitmap(bitmap);
                Bimp.tempSelectBitmap.add(i, item);
            }
        }
        return Bimp.tempSelectBitmap;
    }

    @Provides
    @ActivityScope
    public GridLayoutManager provideGridLayoutManager(FixActivity activity) {
        return new GridLayoutManager(activity, 3);
    }

    @Provides
    @ActivityScope
    public GridAdapter provideGridAdapter(ArrayList<ImageItem> list) {
        return new GridAdapter(list);
    }

    @Provides
    @ActivityScope
    public FixActivity provideFixActivity() {
        return (FixActivity) mView;
    }
}