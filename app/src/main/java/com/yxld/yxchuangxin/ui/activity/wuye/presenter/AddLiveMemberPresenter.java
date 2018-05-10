package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.BaseBack1;
import com.yxld.yxchuangxin.ui.activity.wuye.AddLiveMemberActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.AddLiveMemberContract;

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
 * @Description: presenter of AddLiveMemberActivity
 * @date 2017/06/07
 */
public class AddLiveMemberPresenter implements AddLiveMemberContract.AddLiveMemberContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private AddLiveMemberContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private AddLiveMemberActivity mActivity;

    private Uri uri_DATA = Uri.parse("content://com.android.contacts/data");

    @Inject
    public AddLiveMemberPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull AddLiveMemberContract.View view, AddLiveMemberActivity mActivity) {
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
        mView = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) { //请求码为1
            List<String> nameAndNum = UIUtils.getPhoneAndName(data,mActivity);
            if(nameAndNum.size()!=2){
                ToastUtil.show(mActivity,"获取联系人失败");
            }else {
                mView.setContactNumber(nameAndNum.get(0), nameAndNum.get(1));
            }
        }
    }

    @Override
    public void addChengyuan(Map data) {
        Disposable disposable = httpAPIWrapper.addChengyuan(data)
                .subscribe(new Consumer<BaseBack1>() {
                    @Override
                    public void accept(BaseBack1 back) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.onAddBack(back.getMSG());
                        mActivity.finish();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //onError
                        KLog.i("onError");
                        mView.onAddBack("保存失败，请重试");
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