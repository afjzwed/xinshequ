package com.yxld.yxchuangxin.ui.activity.wuye.presenter;
import android.support.annotation.NonNull;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.WebSatisficingContract;
import com.yxld.yxchuangxin.ui.activity.wuye.WebSatisficingActivity;
import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.websatisficing
 * @Description: presenter of WebSatisficingActivity
 * @date 2017/12/22 18:18:04
 */
public class WebSatisficingPresenter implements WebSatisficingContract.WebSatisficingContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final WebSatisficingContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private WebSatisficingActivity mActivity;

    @Inject
    public WebSatisficingPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull WebSatisficingContract.View view, WebSatisficingActivity activity) {
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