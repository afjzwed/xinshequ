package com.yxld.yxchuangxin.ui.activity.goods;

import android.content.Intent;
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
import com.yxld.yxchuangxin.entity.OrderComplainEntity;
import com.yxld.yxchuangxin.entity.goods.MallOrderSuggest;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerOrderComplainPartitionComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.OrderComplainPartitionContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.OrderComplainPartitionModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.OrderComplainPartitionPresenter;
import com.yxld.yxchuangxin.ui.adapter.OrderComplainAdapter;

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
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/06/22 18:31:29
 */

public class OrderComplainPartitionFragment extends MyBaseFragment implements OrderComplainPartitionContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    public static final String ARG_TYPE = "arg_type";
    private static final int ONE_PAGE_SIZE = 5;
    @Inject
    OrderComplainPartitionPresenter mPresenter;
    @BindView(R.id.recycler_order_complain)
    RecyclerView recyclerOrderComplain;
    @BindView(R.id.swipeLayouts)
    SwipeRefreshLayout swipeLayouts;

    private String mType;
    private OrderComplainAdapter mAdapter;
    private List<MallOrderSuggest.DataBean> mDatas;
    private int mTotal;
    private int mNextPage;


    public static OrderComplainPartitionFragment newInstance(String type) {
        OrderComplainPartitionFragment fragment = new OrderComplainPartitionFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle mBundle = getArguments();
        if (mBundle != null) {
            mType = mBundle.getString(ARG_TYPE);
        }
    }

    @Override
    public void fetchData() {
        swipeLayouts.post(new Runnable() {
            @Override
            public void run() {
                loadComplainListFromServer(true);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_complain_partition, container,false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    protected void setupFragmentComponent() {
        DaggerOrderComplainPartitionComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .orderComplainPartitionModule(new OrderComplainPartitionModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(OrderComplainPartitionContract.OrderComplainPartitionContractPresenter presenter) {
        mPresenter = (OrderComplainPartitionPresenter) presenter;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        mTotal = 0;
        mNextPage = 1;

        mAdapter = new OrderComplainAdapter(mDatas);
        recyclerOrderComplain.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerOrderComplain.setAdapter(mAdapter);
        UIUtils.configSwipeRefreshLayoutColors(swipeLayouts);
        swipeLayouts.setOnRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this, recyclerOrderComplain);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent(getContext(),OrderComplainDetailActivity.class);
             //   intent.putExtra(OrderComplainDetailActivity.KEY_ENTITY,mDatas.get(i));
                startActivity(intent);
            }
        });
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
    public void onOrderComplainDataBacked(MallOrderSuggest entities) {
        swipeLayouts.setRefreshing(false);
        mAdapter.loadMoreComplete();
        if (entities.getStatus() == 1) {
            mAdapter.addData(entities.getData());

           // handlerEntity(entities.getMalllist());
        } else {
            onError(entities.MSG, entities.status);
        }

    }

    private void handlerEntity(List<OrderComplainEntity> malllist) {

        Observable.create(new ObservableOnSubscribe<List<OrderComplainEntity>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<OrderComplainEntity>> e) throws Exception {
                List<OrderComplainEntity> entities = new ArrayList<OrderComplainEntity>();
                entities.addAll(malllist);
                Collections.sort(entities, new Comparator<OrderComplainEntity>() {
                    @Override
                    public int compare(OrderComplainEntity o1, OrderComplainEntity o2) {
                        return (int) (o2.getTsjyShijian().getTime()-o1.getTsjyShijian().getTime());
                    }
                });
                e.onNext(entities);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<OrderComplainEntity>>() {
                    @Override
                    public void accept(@NonNull List<OrderComplainEntity> entities) throws Exception {
                        if (mNextPage == 1) {
                            mDatas.clear();
                        }
                        mNextPage++;
                    //    mDatas.addAll(entities);
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void onLoadDataFailed() {
        ToastUtil.show(getContext(), getResources().getString(R.string.load_failed));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        swipeLayouts.setRefreshing(false);
        mPresenter.unsubscribe();
        mPresenter = null;
        mAdapter = null;
        mDatas.clear();
        mDatas = null;
    }

    @Override
    public void onRefresh() {
        mTotal = 0;
        mNextPage = 1;
        loadComplainListFromServer(true);
    }

    @Override
    public void onLoadMoreRequested() {
        if (mDatas.size() >= mTotal || mDatas.size() < ONE_PAGE_SIZE) {
            mAdapter.loadMoreEnd(false);
            return;
        }
        loadComplainListFromServer(false);
    }

    private void loadComplainListFromServer(boolean showSwipe) {
        if (showSwipe) {
            swipeLayouts.setRefreshing(true);
        }
        mPresenter.getOrderComplainDataFromServer(mType, mNextPage, mTotal);
    }
}