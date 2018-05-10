package com.yxld.yxchuangxin.ui.activity.camera.presenter;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.HostEntiti;
import com.yxld.yxchuangxin.ui.activity.camera.AlarmListFragment;
import com.yxld.yxchuangxin.ui.activity.camera.contract.AlarmListContract;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: presenter of AlarmListFragment
 * @date 2017/09/04 15:09:38
 */
public class AlarmListPresenter implements AlarmListContract.AlarmListContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final AlarmListContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private AlarmListFragment mFragment;

    @Inject
    public AlarmListPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull AlarmListContract.View view, AlarmListFragment fragment) {
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
    public void getHost() {
        KLog.i("获取列表");
        mView.showProgressDialog();
        Map map = new HashMap();
        map.put("xmId", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwLoupanId());
        map.put("loudong", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwLoudong());
        map.put("danyuan", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwDanyuan());
        map.put("fanghao", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwFanghao());
        map.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.searchAllHost(map)
                .subscribe(new Consumer<HostEntiti>() {
                    @Override
                    public void accept(HostEntiti entity) throws Exception {
                        //isSuccesse
                        mView.closeProgressDialog();
                        KLog.i("onSuccesse");
                        mView.setAdapter(entity.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        KLog.i("onError");
                        mView.closeProgressDialog();
                        throwable.printStackTrace();
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
    public void buCheFang(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.buCheFang(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity entity) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mHandler.sendEmptyMessageDelayed(0, 2000);
                        ToastUtil.displayShortToast(entity.getMSG());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        KLog.i("onError");
                        throwable.printStackTrace();
                        mView.closeProgressDialog();
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

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    getHost();
                    break;
                case 1:
                    getHost();
                    break;
            }
        }
    };

    @Override
    public void mingDi(Map map) {
        Disposable disposable = httpAPIWrapper.mingDi(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity entity) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        ToastUtil.displayShortToast(entity.getMSG());
                        mHandler.sendEmptyMessageDelayed(1, 2000);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        KLog.i("onError");
                        throwable.printStackTrace();
                        mView.closeProgressDialog();
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