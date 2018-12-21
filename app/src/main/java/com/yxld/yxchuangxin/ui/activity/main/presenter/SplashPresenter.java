package com.yxld.yxchuangxin.ui.activity.main.presenter;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yxld.yxchuangxin.Utils.CxUtil;
import com.yxld.yxchuangxin.Utils.SpUtil;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UpdateManager;
import com.yxld.yxchuangxin.Utils.VersionUtil;
import com.yxld.yxchuangxin.contain.ContainValue;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyAppVersion;
import com.yxld.yxchuangxin.entity.LoginEntity;
import com.yxld.yxchuangxin.ui.activity.main.SplashActivity;
import com.yxld.yxchuangxin.ui.activity.main.contract.SplashContract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static com.yxld.yxchuangxin.ui.activity.wuye.presenter.FixPresenter.PHOTOTAKE;
import static com.yxld.yxchuangxin.ui.activity.wuye.presenter.UpdatePresenter.REQUEST_CODE_ASK_WRITE_EXTERNAL_STORAGE;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: presenter of SplashActivity
 * @date 2017/06/05
 */
public class SplashPresenter implements SplashContract.SplashContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final SplashContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    SplashActivity mActivity;
    private String name;
    private String pwd;
    private int xiangmuId;

    private static final int JUMPTOMAIN = 0;
    private static final int JUMPTOLOGIN = 1;
    private static final int JUMPTOGUEST = 2;    //跳到欢迎页面
    private static final int HASPUDATE = 3;     //表示是否有更新
    private static int permissionState = -1;    //-1表示原始状态,0表示允许,1表示拒绝.
    private boolean getLastVersionBack = false;
    private boolean hasUpdate = false;
    private boolean timeOver = false;
    private int jump = JUMPTOLOGIN;

    private CxwyAppVersion mVersion;

    @Inject
    public SplashPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull SplashContract.View view, @NonNull SplashActivity splashActivity) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        this.mActivity = splashActivity;
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
    public void queryShipperInfo() {
        if (isUpdate()) {
            return;
        }
        boolean savePsd = mActivity.sp.getBoolean("ISCHECK", false);
        if (!savePsd) {
            jump = JUMPTOLOGIN;
        } else {
            AndPermission.with(mActivity)
                    .requestCode(100)
                    .permission(
                            Manifest.permission.READ_PHONE_STATE
                    )
//                    .rationale((requestCode, rationale) -> {
//                                AndPermission.rationaleDialog(mActivity, rationale).show();
//                            }
//                    )
                    .callback(permissionListener)
                    .start();
        }
    }

    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。
            if (requestCode == 100) {
                name = mActivity.sp.getString("NAME", "");
                pwd = mActivity.sp.getString("PASSWORD", "");
                xiangmuId = mActivity.sp.getInt("xiangmuId", 0);
                String mac = StringUitl.getDeviceId(mActivity);
                Map<String, String> params = new HashMap<String, String>();
                params.put("shouji", name);
                params.put("pwd", StringUitl.getMD5(pwd));
                params.put("macAddr", mac);
                params.put("id", xiangmuId + "");
                params.put("app_version", VersionUtil.getAppVersionName(mActivity));
                params.put("mobile_type", "A");
                params.put("mobile_brand", StringUitl.getPhoneBrand() + StringUitl.getPhoneModel());
                params.put("mobile_version", StringUitl.getPhoneSysVersion());
                login(params);
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if (requestCode == PHOTOTAKE) {
                // TODO ...
            }
        }
    };

    @Override
    public void login(Map map) {
        KLog.i(map);
        Disposable disposable = httpAPIWrapper.Login(map)
                .subscribe(new Consumer<LoginEntity>() {
                    @Override
                    public void accept(LoginEntity info) throws Exception {
                        if (jump == HASPUDATE) {
                            return;
                        }
                        if (info.getStatus() != 0) {
                            jump = JUMPTOLOGIN;
                            return;
                        }
                        Contains.uuid = info.getUuid();
                        Contains.user = info.getUser();
                        Contains.yeZhuVo=info.getYezhuVo();
                        if (info.getHouse() != null && info.getHouse().size() > 0) {
                            Contains.appYezhuFangwus = info.getHouse();
                            Contains.curSelectXiaoQuName = info.getHouse().get(0).getXiangmuLoupan();
                            Contains.curSelectXiaoQuId = info.getHouse().get(0).getFwLoupanId();

                            //设置默认地址项目
                            if (Contains.user.getYezhuName() != null && !"".equals(Contains.user.getYezhuName())) {
                                Contains.defuleAddress.setAddName(Contains.user.getYezhuName());
                            } else {
                                Contains.defuleAddress.setAddName(Contains.user.getYezhuShouji());
                            }
                            Contains.defuleAddress.setAddTel(Contains.user.getYezhuShouji());
                            Contains.defuleAddress.setAddAdd(info.getHouse().get(0).getXiangmuLoupan() +
                                    info.getHouse().get(0).getFwLoudong() + "栋" +
                                    info.getHouse().get(0).getFwDanyuan() + "单元" +
                                    info.getHouse().get(0).getFwFanghao());
                        }else {
                            ToastUtil.showShort("账号异常请联系管理员");
                            jump = JUMPTOLOGIN;
                            return;
                        }

                        if (info.getAddr() != null) {
                            Contains.defuleAddress = info.getAddr();
                        }

                        SharedPreferences.Editor editor = mActivity.sp.edit();
                        editor.putString("NAME", name);
                        editor.putString("PASSWORD", pwd);
                        editor.commit();
                        mActivity.sp.edit().putBoolean("ISCHECK", true).commit();
                        jump = JUMPTOMAIN;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        KLog.i("onError");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        //onMiaoshaComplete
                        KLog.i("onMiaoshaComplete");
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void observeJump() {
//        Observable.interval(0, 1, TimeUnit.SECONDS).take(2)
//                .map(new Function<Long, Long>() {
//                    @Override
//                    public Long apply(@NonNull Long aLong) throws Exception {
//                        return 2 - aLong;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())//发射用的是observeOn
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(@NonNull Disposable disposable) throws Exception {
//                        KLog.i("1");
//                    }
//                })
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        KLog.i("2");
//                    }
//
//                    @Override
//                    public void onNext(@NonNull Long remainTime) {
//                        KLog.i("剩余时间" + remainTime);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        KLog.i("4");
//                    }
//
//                    @Override
//                    public void onComplete() {
////                        jump = JUMPTOGUEST;
//                        timeOver = true;
//                        KLog.i("时间到，开始跳转");
//                        if (permissionState != 0) {
//                            return;
//                        }
//                        if (hasUpdate) {
//                            return;
//                        }
//                        if (jump == JUMPTOMAIN) {
//                            mView.loginSuccees();
//                        } else if (jump == JUMPTOLOGIN) {
//                            mView.jumpToLogin();
//                        } else {
//                            mView.iumpToGuest();
//                        }
//                    }
//                });   Observable.interval(0, 1, TimeUnit.SECONDS).take(2)
//                .map(new Function<Long, Long>() {
//                    @Override
//                    public Long apply(@NonNull Long aLong) throws Exception {
//                        return 2 - aLong;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())//发射用的是observeOn
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(@NonNull Disposable disposable) throws Exception {
//                        KLog.i("1");
//                    }
//                })
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        KLog.i("2");
//                    }
//
//                    @Override
//                    public void onNext(@NonNull Long remainTime) {
//                        KLog.i("剩余时间" + remainTime);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        KLog.i("4");
//                    }
//
//                    @Override
//                    public void onComplete() {
////                        jump = JUMPTOGUEST;
//                        timeOver = true;
//                        KLog.i("时间到，开始跳转");
//                        if (permissionState != 0) {
//                            return;
//                        }
//                        if (hasUpdate) {
//                            return;
//                        }
//                        if (jump == JUMPTOMAIN) {
//                            mView.loginSuccees();
//                        } else if (jump == JUMPTOLOGIN) {
//                            mView.jumpToLogin();
//                        } else {
//                            mView.iumpToGuest();
//                        }
//                    }
//                });

        timeOver = true;
        KLog.i("时间到，开始跳转");
        if (permissionState != 0) {
            return;
        }
        if (hasUpdate) {
            return;
        }
        if (jump == JUMPTOMAIN) {
            mView.loginSuccees();
        } else if (jump == JUMPTOLOGIN) {
            mView.jumpToLogin();
        } else {
            mView.iumpToGuest();
        }
    }

    /**
     * 判断是否是更新过来的，如果是，就进入欢迎页面
     *
     * @return
     */
    @Override
    public boolean isUpdate() {
        if (SpUtil.getInt(mActivity, ContainValue.CURVERSIONCODE, 0) < VersionUtil.getAppVersionCode(mActivity)) {
            jump = JUMPTOGUEST;
            SpUtil.putInt(mActivity, ContainValue.CURVERSIONCODE, VersionUtil.getAppVersionCode(mActivity));
            return true;
        } else {
            return false;
        }
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
                        if (Float.valueOf(version.getVer().getVersionUId().replace(".", "")) > Float.valueOf(CxUtil.getVersion(mActivity).replace(".", ""))) {
                            getLastVersionBack = true;
                            hasUpdate = true;
                            getUpdatePermission();
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

    @Override
    public void getUpdatePermission() {
        AndPermission.with(mActivity)
                .requestCode(REQUEST_CODE_ASK_WRITE_EXTERNAL_STORAGE)
                .permission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
//                .rationale((requestCode, rationale) -> {
//                            AndPermission.rationaleDialog(mActivity, rationale).show();
//                        }
//                )
                .callback(updateListener)
                .start();
    }

    private PermissionListener updateListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。
            if (requestCode == REQUEST_CODE_ASK_WRITE_EXTERNAL_STORAGE) {
                alertUpdate();
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if (requestCode == REQUEST_CODE_ASK_WRITE_EXTERNAL_STORAGE) {
                // TODO ...
            }
        }
    };

    /**
     * 控制更新版本弹框
     */
    private void alertUpdate() {
        // 这里来检测版本是否需要更新
        UpdateManager mUpdateManager = new UpdateManager(mActivity, mVersion.getVer().getVersionDownloadUrl());
        mUpdateManager.checkUpdateInfo(mVersion.getVer().getVersionUId(), mVersion.getVer().getVersionExplain(), mVersion.getVer().getVersionIsCompulsory());
        mUpdateManager.setOnYiHouOnClickListener(new UpdateManager.OnYiHouOnClickListener() {
            @Override
            public void onYihouClick() {
                if (jump == JUMPTOMAIN) {
                    mView.loginSuccees();
                } else if (jump == JUMPTOLOGIN) {
                    mView.jumpToLogin();
                } else {
                    mView.iumpToGuest();
                }
            }
        });
    }

    public void getPermission() {
        AndPermission.with(mActivity)
                .requestCode(101)
                .permission(
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.INTERNET,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_CONTACTS
                )
                .rationale((requestCode, rationale) -> {
                            AndPermission
                                    .rationaleDialog(mActivity, rationale)
                                    .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            ToastUtil.show(mActivity, "权限申请失败,app部分功能将无法使用!!!");
                                            if (jump == JUMPTOMAIN) {
                                                mView.loginSuccees();
                                            } else if (jump == JUMPTOLOGIN) {
                                                mView.jumpToLogin();
                                            } else {
                                                mView.iumpToGuest();
                                            }
                                        }
                                    })
                                    .show();
                        }
                )
                .callback(permission)
                .start();
    }

    private PermissionListener permission = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。
            if (requestCode == 101) {
                permissionState = 0;
                if (timeOver) {
                    if (jump == JUMPTOMAIN) {
                        mView.loginSuccees();
                    } else if (jump == JUMPTOLOGIN) {
                        mView.jumpToLogin();
                    } else {
                        mView.iumpToGuest();
                    }
                }
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if (requestCode == 101) {
                KLog.i("权限申请失败");
                permissionState = 1;
                ToastUtil.show(mActivity, "权限申请失败,app部分功能将无法使用!!!");
                if (jump == JUMPTOMAIN) {
                    mView.loginSuccees();
                } else if (jump == JUMPTOLOGIN) {
                    mView.jumpToLogin();
                } else {
                    mView.iumpToGuest();
                }
            }
        }
    };

    /**
     * 判断是否安装了老版的欣社区
     *
     * @param context
     * @return
     */
    public static boolean isXsqAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.yxld.yxchuangxin")) {
                    return true;
                }
            }
        }
        return false;
    }
}