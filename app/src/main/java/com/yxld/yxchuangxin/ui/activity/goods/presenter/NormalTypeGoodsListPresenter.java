package com.yxld.yxchuangxin.ui.activity.goods.presenter;

import android.support.annotation.NonNull;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.goods.contract.NormalTypeGoodsListContract;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: presenter of NormalTypeGoodsListActivity
 * @date 2017/06/16
 */
public class NormalTypeGoodsListPresenter implements NormalTypeGoodsListContract.NormalTypeGoodsListContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final NormalTypeGoodsListContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public NormalTypeGoodsListPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull NormalTypeGoodsListContract.View view) {
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