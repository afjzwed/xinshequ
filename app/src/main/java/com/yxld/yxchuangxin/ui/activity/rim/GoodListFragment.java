package com.yxld.yxchuangxin.ui.activity.rim;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.CxwyClassifyInfo;
import com.yxld.yxchuangxin.entity.CxwyProductInfo;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerGoodListComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.GoodListContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.GoodListModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.GoodListPresenter;
import com.yxld.yxchuangxin.ui.adapter.rim.ProductAdapter;
import com.yxld.yxchuangxin.view.SelectMultiCheckGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: $description
 * @date 2017/06/17
 */

public class GoodListFragment extends BaseFragment implements GoodListContract.View, SelectMultiCheckGroup.OnItemSelectedListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.checkGroup)
    SelectMultiCheckGroup checkGroup;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private String businessNumber;
    private String classifyId;
    private ProductAdapter adapter;

    int total = 0;
    int pageSize = 10;
    private boolean isErr = true;
    private boolean mLoadMoreEndGone = false;
    private int mCurrentCounter = 0;
    private int page = 0;

    private OnItemClickListener itemClickListener;

    public GoodListFragment() {
    }


    @Inject
    GoodListPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_good_list, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        page = 0;
        businessNumber = mBundle.getString("businessNumber");
        classifyId = mBundle.getString("classifyId");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        UIUtils.configSwipeRefreshLayoutColors(refreshLayout);
        Map<String, String> map = new HashMap<>();
        map.put("businessNumber", businessNumber);
        map.put("classifyId", classifyId);
        map.put("uuId", Contains.uuid);
        mPresenter.getClassifyinfo(map);
        return view;
    }


    @Override
    protected void setupFragmentComponent() {
        DaggerGoodListComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .goodListModule(new GoodListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(GoodListContract.GoodListContractPresenter presenter) {
        mPresenter = (GoodListPresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onGetClassify(CxwyClassifyInfo info) {
        if (info.getData().size() == 0) {          //   {"data":[],"msg":"查询成功成功","success":"1","total":0}
            //没有子分类
            initProductinfo(businessNumber, classifyId, false);
            refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    page = 0;
                    adapter.setEnableLoadMore(true);
                    initProductinfo(businessNumber, classifyId, false);
                }
            });
            refreshLayout.setRefreshing(true);
        } else {
            //有子分类
            showClassifyMenu(info);
            refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    page = 0;
                    adapter.setEnableLoadMore(true);
                    int position = checkGroup.getSelectedOne();
                    initProductinfo(businessNumber, info.getData().get(position).getClassifyId() + "", false);
                }
            });
            initProductinfo(businessNumber, info.getData().get(0).getClassifyId() + "", false);
            refreshLayout.setRefreshing(true);
        }
    }

    /**
     * 显示子分类菜单，并初始化
     */
    private void showClassifyMenu(CxwyClassifyInfo cxwyClassifyInfo) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < cxwyClassifyInfo.getData().size(); i++) {
            list.add(cxwyClassifyInfo.getData().get(i).getClassifyName());
        }
        checkGroup.setVisibility(View.VISIBLE);
        checkGroup.initData(list);// 初始化完了，才设置的监听，所以第一次设置默认第一个，不显示数据，同下
        checkGroup.setEntity(cxwyClassifyInfo);
        checkGroup.setOnItemSelectedListener(this);
    }

    private void initProductinfo(String businessNumber, String classifyId, boolean isLoadMore) {
        //?businessNumber=%1$s&classifyId=%2$s&page=%3$s&rows=%4$s&uuId=%5$s
        page += 1;
        Map<String, String> map = new HashMap<>();
        map.put("businessNumber", businessNumber);
        map.put("classifyId", classifyId);
        map.put("page", "0");
        map.put("page", page + "");
        map.put("rows", pageSize + "");
        map.put("uuId", Contains.uuid);
        mPresenter.getProductinfo(map);
    }

    @Override
    public void ongetProductinfo(CxwyProductInfo info) {
        total = info.getTotal();
        KLog.i(page);
        refreshLayout.setRefreshing(false);
        if (page == 1) {
            adapter = new ProductAdapter(info.getData());
            adapter.setOnLoadMoreListener(this, recyclerView);
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                    itemClickListener.onProductClick(adapter.getData().get(position));
                }
            });
        } else {
            if (adapter == null) {
                adapter = new ProductAdapter(info.getData());
            } else {
                adapter.addData(info.getData());
                adapter.notifyDataSetChanged();
            }
            adapter.loadMoreComplete();
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                    itemClickListener.onProductClick(adapter.getData().get(position));
                }
            });
        }
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
    public void checked(View view, int position, boolean isChecked) {
        if (isChecked) {
            page = 0;
            refreshLayout.setRefreshing(true);
            CxwyClassifyInfo info = (CxwyClassifyInfo) checkGroup.getEntity();
            initProductinfo(businessNumber, info.getData().get(position).getClassifyId() + "", false);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        if (adapter.getData().size() < pageSize) {
            //显示的总数都小于一次请求的数量
            adapter.loadMoreEnd(true);
        } else {
            //当前显示的item大于总数
            if (adapter.getData().size() >= total) {
                adapter.loadMoreEnd(mLoadMoreEndGone);//true is gone,false is visible
            } else {
                if (isErr) {
                    if (adapter.getData().size() == 0) {
                        initProductinfo(businessNumber, classifyId, false);
                    } else {
                        int position = checkGroup.getSelectedOne();
                        CxwyClassifyInfo info = (CxwyClassifyInfo) checkGroup.getEntity();
                        initProductinfo(businessNumber, info.getData().get(position).getClassifyId() + "", true);
                    }
                } else {
                    isErr = true;
                    Toast.makeText(getActivity(), R.string.umeng_socialize_network_break_alert, Toast.LENGTH_LONG).show();
                    adapter.loadMoreFail();
                }
            }
        }
    }

    public interface OnItemClickListener {
        //在接口中定义函数
        void onProductClick(CxwyProductInfo.DataBean data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            itemClickListener = (OnItemClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnHeadlineSelectedListener");
        }
    }
}