package com.yxld.yxchuangxin.ui.activity.common.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.SpUtil;
import com.yxld.yxchuangxin.Utils.TimeUtil;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.contain.ContainValue;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyCommon;
import com.yxld.yxchuangxin.entity.CxwyCommonToken;
import com.yxld.yxchuangxin.ui.activity.common.AisleActivity;
import com.yxld.yxchuangxin.ui.activity.common.RealPlayActivity;
import com.yxld.yxchuangxin.ui.activity.common.contract.AisleContract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.commom
 * @Description: presenter of AisleActivity
 * @date 2017/06/08
 */
public class AislePresenter implements AisleContract.AisleContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final AisleContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private AisleActivity mActivity;

    @Inject
    public AislePresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull AisleContract.View view, AisleActivity mActivity) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        this.mActivity = mActivity;
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
    public void getCommonToken(Map map) {
        Disposable disposable = httpAPIWrapper.getCommonToken(map)
                .subscribe(new Consumer<CxwyCommonToken>() {
                    @Override
                    public void accept(CxwyCommonToken token) throws Exception {
                        KLog.i("onSuccesse");
                        AppConfig.getOpenSDK().setAccessToken(token.getData().getAccessToken());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //onError
                        KLog.i("onError");
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
    public void getCommon(Map map) {
        Disposable disposable = httpAPIWrapper.getCommonHide(map)
                .subscribe(new Consumer<CxwyCommon>() {
                    @Override
                    public void accept(CxwyCommon common) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        List<CxwyCommon.DataBean.CvoListBean> list = new ArrayList<>();
                        for (int i = 0; i < common.getData().size(); i++) {
                            for (int j = 0; j < common.getData().get(i).getCvoList().size(); j++) {
                                list.add(common.getData().get(i).getCvoList().get(j));
                            }
                        }
                        Contains.cvoListBean = list;
                        setTime();
                        mView.setAdapter(list);
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

    /**
     * 添加视频可以观看的时间和视频观看间隔的时间
     */
    private void setTime() {
//        SpUtil.putInt(mActivity, ContainValue.INTERVALTIME,  Contains.cvoListBean.get(0).getIntervalTime() == 0? 100:Contains.cvoListBean.get(0).getIntervalTime());
//        SpUtil.putInt(mActivity, ContainValue.INTERVALTIME,  600);
//        SpUtil.putInt(mActivity, ContainValue.WATCHTIME,  60);
    }

    @Override
    public void aisleAdapterOnItemClick(int position) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        //利用SharedPreferences 存储通道号和时间
        SharedPreferences preferences = mActivity.getSharedPreferences("device", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        int Channel = Contains.cvoListBean.get(position).getTongdaohao();
        int id = Contains.cvoListBean.get(position).getSxtid();
        if (Calendar.getInstance().getTimeInMillis() / 1000 - SpUtil.getLong(mActivity, "Time" + id, 0) > Contains.cvoListBean.get(position).getIntervalTime()) {
            Intent intent = new Intent(mActivity, RealPlayActivity.class);
            SpUtil.putInt(mActivity, ContainValue.WATCHTIME,  Contains.cvoListBean.get(position).getWatchTime() == 0? 60:Contains.cvoListBean.get(position).getWatchTime());
            intent.putExtra("DeviceSerial", Contains.cvoListBean.get(position).getShebeixuliehao());
            intent.putExtra("Channel", Channel);
            mActivity.startActivity(intent);
            SpUtil.putLong(mActivity, "Time" + id, Calendar.getInstance().getTimeInMillis() / 1000);
        } else {
            ToastUtil.show(mActivity, "请" + TimeUtil.getiDefferTime(Contains.cvoListBean.get(position).getIntervalTime() - (Calendar.getInstance().getTimeInMillis() / 1000 - SpUtil.getLong(mActivity, "Time" + id, 0))) + "后进行再进行连接");
        }
//        if (preferences.getString("Time" + id, df.format(new Date())).equals(df.format(new Date()))) {
//            Intent intent = new Intent(mActivity, RealPlayActivity.class);
//            intent.putExtra("DeviceSerial", Contains.cvoListBean.get(position).getShebeixuliehao());
//            intent.putExtra("Channel", Channel);
//            mActivity.startActivity(intent);
//            editor.putString("Time" + id, df.format(new Date()));
//            editor.commit();
//        } else {
//            //以秒为单位......为过去的时间。。。
//            int time = SpUtil.getInt(mActivity, ContainValue.INTERVALTIME, 600) - getDistanceTimes(preferences.getString("Time" + id, df.format(new Date())), df.format(new Date()));
//            KLog.i(time);
//            if (time <= SpUtil.getInt(mActivity, ContainValue.INTERVALTIME, 600) && time >= 0) {
//                ToastUtil.show(mActivity, "请" + TimeUtil.getiDefferTime(preferences.getString("Time" + id, df.format(new Date()))) + "后进行再进行连接");
//            } else {
//                editor.putString("Time" + id, df.format(new Date()));
//                editor.commit();
//                Intent intent = new Intent(mActivity, RealPlayActivity.class);
//                intent.putExtra("DeviceSerial", Contains.cvoListBean.get(position).getShebeixuliehao());
//                intent.putExtra("Channel", Channel);
//                mActivity.startActivity(intent);
//            }
//        }

    }


    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return long[] 返回值为：{天, 时, 分, 秒}
     */
    public static int getDistanceTimes(String str1, String str2) {
        long time2 = TimeUtil.timeStamp(str2);
        long time1 = TimeUtil.timeStamp(str1);
        int returnTime = Integer.parseInt((time2 - time1) + "") / 1000;
        KLog.i(returnTime);
        return returnTime;
    }
}