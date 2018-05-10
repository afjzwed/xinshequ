package com.yxld.yxchuangxin.ui.activity.camera.presenter;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.Level0Item;
import com.yxld.yxchuangxin.entity.Level1Item;
import com.yxld.yxchuangxin.ui.activity.camera.LearnActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.LearnContract;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: presenter of LearnActivity
 * @date 2017/06/21 10:22:14
 */
public class LearnPresenter implements LearnContract.LearnContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final LearnContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private LearnActivity mActivity;

    @Inject
    public LearnPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull LearnContract.View view, LearnActivity activity) {
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

    public ArrayList<MultiItemEntity> generateData(ArrayList<int[]> data) {
        ArrayList<MultiItemEntity> res = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            int[] ww = data.get(i);
            Level0Item lv0 = new Level0Item( i);
            for (int j = 0; j < ww.length; j++) {
                Level1Item lv1 = new Level1Item(ww[j],i,j);
//                Log.d("...", "第"+i+"行，第" + j + "个=" + ww[j]);
                lv0.addSubItem(lv1);
            }
            res.add(lv0);
        }
        return res;
    };

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