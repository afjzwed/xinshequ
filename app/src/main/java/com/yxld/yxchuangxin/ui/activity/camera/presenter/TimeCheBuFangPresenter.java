package com.yxld.yxchuangxin.ui.activity.camera.presenter;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.BuCheFang;
import com.yxld.yxchuangxin.entity.FangquEntity;
import com.yxld.yxchuangxin.ui.activity.camera.TimeCheBuFangActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.TimeCheBuFangContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: presenter of TimeCheBuFangActivity
 * @date 2017/09/05 17:39:46
 */
public class TimeCheBuFangPresenter implements TimeCheBuFangContract.TimeCheBuFangContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final TimeCheBuFangContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private TimeCheBuFangActivity mActivity;

    @Inject
    public TimeCheBuFangPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull TimeCheBuFangContract.View view, TimeCheBuFangActivity activity) {
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
    boolean isLa = false;
    @Override
    public void getFangqu(Map map) {
        Disposable disposable = httpAPIWrapper.getFangqu(map)
                .subscribe(new Consumer<FangquEntity>() {
                    @Override
                    public void accept(FangquEntity entity) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        if (entity.getStatus() != 0) {
                            ToastUtil.displayShortToast(entity.getMSG());
                            return;
                        }
                        if (!isLa) {
                            getFangqu(map);
                        }
                        isLa = true;
                        Contains.fangquEntity = entity;
                        mView.setFangqu(Contains.fangquEntity.getData());
//                        mHandler.sendEmptyMessageDelayed(0, 2000);
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
                    mView.setFangqu(Contains.fangquEntity.getData());
                    break;
            }
        }
    };

    @Override
    public void getTimingBufang(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.selectTimingBufang(map)
                .subscribe(new Consumer<BuCheFang>() {
                    @Override
                    public void accept(BuCheFang entity) throws Exception {
                        //isSuccesse
                        mView.closeProgressDialog();
                        KLog.i("onSuccesse");
                        List<BuCheFang.DataBean> list = new ArrayList<BuCheFang.DataBean>();
                        list.add(entity.getData());
                        entity.setList(list);
                        mView.setBuCheFangList(entity);
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

    @Override
    public void cacanlDingshiCheBuFang(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.cacanlDingshiCheBuFang(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity entity) throws Exception {
                        //isSuccesse
                        mView.closeProgressDialog();
                        mView.onCancalBuchefangChenggong();
                        if (entity.getStatus() == 0) {
                            ToastUtil.displayShortToast("设置成功");
                            AppConfig.getInstance().mAppActivityManager.finishActivity(TimeCheBuFangActivity.class);
                        } else {
                            ToastUtil.displayShortToast("设置失败");
                        }
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