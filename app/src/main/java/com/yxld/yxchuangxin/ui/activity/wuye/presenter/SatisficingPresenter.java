package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.MsgAndSuccess;
import com.yxld.yxchuangxin.entity.Question;
import com.yxld.yxchuangxin.ui.activity.wuye.SatisficingActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.SatisficingContract;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of SatisficingActivity
 * @date 2017/06/20 10:07:43
 */
public class SatisficingPresenter implements SatisficingContract.SatisficingContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private SatisficingContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private SatisficingActivity mActivity;

    @Inject
    public SatisficingPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull SatisficingContract.View view, SatisficingActivity activity) {
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
        mView = null;
    }

    @Override
    public void getQuestion() {
        String manyiduString;
        AssetManager assetManager = AppConfig.getInstance().getAssets();
        try {
            InputStream is = assetManager.open("question.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuffer stringBuffer = new StringBuffer();
            manyiduString = null;
            while ((manyiduString = br.readLine()) != null) {
                stringBuffer.append(manyiduString);
            }
            manyiduString = stringBuffer.toString();
            if (manyiduString != null) {
                KLog.i(manyiduString);
                Gson gson = new Gson();
                Question question = gson.fromJson(manyiduString, Question.class);
                mView.setData(question.getData());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveMaYiDu(String map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.saveManYiDu(map)
                .subscribe(new Consumer<MsgAndSuccess>() {
                    @Override
                    public void accept(MsgAndSuccess success) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        if (success.isSuccess()) {
                            mView.onSuccess();
                        }
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

//    @Override
//    public void getUser(HashMap map) {
//        //mView.showProgressDialog();
//        Disposable disposable = httpAPIWrapper.getUser(map)
//                .subscribe(new Consumer<User>() {
//                    @Override
//                    public void accept(User user) throws Exception {
//                        //isSuccesse
//                        KLog.i("onSuccesse");
//                        mView.setText(user);
//                      //mView.closeProgressDialog();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        //onError
//                        KLog.i("onError");
//                        throwable.printStackTrace();
//                      //mView.closeProgressDialog();
//                      //ToastUtil.show(mActivity, mActivity.getString(R.string.loading_failed_1));
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