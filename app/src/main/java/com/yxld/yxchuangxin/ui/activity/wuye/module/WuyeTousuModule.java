package com.yxld.yxchuangxin.ui.activity.wuye.module;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.index.util.Bimp;
import com.yxld.yxchuangxin.ui.activity.index.util.ImageItem;
import com.yxld.yxchuangxin.ui.activity.wuye.WuyeTousuFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.WuyeTousuContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.WuyeTousuPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.GridAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of WuyeTousuFragment, provide field for WuyeTousuFragment
 * @date 2017/06/20 11:11:21
 */
@Module
public class WuyeTousuModule {
    private final WuyeTousuContract.View mView;


    public WuyeTousuModule(WuyeTousuContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public WuyeTousuPresenter provideWuyeTousuPresenter(HttpAPIWrapper httpAPIWrapper, WuyeTousuFragment mFragment) {
        return new WuyeTousuPresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public WuyeTousuFragment provideWuyeTousuFragment() {
        return (WuyeTousuFragment) mView;
    }

    @Provides
    @ActivityScope
    public ArrayList<ImageItem> provideList(WuyeTousuFragment fragment) {
        if (Bimp.tempTousuSelectBitmap.size() == 0) {
            for (int i = 0; i < 3; i++) {
                ImageItem item = new ImageItem();
                BitmapDrawable bd = (BitmapDrawable) fragment.getResources().getDrawable(R.mipmap.icon_addpic_unfocused);
                Bitmap bitmap = bd.getBitmap();
                item.setBitmap(bitmap);
                Bimp.tempTousuSelectBitmap.add(i, item);
            }
        }
        return Bimp.tempTousuSelectBitmap;
    }

    @Provides
    @ActivityScope
    public GridLayoutManager provideGridLayoutManager(WuyeTousuFragment fragment) {
        return new GridLayoutManager(fragment.getActivity(), 3);
    }

    @Provides
    @ActivityScope
    public GridAdapter provideGridAdapter(ArrayList<ImageItem> list) {
        return new GridAdapter(list);
    }

}