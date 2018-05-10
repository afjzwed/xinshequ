package com.yxld.yxchuangxin.ui.activity.rim.presenter;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyBusinessInfo;
import com.yxld.yxchuangxin.entity.CxwyClassifyInfo;
import com.yxld.yxchuangxin.entity.CxwyProductInfo;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimGoodListContract;
import com.yxld.yxchuangxin.ui.activity.rim.RimGoodListFragment;

import java.util.Map;

import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: presenter of RimGoodListFragment
 * @date 2017/12/13 10:44:31
 */
public class RimGoodListPresenter implements RimGoodListContract.RimGoodListContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final RimGoodListContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private RimGoodListFragment mFragment;

    @Inject
    public RimGoodListPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull RimGoodListContract.View view, RimGoodListFragment fragment) {
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
    public void getClassifyinfo(Map map) {
        Disposable disposable = httpAPIWrapper.getClassifyinfo(map)
                .subscribe(new Consumer<CxwyClassifyInfo>() {
                    @Override
                    public void accept(CxwyClassifyInfo info) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.onGetClassify(info);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //onError
                        KLog.i("onError");
                        throwable.printStackTrace();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        //onComplete
                        KLog.i("onComplete");
                    }
                });
        mCompositeDisposable.add(disposable);
    }
    @Override
    public void getProductinfo(Map map) {
//        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getProductinfo(map)
                .subscribe(new Consumer<CxwyProductInfo>() {
                    @Override
                    public void accept(CxwyProductInfo info) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.ongetProductinfo(info);
//                        mView.closeProgressDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //onError
                        KLog.i("onError");
                        throwable.printStackTrace();
//                        mView.closeProgressDialog();
//                        ToastUtil.show(mFragment.getActivity(), mFragment.getString(R.string.loading_failed_1));
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        //onComplete
                        KLog.i("onComplete");
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}