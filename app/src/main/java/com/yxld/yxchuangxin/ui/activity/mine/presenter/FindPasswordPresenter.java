package com.yxld.yxchuangxin.ui.activity.mine.presenter;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.mob.MobSDK;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.LoginPhoneEntity;
import com.yxld.yxchuangxin.ui.activity.mine.FindPasswordActivity;
import com.yxld.yxchuangxin.ui.activity.mine.contract.FindPasswordContract;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: presenter of FindPasswordActivity
 * @date 2017/06/23 14:14:05
 */
public class FindPasswordPresenter implements FindPasswordContract.FindPasswordContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final FindPasswordContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private FindPasswordActivity mActivity;

    // 填写从短信SDK应用后台注册得到的APPKEY
    private  String APPKEY = "14e1cc04efbc0";
    // 填写从短信SDK应用后台注册得到的APPSECRET
    private  String APPSECRET = "39a445e014ef8b2575238d50f97b94b2";

    @Inject
    public FindPasswordPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull FindPasswordContract.View view, FindPasswordActivity activity) {
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
    public void loginPlot(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getloginPlot(map)
                .subscribe(new Consumer<LoginPhoneEntity>() {
                    @Override
                    public void accept(LoginPhoneEntity loginPhoneEntity) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        mView.setLoginPhone(loginPhoneEntity);
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
    public void getCheckCode(Map map) {

    }

    @Override
    public void getReSetPassWord(Map map) {      //getFindPwd      ?shouji=%1$s&pwd=%2$s&id=%3$s
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getFindPwd(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity entity) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        mView.setResetPassWordSuccess();
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

    String phoneNumber = "";
    @Override
    public void getExistShouji(Map map, String phone) {
        MobSDK.init(mActivity, APPKEY, APPSECRET);
        EventHandler eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eh);
        this.phoneNumber = phone;
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getExistShouji(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity entity) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        if (entity.status != 0) {
                            Toast.makeText(mActivity, R.string.cannot_find_password, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        //发送获取验证码的请求
                        SMSSDK.getVerificationCode("86", phoneNumber);
                        mView.showProgressDialog();
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
    public void checkCheckCode(String shouji, String checkCode) {
        SMSSDK.submitVerificationCode("86",shouji, checkCode);
    }

    Disposable mDisposable;
    private void startTime() {
        Observable.interval(0, 1, TimeUnit.SECONDS).take(60 + 1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return 60 - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//发射用的是observeOn
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        KLog.i("开始倒计时");
                        mDisposable = disposable;
                        mView.setStartTime();
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
                        mView.setSecond(remainTime + "s");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        KLog.i("4");
                    }

                    @Override
                    public void onComplete() {
                        KLog.i("倒计时完成");
                        mView.setSecondOver();
                    }
                });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            KLog.e("event", "event=" + event);
            if (result == SMSSDK.RESULT_COMPLETE) {
                mView.closeProgressDialog();
                System.out.println("----" + event);
                //短信注册成功后
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        Toast.makeText(mActivity, R.string.validate_code_success, Toast.LENGTH_SHORT).show();
                        //修改密码的网络请求
                        mDisposable.dispose();
                        mView.checkCodeCheckSuccess();
                        mView.setSecondOver();
                    }
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    Toast.makeText(mActivity, R.string.validate_code_send, Toast.LENGTH_SHORT).show();
                    //开始读秒
                    startTime();
                } else if (event == SMSSDK.RESULT_ERROR) {
                    Toast.makeText(mActivity, R.string.validate_code_send_fail, Toast.LENGTH_SHORT).show();
                }
            } else {
                ((Throwable) data).printStackTrace();
                try {
                    JSONObject josn=new JSONObject(((Throwable) data).getMessage().toString());
                    Toast.makeText(mActivity, josn.get("detail").toString(), Toast.LENGTH_SHORT).show();
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

        }

    };
}