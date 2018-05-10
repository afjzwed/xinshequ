package com.yxld.yxchuangxin.ui.activity.rim.presenter;
import android.support.annotation.NonNull;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimComplainDetailContract;
import com.yxld.yxchuangxin.ui.activity.rim.RimComplainDetailActivity;
import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.rimcomplaindetail
 * @Description: presenter of RimComplainDetailActivity
 * @date 2017/12/21 15:47:26
 */
public class RimComplainDetailPresenter implements RimComplainDetailContract.RimComplainDetailContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final RimComplainDetailContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private RimComplainDetailActivity mActivity;

    @Inject
    public RimComplainDetailPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull RimComplainDetailContract.View view, RimComplainDetailActivity activity) {
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