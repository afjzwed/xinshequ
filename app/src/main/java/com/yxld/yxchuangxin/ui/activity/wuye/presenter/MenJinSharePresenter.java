package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.DoorInfo;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MenJinShareContract;
import com.yxld.yxchuangxin.ui.activity.wuye.MenJinShareFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of MenJinShareFragment
 * @date 2018/05/26 13:36:35
 */
public class MenJinSharePresenter implements MenJinShareContract.MenJinShareContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final MenJinShareContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private MenJinShareFragment mFragment;

    @Inject
    public MenJinSharePresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull MenJinShareContract.View view,
                                MenJinShareFragment fragment) {
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) { //请求码为1
            List<String> nameAndNum = UIUtils.getPhoneAndName(data, mFragment.getContext());
            if (nameAndNum.size() != 2) {
                ToastUtil.show(mFragment.getContext(), "获取联系人失败");
            } else {
                mView.setOnResult(nameAndNum.get(0), nameAndNum.get(1));
            }

        }
    }

    @Override
    public void getDoorMima(Map<String, String> map) {
        Disposable disposable = httpAPIWrapper.getDoorMima(map).subscribe(new Consumer<DoorInfo>() {
            @Override
            public void accept(DoorInfo user) throws Exception {
                //isSuccesse
                mView.setDoorMima(user);
                //mView.closeProgressDialog();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //onError
                throwable.printStackTrace();
                //mView.closeProgressDialog();
                //ToastUtil.show(mFragment.getActivity(), mFragment.getString(R.string.loading_failed_1));
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
}