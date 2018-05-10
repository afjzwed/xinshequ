package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.AppYezhuFangwu;
import com.yxld.yxchuangxin.ui.activity.wuye.LiveMemberActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.LiveMemberContract;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of LiveMemberActivity
 * @date 2017/06/07
 */
public class LiveMemberPresenter implements LiveMemberContract.LiveMemberContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private LiveMemberContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private LiveMemberActivity mActivity;
    private AppYezhuFangwu yezhuFangwu;

    @Inject
    public LiveMemberPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull LiveMemberContract.View view, LiveMemberActivity activity) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        mActivity = activity;
    }
    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        if (!mCompositeDisposable.isDisposed()) {
             mCompositeDisposable.dispose();
        }
        mView = null;
    }

    @Override
    public void getAllLiveMember(Map map) {
        Disposable disposable = httpAPIWrapper.getAllLiveMember(map)
                .subscribe(new Consumer<AppYezhuFangwu>() {
                    @Override
                    public void accept(AppYezhuFangwu data) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.setMember(data);
                        yezhuFangwu = data;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //onError
                        KLog.i("onError");
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
    public void deletLiveMember(int position) {
        mView.showProgressDialog();
        //?yezhuId=%1$s&fwyzFw=%2$s&fwyzId=%3$s&uuid=%4$s
        Map<String, String> map = new HashMap<>();
         map.put("yezhuId", yezhuFangwu.getRows().get(position).getYezhuId() + "");
         map.put("fwyzFw", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwId()+ "");
         map.put("fwyzId", Contains.user.getYezhuId() + "");
         map.put("uuid", Contains.uuid);
        KLog.i(map);
        Disposable disposable = httpAPIWrapper.deletLiveMember(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity entity) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        ToastUtil.show(mActivity, entity.getMSG());
                        if (entity.getStatus() == -1) {
                            mView.deletMember(-1);
                        } else {
                            mView.deletMember(position);
                        }
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
}