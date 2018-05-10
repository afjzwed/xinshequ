package com.yxld.yxchuangxin.ui.activity.wuye.module;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.index.util.Bimp;
import com.yxld.yxchuangxin.ui.activity.index.util.ImageItem;
import com.yxld.yxchuangxin.ui.activity.wuye.WuyeJianyiFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.WuyeJianyiContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.WuyeJianyiPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.GridAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of WuyeJianyiFragment, provide field for WuyeJianyiFragment
 * @date 2017/06/20 11:11:36
 */
@Module
public class WuyeJianyiModule {
    private final WuyeJianyiContract.View mView;


    public WuyeJianyiModule(WuyeJianyiContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public WuyeJianyiPresenter provideWuyeJianyiPresenter(HttpAPIWrapper httpAPIWrapper, WuyeJianyiFragment mFragment) {
        return new WuyeJianyiPresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public WuyeJianyiFragment provideWuyeJianyiFragment() {
        return (WuyeJianyiFragment) mView;
    }

    @Provides
    @ActivityScope
    public ArrayList<ImageItem> provideList(WuyeJianyiFragment fragment) {
        if (Bimp.tempJianyiSelectBitmap.size() == 0) {
            for (int i = 0; i < 3; i++) {
                ImageItem item = new ImageItem();
                BitmapDrawable bd = (BitmapDrawable) fragment.getResources().getDrawable(R.mipmap.icon_addpic_unfocused);
                Bitmap bitmap = bd.getBitmap();
                item.setBitmap(bitmap);
                Bimp.tempJianyiSelectBitmap.add(i, item);
            }
        }
        return Bimp.tempJianyiSelectBitmap;
    }

    @Provides
    @ActivityScope
    public GridLayoutManager provideGridLayoutManager(WuyeJianyiFragment fragment) {
        return new GridLayoutManager(fragment.getActivity(), 3);
    }

    @Provides
    @ActivityScope
    public GridAdapter provideGridAdapter(ArrayList<ImageItem> list) {
        return new GridAdapter(list);
    }
}