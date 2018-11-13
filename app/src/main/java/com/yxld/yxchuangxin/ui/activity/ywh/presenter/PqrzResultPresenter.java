package com.yxld.yxchuangxin.ui.activity.ywh.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.YwhHouse;
import com.yxld.yxchuangxin.ui.activity.ywh.PqrzResultActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.PqrzResultContract;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: presenter of PqrzResultActivity
 * @date 2018/11/09 14:52:55
 */
public class PqrzResultPresenter implements PqrzResultContract.PqrzResultContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final PqrzResultContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private PqrzResultActivity mActivity;

    @Inject
    public PqrzResultPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull PqrzResultContract.View view, PqrzResultActivity activity) {
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
    public void getDetail() {
        mView.showProgressDialog();
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.getSmrzDetail(map)
                .subscribe(new Consumer<YwhHouse>() {
                    @Override
                    public void accept(YwhHouse baseEntity) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        mView.getDetailSuccess(baseEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //onError
                        KLog.i("onError");
                        throwable.printStackTrace();
                        mView.closeProgressDialog();
//                        ToastUtil.show(mActivity, mActivity.getString(R.string.loading_failed_1));
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
    public void getChangeStatus() {
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.getSmrzCChange(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity baseEntity) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        mView.getStatusSuccess(baseEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //onError
                        KLog.i("onError");
                        throwable.printStackTrace();
                        mView.closeProgressDialog();
//                        ToastUtil.show(mActivity, mActivity.getString(R.string.loading_failed_1));
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