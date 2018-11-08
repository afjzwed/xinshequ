package com.yxld.yxchuangxin.ui.activity.ywh.presenter;
import android.support.annotation.NonNull;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.YeWeiHuiContract;
import com.yxld.yxchuangxin.ui.activity.ywh.YeWeiHuiActivity;
import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of YeWeiHuiActivity
 * @date 2018/11/07 11:49:57
 */
public class YeWeiHuiPresenter implements YeWeiHuiContract.YeWeiHuiContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final YeWeiHuiContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private YeWeiHuiActivity mActivity;

    @Inject
    public YeWeiHuiPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull YeWeiHuiContract.View view, YeWeiHuiActivity activity) {
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