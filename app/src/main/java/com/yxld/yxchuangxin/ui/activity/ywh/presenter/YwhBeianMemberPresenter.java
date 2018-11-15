package com.yxld.yxchuangxin.ui.activity.ywh.presenter;
import android.support.annotation.NonNull;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.YwhBeianMemberContract;
import com.yxld.yxchuangxin.ui.activity.ywh.YwhBeianMemberActivity;
import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: presenter of YwhBeianMemberActivity
 * @date 2018/11/15 09:47:02
 */
public class YwhBeianMemberPresenter implements YwhBeianMemberContract.YwhBeianMemberContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final YwhBeianMemberContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private YwhBeianMemberActivity mActivity;

    @Inject
    public YwhBeianMemberPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull YwhBeianMemberContract.View view, YwhBeianMemberActivity activity) {
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