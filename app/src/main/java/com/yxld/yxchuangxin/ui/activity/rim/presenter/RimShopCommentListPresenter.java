package com.yxld.yxchuangxin.ui.activity.rim.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.RimCommentListBean;
import com.yxld.yxchuangxin.ui.activity.rim.RimShopCommentListActivity;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimShopCommentListContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author RimShopCommentList
 * @Package com.yxld.yxchuangxin.ui.activity.rimshopcommentlist
 * @Description: presenter of RimShopCommentListActivity
 * @date 2017/12/18 13:46:52
 */
public class RimShopCommentListPresenter implements RimShopCommentListContract.RimShopCommentListContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final RimShopCommentListContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private RimShopCommentListActivity mActivity;

    @Inject
    public RimShopCommentListPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull RimShopCommentListContract.View view, RimShopCommentListActivity activity) {
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
    public void getRimShopCommentList(Map map, boolean isRefresh) {
        Disposable disposable = httpAPIWrapper.getRimShopCommentList(map)
                .subscribe(new Consumer<RimCommentListBean>() {
                    @Override
                    public void accept(RimCommentListBean data) throws Exception {
                        //这里接收数据项
                        KLog.i("成功的回调" + data.toString());
//                        if (order == null || order.getTotal() == 0) {
//                            mView.setError();
//                        }
//                        mView.setData(data);
                        if (data.getSuccess().equals("1")) {
                            mView.setData(isRefresh, data);
                        } else {
                            mView.setEmptyData(data);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //这里接收onError
                        KLog.i("onError的回调");
                        mView.setError();
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