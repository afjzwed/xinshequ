package com.yxld.yxchuangxin.ui.activity.camera.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.PaianYezhuJiashu;
import com.yxld.yxchuangxin.entity.PaianYijiaJiashu;
import com.yxld.yxchuangxin.ui.activity.camera.InformPersonActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.InformPersonContract;

import java.util.ArrayList;
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
 * @Description: presenter of InformPersonActivity
 * @date 2017/09/19 11:17:32
 */
public class InformPersonPresenter implements InformPersonContract.InformPersonContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final InformPersonContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private InformPersonActivity mActivity;
    private PaianYezhuJiashu paianYezhuJiashu;

    @Inject
    public InformPersonPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull InformPersonContract.View view, InformPersonActivity activity) {
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
    public void getInformPerson(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.findShare(map)
                .subscribe(new Consumer<PaianYijiaJiashu>() {
                    @Override
                    public void accept(PaianYijiaJiashu entity) throws Exception {
                        //isSuccesse
                        perJiajiaJiashu(entity);
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

    private void perJiajiaJiashu(PaianYijiaJiashu entity) {
        ArrayList<String> personList = new ArrayList<>();
        for (int i = 0; i < entity.getData().size(); i++) {
            for (int j = 0; j < paianYezhuJiashu.getData().size(); j++) {
                if (paianYezhuJiashu.getData().get(j).getYezhuShouji().equals(entity.getData().get(i).getFxHaoma())) {
                    personList.add(paianYezhuJiashu.getData().get(j).getYezhuName() + "    " + paianYezhuJiashu.getData().get(j).getYezhuShouji());
                    break;
                }
            }
        }
        mView.closeProgressDialog();
        mView.setYijiaJiashu(personList);
    }

    @Override
    public void findYezhuJiashu(Map map, String mac) {
        Disposable disposable = httpAPIWrapper.findYezhuJiashu(map)
                .subscribe(new Consumer<PaianYezhuJiashu>() {
                    @Override
                    public void accept(PaianYezhuJiashu entity) throws Exception {
                        //isSuccesse
                        paianYezhuJiashu = entity;
                        mView.setPerson(entity);
                        Map<String, String> map = new HashMap<>();
                        map.put("uuid", Contains.uuid);
                        map.put("mac", mac);
                        getInformPerson(map);
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
    public void saveNumber(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.saveShare(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity entity) throws Exception {
                        mView.closeProgressDialog();
                        ToastUtil.displayShortToast(entity.getMSG());
                        AppConfig.getInstance().mAppActivityManager.finishActivity(InformPersonActivity.class);
                        //isSuccesse
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