package com.yxld.yxchuangxin.ui.activity.goods;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.entity.CxwyDianziquan;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerTicketComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.TicketContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.TicketModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.TicketPresenter;
import com.yxld.yxchuangxin.ui.adapter.goods.ETicketAdapter;
import com.yxld.yxchuangxin.view.AutoCoordinatorLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/06/22 10:47:40
 */

public class TicketActivity extends BaseActivity implements TicketContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    @Inject
    TicketPresenter mPresenter;
    @BindView(R.id.iv_large)
    ImageView ivLarge;
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.reccyclerview)
    RecyclerView reccycLerview;
    @BindView(R.id.toolbar_private)
    Toolbar toolbarPrivate;
    @BindView(R.id.ll_main)
    AutoCoordinatorLayout llMain;
    @BindView(R.id.tv_ticket_money)
    TextView tvTicketMoney;
    private int mNextPage;
    private int mTotalOrderNum;

    private List<CxwyDianziquan.RowsBean> mDianZiQuans;
    private ETicketAdapter mAdapter;
    private static final int ONE_PAGE_SIZE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        needFront = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ticket);
        ButterKnife.bind(this);
        toolbar.setVisibility(View.GONE);
        toolbarPrivate.setPadding(0, UIUtils.getStatusBarHeight(this), 0, 0);
        setSupportActionBar(toolbarPrivate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        if (mDianZiQuans == null) {
            mDianZiQuans = new ArrayList<>();
        }
        mAdapter = new ETicketAdapter(mDianZiQuans);
        reccycLerview.setAdapter(mAdapter);
        mAdapter.setEmptyView(R.layout.layout_empty_data, (ViewGroup) reccycLerview.getParent());
        mNextPage = 1;
        mTotalOrderNum = 0;
        mAdapter.setOnLoadMoreListener(this, reccycLerview);
        mPresenter.loadDianZiQuanListFromServer(mNextPage, ONE_PAGE_SIZE);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerTicketComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .ticketModule(new TicketModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(TicketContract.TicketContractPresenter presenter) {
        mPresenter = (TicketPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();

    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    private void onLoadDataFromSever(boolean isShow) {

    }

    @Override
    public void onLoadDianZiQuanListSucceed(CxwyDianziquan dianZiQuanList) {
        mAdapter.loadMoreComplete();
        if (dianZiQuanList.status == 1) {
            if (mNextPage == 1) {
                mDianZiQuans.clear();
            }
            mTotalOrderNum = dianZiQuanList.getTotal();
            mDianZiQuans.addAll(dianZiQuanList.getList());
            if (mDianZiQuans.size() < mTotalOrderNum) {
                mNextPage++;
            }
            mAdapter.notifyDataSetChanged();
            tvTicketMoney.setText("¥ " + dianZiQuanList.getBalance());
//            Observable.create(new ObservableOnSubscribe<List<CxwyDianziquan.RowsBean>>() {
//                @Override
//                public void subscribe(@NonNull ObservableEmitter<List<CxwyDianziquan.RowsBean>> e) throws Exception {
//                    List<CxwyDianziquan.RowsBean> rows = dianZiQuanList.getList();
//                    Collections.sort(rows, new Comparator<CxwyDianziquan.RowsBean>() {
//                        @Override
//                        public int compare(CxwyDianziquan.RowsBean o1, CxwyDianziquan.RowsBean o2) {
//                            return o2.getGivenTime().compareTo(o1.getGivenTime());
//                        }
//                    });
//                    e.onNext(rows);
//                    e.onComplete();
//                }
//            }).subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Consumer<List<CxwyDianziquan.RowsBean>>() {
//                        @Override
//                        public void accept(@NonNull List<CxwyDianziquan.RowsBean> cxwyDianziquen) throws Exception {
//                            mDianZiQuans.clear();
//                            mDianZiQuans.addAll(cxwyDianziquen);
//
//                            mAdapter.notifyDataSetChanged();
//                        }
//                    }, new Consumer<Throwable>() {
//                        @Override
//                        public void accept(@NonNull Throwable throwable) throws Exception {
//                            ToastUtil.show(TicketActivity.this, "获取数据错误");
//                            throwable.printStackTrace();
//                        }
//                    });

        } else {
            onError(dianZiQuanList.MSG, dianZiQuanList.status);
        }
    }

    @Override
    public void onLoadDianZiQuanListFailed() {
        ToastUtil.show(TicketActivity.this, getResources().getString(R.string.load_failed));
    }

    @Override
    public void onLoadMoreRequested() {
        if (mDianZiQuans.size() < ONE_PAGE_SIZE || mDianZiQuans.size() >= mTotalOrderNum) {
            mAdapter.loadMoreEnd(false);
            return;
        }
        mPresenter.loadDianZiQuanListFromServer(mNextPage, ONE_PAGE_SIZE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
        mPresenter = null;
        mDianZiQuans.clear();
        mDianZiQuans = null;
        mAdapter.onDestroy();
        mAdapter = null;
    }
}