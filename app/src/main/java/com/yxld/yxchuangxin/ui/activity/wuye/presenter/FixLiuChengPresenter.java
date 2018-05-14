package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.LiuCheng;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.FixLiuChengContract;
import com.yxld.yxchuangxin.ui.activity.wuye.FixLiuChengActivity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of FixLiuChengActivity
 * @date 2018/05/14 10:23:41
 */
public class FixLiuChengPresenter implements FixLiuChengContract.FixLiuChengContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final FixLiuChengContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private FixLiuChengActivity mActivity;

    @Inject
    public FixLiuChengPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull FixLiuChengContract.View view,
                                FixLiuChengActivity activity) {
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
    public void getData(Map<String, String> map) {
// TODO: 2018/5/11 维修流程
        Disposable disposable1 = httpAPIWrapper.getWeiXiuLiucheng(map).subscribe(new Consumer<LiuCheng>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull LiuCheng liuCheng) throws Exception {
                KLog.e(liuCheng.toString());
                // mView.saveAdInfo(baseEntity);
                mView.setData(liuCheng);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                KLog.e("==================" + throwable.toString());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        });
        mCompositeDisposable.add(disposable1);

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