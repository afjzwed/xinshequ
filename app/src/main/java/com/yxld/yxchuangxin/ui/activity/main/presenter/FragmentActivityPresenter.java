package com.yxld.yxchuangxin.ui.activity.main.presenter;

import android.support.annotation.NonNull;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.main.contract.FragmentActivityContract;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author hu
 * @Package com.example.hu.myapplication.ui.activity.main
 * @Description: presenter of FragmentActivityActivity
 * @date 2017/05/18
 */
public class FragmentActivityPresenter implements FragmentActivityContract.FragmentActivityContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final FragmentActivityContract.View mView;
    private CompositeDisposable mSubscriptions;

    @Inject
    public FragmentActivityPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull FragmentActivityContract.View view) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mSubscriptions = new CompositeDisposable();
    }
    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscriptions.dispose();
    }


    //   The templet for net request method
    //    @Override
    //    public void getList(LinkedHashMap<String, String> data) {
    //        Subscription rxSubscription = httpAPIWrapper.getList(data)
    //                .subscribeOn(Schedulers.io())
    //                .observeOn(AndroidSchedulers.mainThread())
    //                .subscribe(new Observer<ActivityOrder>() {
    //                    @Override
    //                    public void onCompleted() {
    //                    }
    //
    //                    @Override
    //                    public void onError(Throwable e) {
    //                        KLog.e(e.toString());
    //                    }
    //
    //                    @Override
    //                    public void onNext(ActivityOrder activityOrder) {
    //                        mView.setData(activityOrder);
    //                    }
    //                });
    //        mSubscriptions.add(rxSubscription);
    //    }
}