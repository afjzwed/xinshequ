package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.DoorInfo;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MenJinListContract;
import com.yxld.yxchuangxin.ui.activity.wuye.MenJinListFragment;

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
 * @Description: presenter of MenJinListFragment
 * @date 2018/05/26 13:35:55
 */
public class MenJinListPresenter implements MenJinListContract.MenJinListContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final MenJinListContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private MenJinListFragment mFragment;

    @Inject
    public MenJinListPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull MenJinListContract.View view,
                               MenJinListFragment fragment) {
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
    public void getDoorList() {
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
        httpAPIWrapper.getDoorList(map).subscribe(new Consumer<DoorInfo>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull DoorInfo baseEntity) throws Exception {
                mView.setDoorList(baseEntity);
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
//                      //ToastUtil.show(mFragment.getActivity(), mFragment.getString(R.string.loading_failed_1));
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