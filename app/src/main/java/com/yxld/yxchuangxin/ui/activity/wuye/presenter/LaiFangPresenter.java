package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.OpenDoorCode;
import com.yxld.yxchuangxin.ui.activity.wuye.LaiFangFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.PhoneOpenDoorActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.LaiFangContract;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of LaiFangFragment
 * @date 2017/06/06
 */
public class LaiFangPresenter implements LaiFangContract.LaiFangContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private LaiFangContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private LaiFangFragment mFragment;

    private Uri uri_DATA = Uri.parse("content://com.android.contacts/data");

    @Inject
    public LaiFangPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull LaiFangContract.View view, LaiFangFragment fragment) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        mFragment = fragment;
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
        mFragment = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) { //请求码为1
            List<String> nameAndNum = UIUtils.getPhoneAndName(data,mFragment.getContext());
            if(nameAndNum.size()!=2){
                ToastUtil.show(mFragment.getContext(),"获取联系人失败");
            }else {
                mView.setOnResult(nameAndNum.get(0), nameAndNum.get(1));
            }

        }
    }

    @Override
    public void getQrCode(Map data) {
        Disposable disposable = httpAPIWrapper.getVisitorQRCode(data)
                .subscribe(new Consumer<OpenDoorCode>() {
                    @Override
                    public void accept(OpenDoorCode code) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        if (!code.getCode().equals("")) {
                            Intent intent = new Intent(mFragment.getActivity(), PhoneOpenDoorActivity.class);
                            intent.putExtra("time", code.getTime());
                            intent.putExtra("code", code.getCode());
                            intent.putExtra("address", mFragment.address);
                            mFragment.getActivity().startActivity(intent);
                            mView.onQrBack();
                        }
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

//    @Override
//    public void getUser(LinkedHashMap data) {
//        Disposable disposable = httpAPIWrapper.getUser(data)
//                .subscribe(new Consumer<User>() {
//                    @Override
//                    public void accept(User user) throws Exception {
//                        //isSuccesse
//                        KLog.i("onSuccesse");
//                        mView.setText(user);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        //onError
//                        KLog.i("onError");
//                    }
//                }, new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        //onComplete
//                        KLog.i("onComplete");
//                    }
//                });
//        mCompositeDisposable.add(disposable);
//    }
}