package com.yxld.yxchuangxin.ui.activity.goods.presenter;

import android.support.annotation.NonNull;

import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.MallClassify;
import com.yxld.yxchuangxin.ui.activity.goods.contract.GoodsFenLeiContract;
import com.yxld.yxchuangxin.ui.activity.goods.GoodsFenLeiActivity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: presenter of GoodsFenLeiActivity
 * @date 2017/10/19 08:58:40
 */
public class GoodsFenLeiPresenter implements GoodsFenLeiContract.GoodsFenLeiContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final GoodsFenLeiContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private GoodsFenLeiActivity mActivity;

    @Inject
    public GoodsFenLeiPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull GoodsFenLeiContract.View view, GoodsFenLeiActivity activity) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        this.mActivity = activity;
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
    public void getFenLei2(Map map) {
        Disposable disposable = httpAPIWrapper.getShangchengFenlei(map)
                .subscribe(new Consumer<MallClassify>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull MallClassify mallClassify) throws Exception {
                        mView.loadFenLei2Success(mallClassify);
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

//    @Override
//    public void getUser(HashMap map) {
//        //mView.showProgressDialog();
//        Disposable disposable = httpAPIWrapper.getUser(map)
//                .subscribe(new Consumer<User>() {
//                    @Override
//                    public void accept(User user) throws Exception {
//                        //isSuccesse
//                        KLog.i("onSuccesse");
//                        mView.setText(user);
//                      //mView.closeProgressDialog();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        //onError
//                        KLog.i("onError");
//                        throwable.printStackTrace();
//                      //mView.closeProgressDialog();
//                      //ToastUtil.show(mActivity, mActivity.getString(R.string.loading_failed_1));
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