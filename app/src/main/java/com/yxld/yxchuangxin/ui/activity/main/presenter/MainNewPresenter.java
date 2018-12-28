package com.yxld.yxchuangxin.ui.activity.main.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.CxUtil;
import com.yxld.yxchuangxin.Utils.UpdateManager;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyAppVersion;
import com.yxld.yxchuangxin.entity.CxwyMallPezhi;
import com.yxld.yxchuangxin.entity.GoodsKind;
import com.yxld.yxchuangxin.entity.MallClassify;
import com.yxld.yxchuangxin.ui.activity.main.MainNewFragment;
import com.yxld.yxchuangxin.ui.activity.main.contract.MainNewContract;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: presenter of MainNewFragment
 * @date 2018/11/16 10:36:55
 */
public class MainNewPresenter implements MainNewContract.MainNewContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final MainNewContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private MainNewFragment mFragment;

    private CxwyAppVersion mVersion;
//    HomeActivity mActivity;

    @Inject
    public MainNewPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull MainNewContract.View view, MainNewFragment fragment) {
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
    public void getBanner(Map data) {
        Disposable disposable = httpAPIWrapper.getBanner(data).subscribe(new Consumer<CxwyMallPezhi>() {
            @Override
            public void accept(CxwyMallPezhi pezhi) throws Exception {
                //这里接收数据项
                KLog.i("成功的回调");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //这里接收onError
                KLog.i("onError的回调");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                //这里接收onComplete。
                KLog.i("run的回调");
            }
        });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getAction(Map data) {
        Disposable disposable = httpAPIWrapper.getAction(data).subscribe(new Consumer<BaseEntity>() {
            @Override
            public void accept(BaseEntity entity) throws Exception {
                //这里接收数据项
                mView.setAction(entity.getMSG());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //这里接收onError
                KLog.i("onError的回调");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                //这里接收onComplete。
                KLog.i("run的回调");
            }
        });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getMianShaData() {
        Map<String, String> params = new HashMap<>();
        params.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.getGoodsKinds(params).subscribe(new Consumer<GoodsKind>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull GoodsKind goodsKind) throws Exception {
                mView.setMiaoShaDatas(goodsKind);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        });
        mCompositeDisposable.add(disposable);


    }
    @Override
    public void getFenlei() {
        Map<String, String> params = new HashMap<>();
        params.put("uuid", Contains.uuid);
        params.put("fenlei1", "");
        Disposable disposable = httpAPIWrapper.getShangchengFenlei(params).subscribe(new Consumer<MallClassify>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull MallClassify mallClassify) throws Exception {
                mView.setFenleiAdapter(mallClassify);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getLastVersion() {
        Map<String, String> map = new HashMap<>();
        Disposable disposable = httpAPIWrapper.getAppVersionInfo(map)
                .subscribe(new Consumer<CxwyAppVersion>() {
                    @Override
                    public void accept(CxwyAppVersion version) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mVersion = version;
                        if (Float.valueOf(version.getVer().getVersionUId().replace(".", "")) > Float.valueOf(CxUtil.getVersion(mFragment.getActivity()).replace(".", ""))) {
                            alertUpdate();
                        } else {

                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //onError
                        KLog.i("onError");
                        throwable.printStackTrace();
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

    /**
     * 控制更新版本弹框
     */
    private void alertUpdate() {
        // 这里来检测版本是否需要更新
        UpdateManager mUpdateManager = new UpdateManager(mFragment.getActivity(), mVersion.getVer().getVersionDownloadUrl());
        mUpdateManager.checkUpdateInfo(mVersion.getVer().getVersionUId(), mVersion.getVer().getVersionExplain(), mVersion.getVer().getVersionIsCompulsory());
        mUpdateManager.setOnYiHouOnClickListener(new UpdateManager.OnYiHouOnClickListener() {
            @Override
            public void onYihouClick() {
//                if (jump == JUMPTOMAIN) {
//                    mView.loginSuccees();
//                } else if (jump == JUMPTOLOGIN) {
//                    mView.jumpToLogin();
//                } else {
//                    mView.iumpToGuest();
//                }
            }
        });
    }
}