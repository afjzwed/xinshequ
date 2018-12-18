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
import com.yxld.yxchuangxin.ui.activity.wuye.NewComplainActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.WuyeTousuFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.NewComplainContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.NewComplainPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.GridAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of NewComplainActivity, provide field for NewComplainActivity
 * @date 2018/12/14 10:49:02
 */
@Module
public class NewComplainModule {
    private final NewComplainContract.View mView;


    public NewComplainModule(NewComplainContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public NewComplainPresenter provideNewComplainPresenter(HttpAPIWrapper httpAPIWrapper, NewComplainActivity mActivity) {
        return new NewComplainPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public NewComplainActivity provideNewComplainActivity() {
        return (NewComplainActivity) mView;
    }

    @Provides
    @ActivityScope
    public GridLayoutManager provideGridLayoutManager(NewComplainActivity activity) {
        return new GridLayoutManager(activity, 3);
    }

    @Provides
    @ActivityScope
    public GridAdapter provideGridAdapter(ArrayList<ImageItem> list) {
        return new GridAdapter(list);
    }

    @Provides
    @ActivityScope
    public ArrayList<ImageItem> provideList(NewComplainActivity activity) {
        if (Bimp.tempTousuSelectBitmap.size() == 0) {
            for (int i = 0; i < 3; i++) {
                ImageItem item = new ImageItem();
                BitmapDrawable bd = (BitmapDrawable) activity.getResources().getDrawable(R.mipmap.icon_addpic_unfocused);
                Bitmap bitmap = bd.getBitmap();
                item.setBitmap(bitmap);
                Bimp.tempTousuSelectBitmap.add(i, item);
            }
        }
        return Bimp.tempTousuSelectBitmap;
    }
}