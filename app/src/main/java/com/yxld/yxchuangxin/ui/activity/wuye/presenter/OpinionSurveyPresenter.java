package com.yxld.yxchuangxin.ui.activity.wuye.presenter;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.OpinionSurveyEntity;
import com.yxld.yxchuangxin.entity.YwhMember;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.OpinionSurveyContract;
import com.yxld.yxchuangxin.ui.activity.wuye.OpinionSurveyActivity;

import java.util.LinkedHashMap;

import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of OpinionSurveyActivity
 * @date 2018/11/12 18:08:34
 */
public class OpinionSurveyPresenter implements OpinionSurveyContract.OpinionSurveyContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final OpinionSurveyContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private OpinionSurveyActivity mActivity;

    @Inject
    public OpinionSurveyPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull OpinionSurveyContract.View view, OpinionSurveyActivity activity) {
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
    public void getData(LinkedHashMap<String, String> map, boolean isRefresh) {
        httpAPIWrapper.getSurveyList(map).subscribe(new Consumer<OpinionSurveyEntity>() {
            @Override
            public void accept(OpinionSurveyEntity baseEntity) throws Exception {
                if (baseEntity.isSuccess()) {
                    if (isRefresh) {
                        mView.setData(true, baseEntity);
                    } else {
                        mView.setData(false, baseEntity);
                    }
                } else {
                    mView.setError();
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                KLog.i("onError");
                mView.setError();
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                KLog.i("onComplete");
            }
        });
    }
}