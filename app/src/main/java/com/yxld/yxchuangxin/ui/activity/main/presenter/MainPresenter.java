package com.yxld.yxchuangxin.ui.activity.main.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyMallPezhi;
import com.yxld.yxchuangxin.entity.GoodsKind;
import com.yxld.yxchuangxin.entity.MallClassify;
import com.yxld.yxchuangxin.entity.ShopList;
import com.yxld.yxchuangxin.ui.activity.main.contract.MainContract;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by hu on 2017/5/16.
 */

public class MainPresenter implements MainContract.MainPresenter {
    private final MainContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    private CxwyMallPezhi mPezhi;
    private ShopList mShopList;
    private String msg;

    private GoodsKind mGoodsKind;//商城的查询新品、热销商品、店长推荐商品数据，只取新品数据
    private MallClassify mMallClassify;

    //防止有冒失鬼忘记实现XXXContract.View中的setPresenter(XXXContract.Presenter presenter)了
    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    HttpAPIWrapper httpAPIWrapper;

    @Inject
    public MainPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull MainContract.View view) {
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
    public void getBanner(Map data) {
        Disposable disposable = httpAPIWrapper.getBanner(data)
                .subscribe(new Consumer<CxwyMallPezhi>() {
                    @Override
                    public void accept(CxwyMallPezhi pezhi) throws Exception {
                        //这里接收数据项
                        KLog.i("成功的回调");
                        mPezhi = pezhi;
                        mView.setBanner(pezhi);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //这里接收onError
                        KLog.i("onError的回调");
                        mView.onRefreshFailure();
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
        Disposable disposable = httpAPIWrapper.getAction(data)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity entity) throws Exception {
                        //这里接收数据项
                        KLog.i("成功的回调");
                        msg = entity.getMSG();
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
        Disposable disposable = httpAPIWrapper.getGoodsKinds(params)
                .subscribe(new Consumer<GoodsKind>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull GoodsKind goodsKind)
                            throws Exception {
                        mGoodsKind = goodsKind;
                        mView.setMiaoShaDatas(goodsKind);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable)
                            throws Exception {
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);

       /* Map<String, String> params = new HashMap<String, String>();
        params.put("rows", "6");
        params.put("page", "0");
        params.put("product.shangpinClassicShow", "0");
        params.put("appxiaoqu", Contains.curSelectXiaoQuId + "");
        params.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.getMiaoShaProduct(params)
                .subscribe(new Consumer<ShopList>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull ShopList shopList)
                    throws Exception {
                        mShopList = shopList;
                        mView.setMiaoShaDatas(mShopList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable)
                    throws Exception {
                        KLog.i("onError的回调");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);*/
    }

    @Override
    public void setReStart() {
        if (mPezhi != null) {
            mView.setBanner(mPezhi);
        }
        if (mGoodsKind != null) {
            mView.setMiaoShaDatas(mGoodsKind);
        }
        if (msg != null) {
            mView.setAction(msg);
        }
        if (mMallClassify != null) {
            mView.setFenleiAdapter(mMallClassify);
        }
    }

    @Override
    public void getFenlei() {
        Map<String, String> params = new HashMap<>();
        params.put("uuid", Contains.uuid);
        params.put("fenlei1", "");
        Disposable disposable = httpAPIWrapper.getShangchengFenlei(params)
                .subscribe(new Consumer<MallClassify>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull MallClassify
                                               mallClassify) throws Exception {
                        mMallClassify = mallClassify;
                        mView.setFenleiAdapter(mallClassify);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable)
                            throws Exception {
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
