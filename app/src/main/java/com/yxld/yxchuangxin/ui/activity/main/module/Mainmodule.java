package com.yxld.yxchuangxin.ui.activity.main.module;


import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.GoodsKind;
import com.yxld.yxchuangxin.entity.MallClassify;
import com.yxld.yxchuangxin.entity.adapter.Wuye;
import com.yxld.yxchuangxin.entity.test.MainShopSale;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.MainFragment;
import com.yxld.yxchuangxin.ui.activity.main.contract.MainContract;
import com.yxld.yxchuangxin.ui.activity.main.presenter.MainPresenter;
import com.yxld.yxchuangxin.ui.adapter.main.MainMiaoshaAdapter;
import com.yxld.yxchuangxin.ui.adapter.main.MainShopAdapter;
import com.yxld.yxchuangxin.ui.adapter.main.WuyeAdapter1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hu on 2017/5/16.
 */

@Module
public class Mainmodule {
    private final MainContract.View mView;


    public Mainmodule(MainContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MainPresenter provideMainPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new MainPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public MainFragment provideMainActivity() {
        return (MainFragment) mView;
    }

    @Provides
    @ActivityScope
    public List<MainShopSale> provideList() {

        return new ArrayList<>();
    }

    @Provides
    @ActivityScope
    public MainShopAdapter provideMainShopAdapter(List<MallClassify.RowsBean> list) {
        return new MainShopAdapter(list);
    }

    @Provides
    @ActivityScope
    public List<GoodsKind.RowsBean.XinpinListsBean> provideMiaoList() {

        return new ArrayList<>();
    }

    @Provides
    @ActivityScope
    public MainMiaoshaAdapter provideMainMiaoshaAdapter(List<GoodsKind.RowsBean.XinpinListsBean> list) {
        return new MainMiaoshaAdapter(list);
    }

    @Provides
    @ActivityScope
    public WuyeAdapter1 provideWuyeAdapter(List<Wuye.DataBean.ListBean> list) {
        return new WuyeAdapter1(list);
    }

    @Provides
    @ActivityScope
    public List<Wuye.DataBean.ListBean> provideWuyeList() {
        String wuyeString;
        AssetManager assetManager = AppConfig.getInstance().getAssets();
        try {
            InputStream is = assetManager.open("wuye1.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuffer stringBuffer = new StringBuffer();
            wuyeString = null;
            while ((wuyeString = br.readLine()) != null) {
                stringBuffer.append(wuyeString);
            }
            wuyeString = stringBuffer.toString();
            if (wuyeString != null) {
                KLog.i(wuyeString);
                Gson gson = new Gson();
                Wuye wuye = gson.fromJson(wuyeString, Wuye.class);
                return wuye.getData().get(0).getList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
