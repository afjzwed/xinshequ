package com.yxld.yxchuangxin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.TimeUtil;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 作者：hu on 2017/6/2
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class MiaoshaTimeView extends AutoLinearLayout {

    private TextView shi;
    private TextView fen;
    private TextView miao;
    private String remainTime;
    private MiaoshaWanchengListener miaoshaWanchengListener;

    public MiaoshaTimeView(Context context) {
        super(context);
        init(context);
    }

    public MiaoshaTimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MiaoshaTimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MiaoshaTimeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.miaosha_time_layout, this, true);
        shi = (TextView) view.findViewById(R.id.tv_shi);
        fen = (TextView) view.findViewById(R.id.tv_fen);
        miao = (TextView) view.findViewById(R.id.tv_miao);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    public void setRemainTime(String remainTime) {
        this.remainTime = remainTime;
        long timeStamp = TimeUtil.timeStamp(remainTime);
        Calendar c = Calendar.getInstance();
        long remainTimeStamp = (timeStamp - c.getTimeInMillis())/1000;
        if (remainTimeStamp <= 0) {
            if (miaoshaWanchengListener != null) {
                miaoshaWanchengListener.onMiaoshaComplete();
            }
            return;
        }
        if (remainTimeStamp < 0 ) {

        }
        Observable.interval(0, 1, TimeUnit.SECONDS).take(remainTimeStamp + 1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return remainTimeStamp - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//发射用的是observeOn
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        KLog.i("1");
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        KLog.i("2");
                    }

                    @Override
                    public void onNext(@NonNull Long remainTime) {
                        setTime(remainTime);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        KLog.i("4");
                    }

                    @Override
                    public void onComplete() {
                        KLog.i("秒杀完成");
                        if (miaoshaWanchengListener != null) {
                            miaoshaWanchengListener.onMiaoshaComplete();
                        }
                    }
                });

    }

    private void setTime(long defferTime) {
//        KLog.i(defferTime);
        long hour = defferTime / 60 / 60;                 //小时
        long remainSecond = (defferTime - 60* 60 *hour);      //除以小时之后剩余的秒数
        long minute = remainSecond / 60;                 //去掉小时之后剩下的分钟数
        long second = remainSecond - 60*minute;                 //剩余的秒数
        if (hour < 10) {
            shi.setText("0" + hour);
        } else {
            shi.setText(hour + "");
        }
        if (minute < 10) {
            fen.setText("0" + minute);
        } else {
            fen.setText(minute + "");
        }
        if (second < 10) {
            miao.setText("0" + second);
        } else {
            miao.setText(second + "");
        }
    }

    public interface MiaoshaWanchengListener {
        void onMiaoshaComplete();
    }

}
