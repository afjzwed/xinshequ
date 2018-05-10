package com.yxld.yxchuangxin.ui.activity.goods.presenter;
import android.support.annotation.NonNull;

import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.goods.MallOrderSuggest;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MarketComplainListContract;
import com.yxld.yxchuangxin.ui.activity.goods.MarketComplainListActivity;

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
 * @Description: presenter of MarketComplainListActivity
 * @date 2017/11/02 14:28:33
 */
public class MarketComplainListPresenter implements MarketComplainListContract.MarketComplainListContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final MarketComplainListContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private MarketComplainListActivity mActivity;

    @Inject
    public MarketComplainListPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull MarketComplainListContract.View view, MarketComplainListActivity activity) {
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
    public void getSuggestListFromSever() {
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getMallSuggestList(map).subscribe(new Consumer<MallOrderSuggest>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull MallOrderSuggest mallOrderSuggest) throws Exception {
                mView.loadSuggestListSuccess(mallOrderSuggest);
                mView.closeProgressDialog();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                mView.closeProgressDialog();
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