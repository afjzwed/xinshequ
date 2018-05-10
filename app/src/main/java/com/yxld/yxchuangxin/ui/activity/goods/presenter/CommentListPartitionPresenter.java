package com.yxld.yxchuangxin.ui.activity.goods.presenter;
import android.support.annotation.NonNull;

import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyMallComment;
import com.yxld.yxchuangxin.entity.MyAllComment;
import com.yxld.yxchuangxin.ui.activity.goods.contract.CommentListPartitionContract;
import com.yxld.yxchuangxin.ui.activity.goods.CommentListPartitionFragment;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: presenter of CommentListPartitionFragment
 * @date 2017/06/30 16:43:22
 */
public class CommentListPartitionPresenter implements CommentListPartitionContract.CommentListPartitionContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private CommentListPartitionContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private CommentListPartitionFragment mFragment;

    @Inject
    public CommentListPartitionPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull CommentListPartitionContract.View view, CommentListPartitionFragment fragment) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        this.mFragment = fragment;
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
    public void loadCommentFromServer(String productId, String type, int nextPage, int onePageSize) {
        Map<String, String> params = new HashMap<>();
        //params.put("comment.pingjiaLevel", type);
        params.put("goodsId", productId);
        params.put("page", String.valueOf(nextPage));
        params.put("rows", String.valueOf(onePageSize));
        params.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.getGoodsAppraise(params)
                .subscribe(new Consumer<MyAllComment>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull MyAllComment comment) throws Exception {
                        mView.onLoadCommentSucceed(comment);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        mView.onLoadCommentFailed();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }
}