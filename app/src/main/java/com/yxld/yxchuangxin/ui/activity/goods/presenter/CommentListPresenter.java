package com.yxld.yxchuangxin.ui.activity.goods.presenter;
import android.support.annotation.NonNull;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.goods.contract.CommentListContract;
import com.yxld.yxchuangxin.ui.activity.goods.CommentListActivity;
import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: presenter of CommentListActivity
 * @date 2017/06/29 11:01:08
 */
public class CommentListPresenter implements CommentListContract.CommentListContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private CommentListContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private CommentListActivity mActivity;

    @Inject
    public CommentListPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull CommentListContract.View view, CommentListActivity activity) {
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
}