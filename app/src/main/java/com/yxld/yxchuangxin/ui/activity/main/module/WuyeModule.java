package com.yxld.yxchuangxin.ui.activity.main.module;

import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.adapter.Wuye;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.main.WuyeFragment;
import com.yxld.yxchuangxin.ui.activity.main.contract.WuyeContract;
import com.yxld.yxchuangxin.ui.activity.main.presenter.WuyePresenter;
import com.yxld.yxchuangxin.ui.adapter.main.WuyeAdapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: The moduele of WuyeActivity, provide field for WuyeActivity
 * @date 2017/06/05
 */
@Module
public class WuyeModule {
    private final WuyeContract.View mView;


    public WuyeModule(WuyeContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public WuyePresenter provideWuyePresenter(HttpAPIWrapper httpAPIWrapper) {
        return new WuyePresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public WuyeFragment provideWuyeActivity() {
        return (WuyeFragment) mView;
    }

    @Provides
    @ActivityScope
    public List<Wuye.DataBean> provideList() {
        String wuyeString;
        AssetManager assetManager = AppConfig.getInstance().getAssets();
        try {
            InputStream is = assetManager.open("wuye.json");
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
                return wuye.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Provides
    @ActivityScope
    public WuyeAdapter provideWuyeAdapter(List<Wuye.DataBean> dataBean, HttpAPIWrapper httpAPIWrapper) {
        return new WuyeAdapter(dataBean, httpAPIWrapper);
    }
}