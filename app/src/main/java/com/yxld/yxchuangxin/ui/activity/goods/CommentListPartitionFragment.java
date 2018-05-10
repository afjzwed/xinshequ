package com.yxld.yxchuangxin.ui.activity.goods;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.entity.CxwyMallComment;
import com.yxld.yxchuangxin.entity.MyAllComment;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerCommentListPartitionComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.CommentListPartitionContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.CommentListPartitionModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.CommentListPartitionPresenter;
import com.yxld.yxchuangxin.ui.adapter.goods.GoodsCommentAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/06/30 16:43:22
 */

public class CommentListPartitionFragment extends MyBaseFragment implements CommentListPartitionContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    public static final String ARG_TYPE = "arg_type";
    public static final String ARG_PRODUCT_ID = "arg_product_id";

    @Inject
    CommentListPartitionPresenter mPresenter;
    @BindView(R.id.recycerView)
    RecyclerView recycerView;
    @BindView(R.id.swipeLayouts)
    SwipeRefreshLayout swipeLayouts;

    private static final int ONE_PAGE_SIZE = 10;
    private String mCommentType;
    private String mProductId;
    private int mNextPage;
    private int mTotalSize;

    private List<MyAllComment.DataBean> mComments;
    private GoodsCommentAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment_list_partition, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        mCommentType = mBundle.getString(ARG_TYPE);
        mProductId = mBundle.getString(ARG_PRODUCT_ID);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mComments == null){
            mComments = new ArrayList<>();
        }
        mNextPage = 1;
        mTotalSize =0;
        mAdapter = new GoodsCommentAdapter(mComments,true);
      //  mAdapter.addHeaderView(UIUtils.getRecyclerTopView(getContext(),26));
        recycerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycerView.setAdapter(mAdapter);
        swipeLayouts.setOnRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this,recycerView);
        UIUtils.configSwipeRefreshLayoutColors(swipeLayouts);
    }

    private void loadCommentFromServer() {
        swipeLayouts.setRefreshing(true);
        mPresenter.loadCommentFromServer(mProductId,mCommentType,mNextPage,ONE_PAGE_SIZE);
    }

    @Override
    protected void setupFragmentComponent() {
        DaggerCommentListPartitionComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .commentListPartitionModule(new CommentListPartitionModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(CommentListPartitionContract.CommentListPartitionContractPresenter presenter) {
        mPresenter = (CommentListPartitionPresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void onLoadCommentSucceed(MyAllComment comments) {
        swipeLayouts.setRefreshing(false);
        mAdapter.loadMoreComplete();

        if(comments.getStatus() ==1){
            if(mNextPage ==1){
                mComments.clear();
            }
            mTotalSize = comments.getTotal();
            mNextPage++;
            mComments.addAll(comments.getRows());
            mAdapter.notifyDataSetChanged();
        }else {
            onError(comments.MSG,comments.status);
        }
    }

    @Override
    public void onLoadCommentFailed() {
        swipeLayouts.setRefreshing(false);
        mAdapter.loadMoreComplete();
        ToastUtil.show(getContext(),getResources().getString(R.string.load_failed));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        swipeLayouts.setRefreshing(false);
        mPresenter.unsubscribe();
        mPresenter = null;
        mAdapter.onDestroy();
        mAdapter = null;
        mComments.clear();
        mComments = null;
    }

    @Override
    public void onRefresh() {
        mNextPage = 1;
        mTotalSize =0;
        loadCommentFromServer();
    }

    @Override
    public void onLoadMoreRequested() {
        if(mComments.size() < ONE_PAGE_SIZE || mComments.size()>= mTotalSize){
            mAdapter.loadMoreEnd(false);
        }else {
            mPresenter.loadCommentFromServer(mProductId,mCommentType,mNextPage,ONE_PAGE_SIZE);
        }
    }

    @Override
    public void fetchData() {
        swipeLayouts.post(new Runnable() {
            @Override
            public void run() {
                loadCommentFromServer();
            }
        });
    }
}