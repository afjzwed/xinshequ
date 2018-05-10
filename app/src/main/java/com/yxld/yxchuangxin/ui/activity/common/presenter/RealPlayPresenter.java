package com.yxld.yxchuangxin.ui.activity.common.presenter;
import android.support.annotation.NonNull;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.common.contract.RealPlayContract;
import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.common
 * @Description: presenter of RealPlayActivity
 * @date 2017/06/08
 */
public class RealPlayPresenter implements RealPlayContract.RealPlayContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final RealPlayContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public RealPlayPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull RealPlayContract.View view) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
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