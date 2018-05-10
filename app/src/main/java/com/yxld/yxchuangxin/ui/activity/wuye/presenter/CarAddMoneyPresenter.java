package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.BaseBack;
import com.yxld.yxchuangxin.ui.activity.wuye.CarAddMoneyActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.CarAddMoneyContract;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of CarAddMoneyActivity
 * @date 2017/07/06 15:05:41
 */
public class CarAddMoneyPresenter implements CarAddMoneyContract.CarAddMoneyContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private CarAddMoneyContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private CarAddMoneyActivity mActivity;

    @Inject
    public CarAddMoneyPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull CarAddMoneyContract.View view, CarAddMoneyActivity activity) {
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
        mView = null;
    }

    @Override
    public void affordMoney(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.carAffordMoney(map)
                .subscribe(new Consumer<BaseBack>() {
                    @Override
                    public void accept(BaseBack back) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
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

    //输入月份和时间戳, 返回年月日
    @Override
    public long caculateTime(int month, String s) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cd = Calendar.getInstance();
//            System.out.printf(cd.getTimeInMillis() + "");
//            cd.setTime(sdf.parse(s));
            cd.setTimeInMillis(Long.parseLong(s));
            System.out.printf("转换前的时间为：" + sdf.format(cd.getTime()));
            int day1 = cd.get(Calendar.DAY_OF_MONTH);
            cd.add(Calendar.MONTH, month);//增加n个月
            Calendar cd2 = Calendar.getInstance();
            cd2.setTimeInMillis(cd.getTimeInMillis());
            cd2.add(Calendar.DAY_OF_MONTH, 4);
            int day = cd2.get(Calendar.DAY_OF_MONTH);
//            KLog.i(day);
            //最后一天为28
            if (day == 1) {
                if (day1 > 28) {
                    cd.set(Calendar.DAY_OF_MONTH, 28);
                }
//                KLog.i("添加3天");
//                cd.add(Calendar.DAY_OF_MONTH, 3);
            }
            //最后一天为29
            if (day == 2) {
                if (day1 > 29) {
                    cd.set(Calendar.DAY_OF_MONTH, 29);
                }
//                KLog.i("添加2天");
//                cd.add(Calendar.DAY_OF_MONTH, 2);
            }
            //最后一天为30
            if (day == 3) {
                if (day1 > 30) {
                    cd.set(Calendar.DAY_OF_MONTH, 30);
                }
//                KLog.i("添加1天");
//                cd.add(Calendar.DAY_OF_MONTH, 1);
            }
            //最后一天为31
            if (day == 4) {

            }
//            return sdf.format(cd.getTime());
            return cd.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String caculate(int month, String s) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cd = Calendar.getInstance();
            int day1 = cd.get(Calendar.DAY_OF_MONTH);
            cd.setTime(sdf.parse(s));
            cd.add(Calendar.MONTH, month);//增加n个月
            Calendar cd2 = Calendar.getInstance();
            cd2.setTimeInMillis(cd.getTimeInMillis());
            cd2.add(Calendar.DAY_OF_MONTH, 4);
            int day = cd2.get(Calendar.DAY_OF_MONTH);
            KLog.i(day);
            //最后一天为28
            if (day == 1) {
                if (day1 > 28) {
                    cd.set(Calendar.DAY_OF_MONTH, 28);
                }
//                KLog.i("添加3天");
//                cd.add(Calendar.DAY_OF_MONTH, 3);
            }
            //最后一天为29
            if (day == 2) {
                if (day1 > 29) {
                    cd.set(Calendar.DAY_OF_MONTH, 29);
                }
//                KLog.i("添加2天");
//                cd.add(Calendar.DAY_OF_MONTH, 2);
            }
            //最后一天为30
            if (day == 3) {
                if (day1 > 30) {
                    cd.set(Calendar.DAY_OF_MONTH, 30);
                }
//                KLog.i("添加1天");
//                cd.add(Calendar.DAY_OF_MONTH, 1);
            }
            //最后一天为31
            if (day == 4) {

            }
            return sdf.format(cd.getTime());
        } catch (Exception e) {
            return null;
        }
    }

}