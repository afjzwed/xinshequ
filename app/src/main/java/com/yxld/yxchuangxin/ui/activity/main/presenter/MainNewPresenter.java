package com.yxld.yxchuangxin.ui.activity.main.presenter;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyMallPezhi;
import com.yxld.yxchuangxin.entity.GoodsKind;
import com.yxld.yxchuangxin.entity.MallClassify;
import com.yxld.yxchuangxin.ui.activity.main.contract.MainNewContract;
import com.yxld.yxchuangxin.ui.activity.main.MainNewFragment;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: presenter of MainNewFragment
 * @date 2018/11/16 10:36:55
 */
public class MainNewPresenter implements MainNewContract.MainNewContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final MainNewContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private MainNewFragment mFragment;

    @Inject
    public MainNewPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull MainNewContract.View view, MainNewFragment fragment) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        this.mFragment = fragment;
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
    public void getBanner(Map data) {
        Disposable disposable = httpAPIWrapper.getBanner(data).subscribe(new Consumer<CxwyMallPezhi>() {
            @Override
            public void accept(CxwyMallPezhi pezhi) throws Exception {
                //这里接收数据项
                KLog.i("成功的回调");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //这里接收onError
                KLog.i("onError的回调");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                //这里接收onComplete。
                KLog.i("run的回调");
            }
        });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getAction(Map data) {
        Disposable disposable = httpAPIWrapper.getAction(data).subscribe(new Consumer<BaseEntity>() {
            @Override
            public void accept(BaseEntity entity) throws Exception {
                //这里接收数据项
                KLog.i("成功的回调");
                mView.setAction(entity.getMSG());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //这里接收onError
                KLog.i("onError的回调");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                //这里接收onComplete。
                KLog.i("run的回调");
            }
        });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getMianShaData() {
        Map<String, String> params = new HashMap<>();
        params.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.getGoodsKinds(params).subscribe(new Consumer<GoodsKind>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull GoodsKind goodsKind) throws Exception {
                mView.setMiaoShaDatas(goodsKind);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        });
        mCompositeDisposable.add(disposable);


    }
    @Override
    public void getFenlei() {
        Map<String, String> params = new HashMap<>();
        params.put("uuid", Contains.uuid);
        params.put("fenlei1", "");
        Disposable disposable = httpAPIWrapper.getShangchengFenlei(params).subscribe(new Consumer<MallClassify>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull MallClassify mallClassify) throws Exception {
                mView.setFenleiAdapter(mallClassify);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        });
        mCompositeDisposable.add(disposable);
    }
}