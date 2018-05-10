package com.yxld.yxchuangxin.ui.activity.camera.presenter;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.p2p.core.P2PHandler;
import com.p2p.core.P2PSpecial.HttpErrorCode;
import com.p2p.core.global.P2PConstants;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.APPCamera;
import com.yxld.yxchuangxin.entity.AppCameraList;
import com.yxld.yxchuangxin.ui.activity.camera.DeviceActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.DeviceContract;
import com.yxld.yxchuangxin.yoosee.FriendStatus;
import com.yxld.yxchuangxin.yoosee.P2PListener;
import com.yxld.yxchuangxin.yoosee.SettingListener;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static com.yxld.yxchuangxin.application.AppConfig.APPID;
import static com.yxld.yxchuangxin.application.AppConfig.APPToken;
import static com.yxld.yxchuangxin.contain.Contains.user;
import static com.yxld.yxchuangxin.ui.activity.camera.presenter.DeviceListPresenter.LoginID;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: presenter of DeviceActivity
 * @date 2017/06/20 17:26:32
 */
public class DevicePresenter implements DeviceContract.DeviceContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final DeviceContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private DeviceActivity mActivity;

//    public static String LoginID;//登录返回
    private String[] Deviceid;
    private AppCameraList camearList;

    @Inject
    public DevicePresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull DeviceContract.View view, DeviceActivity activity) {
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
    public void Login() {
        mView.showProgressDialog();
        String sha256 = StringUitl.encryptSHA256ToString(APPID + user.getYezhuShouji());
        String md5 = StringUitl.Md5(sha256 + 3 + sha256);
//        KLog.i("我的包名是：" + mActivity.getPackageName());
        KLog.i("我的包名是：" + "com.yxld.yxchuangxin");
        KLog.i("SHA256加密 ：" + sha256);
        KLog.i("MD5 32bit：" + md5);
        Map<String, String> map = new HashMap<String, String>();
        map.put("AppVersion", "56492291");
        map.put("AppOS", "3");//0.其它 1.windows 2.iOS 3.android 4.arm_linux
        map.put("AppName", "xinshequ");//app的英文名
        map.put("Language", "zh-Hans");//语言的缩写
        map.put("ApiVersion", "1");//固定传1
        map.put("AppID", APPID);//技威公司分配
        map.put("AppToken", APPToken);//技威公司分配
        map.put("PackageName", "com.yxld.yxchuangxin");//包名
        map.put("Option", "3");//传3即可
        map.put("PlatformType", "3");//传3即可
        map.put("UnionID", sha256);//UnionID=sha256(AppID+唯一序列号);
        map.put("User", ""); //传空即可
        map.put("UserPwd", ""); //传空即可
        map.put("Token", ""); //传空即可
        map.put("StoreID", ""); //传空即可
        map.put("Sign", md5);//Sign=md5(UnionID+PlatformType+UnionID),md5是md5_32bit的加密算法
        //mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getCameraLogin(map)
                .subscribe(new Consumer<APPCamera>() {
                    @Override
                    public void accept(APPCamera camera) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        loginCallBack(camera);
                        //mView.closeProgressDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //onError
                        KLog.i("onError");
                        throwable.printStackTrace();
                        //mView.closeProgressDialog();
                        //ToastUtil.show(mActivity, mActivity.getString(R.string.loading_failed_1));
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

    private void loginCallBack(APPCamera info) {
        mView.closeProgressDialog();
        switch (info.getError_code()) {
            case HttpErrorCode.ERROR_0:
                int code1 = Integer.parseInt(info.getP2PVerifyCode1());
                int code2 = Integer.parseInt(info.getP2PVerifyCode2());
                boolean connect = P2PHandler.getInstance().p2pConnect(info.getUserID(), code1, code2);
                KLog.i("connect===========" + info.getUserID() + "+" + code1 + "+" + code2);
                if (connect) {
                    P2PHandler.getInstance().p2pInit(AppConfig.getInstance(), new P2PListener(), new SettingListener());
                    LoginID = info.getUserID();
                    getAllCamera();
                    KLog.i(LoginID + "-----------------");
                } else {
                    Toast.makeText(mActivity, "连接失败,请重新进入居家安防", Toast.LENGTH_LONG).show();
                }
                break;
            case HttpErrorCode.ERROR_998:
                break;
            case HttpErrorCode.ERROR_10902011:
                Toast.makeText(mActivity, "用户不存在", Toast.LENGTH_LONG).show();
                break;
            default:
                String msg = String.format("摄像头登录失败测试版(%s)", info.getError_code());
                Toast.makeText(mActivity, msg, Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void getAllCamera() {       //?apptoken=%1$s
        mView.showProgressDialog();
        Map<String, String> map = new HashMap<String, String>();
        map.put("apptoken", Contains.uuid);
        Disposable disposable = httpAPIWrapper.getAllCamera(map)
                .subscribe(new Consumer<AppCameraList>() {
                    @Override
                    public void accept(AppCameraList list) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        if (list.getStatus() != 0) {
                            ToastUtil.show(mActivity, list.getMSG());
                            return;
                        }
                        camearList = list;
                        Deviceid = new String[list.getData().size()];
                        for (int i = 0; i < list.getData().size(); i++) {
                            Deviceid[i] = list.getData().get(i).getSb_ipc_id();
                        }
                        P2PHandler.getInstance().getFriendStatus(Deviceid, P2PConstants.P2P_Server.SERVER_INDEX);
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

    @Override
    public void DeviceStatus(FriendStatus msg) {
        if (msg.bRequestResult == 0) {
            P2PHandler.getInstance().getFriendStatus(Deviceid, P2PConstants.P2P_Server.SERVER_INDEX);
            Toast.makeText(mActivity, "状态获取失败~  重试中", Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < msg.status.length; i++) {
                camearList.getData().get(i).setSb_status(msg.status[i]);
                Log.d("...", "vRetGetIndexFriendStatus: status" + msg.status[i]);
            }
            mView.setCameraList(camearList);
        }
    }

    @Override
    public void deletCamera(Map map, int postion) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.deletCamera(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity entity) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        mView.deletOne(postion);
                        ToastUtil.show(mActivity, "删除设备成功");
                        getAllCamera();
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

    @Override
    public void getHost(Map map) {
        Disposable disposable = httpAPIWrapper.searchAllHost(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity entity) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
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