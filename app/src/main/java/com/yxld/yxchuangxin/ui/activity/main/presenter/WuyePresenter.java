package com.yxld.yxchuangxin.ui.activity.main.presenter;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.adapter.Wuye;
import com.yxld.yxchuangxin.ui.activity.main.WuyeFragment;
import com.yxld.yxchuangxin.ui.activity.main.contract.WuyeContract;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: presenter of WuyeActivity
 * @date 2017/06/05
 */
public class WuyePresenter implements WuyeContract.WuyeContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final WuyeContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    WuyeFragment wuyeActivity;

    @Inject
    public WuyePresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull WuyeContract.View view) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
    }
    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        if (!mCompositeDisposable.isDisposed()) {
             mCompositeDisposable.dispose();
        }
    }

    @Override
    public void setAdapter() {
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
                mView.setAdapter(wuye.getData());
                KLog.i("开始设置adapter");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void getUser(LinkedHashMap data) {
//        Disposable disposable = httpAPIWrapper.getUser(data)
//                .subscribe(new Consumer<User>() {
//                    @Override
//                    public void accept(User user) throws Exception {
//                        //isSuccesse
//                        KLog.i("onSuccesse");
//                        mView.setText(user);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        //onError
//                        KLog.i("onError");
//                    }
//                }, new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        //onComplete
//                        KLog.i("onComplete");
//                    }
//                });
//        mCompositeDisposable.add(disposable);
//    }
}