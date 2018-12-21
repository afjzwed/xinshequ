package com.yxld.yxchuangxin.ui.activity.ywh.presenter;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.YwhMember;
import com.yxld.yxchuangxin.entity.YwhTj;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.YwhMemberShowContract;
import com.yxld.yxchuangxin.ui.activity.ywh.YwhMemberShowActivity;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of YwhMemberShowActivity
 * @date 2018/11/07 20:37:02
 */
public class YwhMemberShowPresenter implements YwhMemberShowContract.YwhMemberShowContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final YwhMemberShowContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private YwhMemberShowActivity mActivity;

    @Inject
    public YwhMemberShowPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull YwhMemberShowContract.View view,
                                  YwhMemberShowActivity activity) {
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
        Disposable subscribe = httpAPIWrapper.getMemberShowList(map).subscribe(new Consumer<YwhMember>() {
            @Override
            public void accept(YwhMember baseEntity) throws Exception {
                if (baseEntity.isSuccess()) {
                    if (isRefresh) {
                        mView.setData(true, baseEntity);
                    } else {
                        mView.setData(false, baseEntity);
                    }
                } else {
                    if (TextUtils.isEmpty(baseEntity.msg)) {
                        mView.setError("加载失败");
                    } else {
                        mView.setError(baseEntity.msg + "");
                    }

                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                KLog.i("onError");
                mView.setError("加载失败");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                KLog.i("onComplete");
            }
        });
        mCompositeDisposable.add(subscribe);
    }
}