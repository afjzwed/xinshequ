package com.yxld.yxchuangxin.ui.activity.rim;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.CxwyClassifyInfo;
import com.yxld.yxchuangxin.entity.CxwyProductInfo;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerRimGoodListComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimGoodListContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimGoodListModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimGoodListPresenter;
import com.yxld.yxchuangxin.ui.adapter.rim.ProductAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: $description
 * @date 2017/12/13 10:44:31
 */

public class RimGoodListFragment extends BaseFragment implements RimGoodListContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @Inject
    RimGoodListPresenter mPresenter;
    @BindView(R.id.rv_sort)
    RecyclerView mRecycleView;
    @BindView(R.id.swip_refresh)
    SwipeRefreshLayout mSwipRefresh;
    private String businessNumber;
    private String classifyId;

    private int page = 1;
    private int rows = 10;
    private int total;
    private ProductAdapter adapter;
    private List<CxwyProductInfo.DataBean> mDataBeanList;
    private RimGoodListFragment.OnItemClickListener itemClickListener;
    private OnDialogShowListener mOnDialogShowListener;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_rim_good_list, null);
            ButterKnife.bind(this, view);
            Bundle mBundle = getArguments();
            businessNumber = mBundle.getString("businessNumber");
            classifyId = mBundle.getString("classifyId");
            UIUtils.configSwipeRefreshLayoutColors(mSwipRefresh);
            mSwipRefresh.setOnRefreshListener(this);
            initData();
            loadDataFromServers();
        }
        return view;
    }

    private void loadDataFromServers() {
        Map<String, String> map = new HashMap<>();
        map.put("businessNumber", businessNumber);
        map.put("classifyId", classifyId);
        map.put("page", page + "");
        map.put("rows", rows + "");
        map.put("uuId", Contains.uuid);
        mPresenter.getProductinfo(map);
    }

    private void initData() {
        if (mDataBeanList == null) {
            mDataBeanList = new ArrayList<>();
        }
        if (adapter == null) {
            adapter = new ProductAdapter(mDataBeanList);
        }
        mRecycleView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this, mRecycleView);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                String tag = (String) view.getTag();
                if ("add".equals(tag)) {
                    itemClickListener.onProductClick(adapter.getData().get(i),view,adapter.getData().get(i).getProductImage().split(",")[0]);
                }else if ("image".equals(tag)){
                    if (mOnDialogShowListener != null) {
                        mOnDialogShowListener.showDialog(adapter.getData().get(i));
                    }
                }
            }
        });
    }


    @Override
    protected void setupFragmentComponent() {
        DaggerRimGoodListComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .rimGoodListModule(new RimGoodListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(RimGoodListContract.RimGoodListContractPresenter presenter) {
        mPresenter = (RimGoodListPresenter) presenter;
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
    public void onGetClassify(CxwyClassifyInfo info) {
//        if (info.getData().size() == 0) {          //   {"data":[],"msg":"查询成功成功","success":"1","total":0}
//            //没有子分类
//            initProductinfo(businessNumber, classifyId, false);
//            mRecycleView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                @Override
//                public void onRefresh() {
//                    page = 0;
//                    adapter.setEnableLoadMore(true);
//                    initProductinfo(businessNumber, classifyId, false);
//                }
//            });
//            refreshLayout.setRefreshing(true);
//        } else {
//            //有子分类
//            showClassifyMenu(info);
//            refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                @Override
//                public void onRefresh() {
//                    page = 0;
//                    adapter.setEnableLoadMore(true);
//                    int position = checkGroup.getSelectedOne();
//                    initProductinfo(businessNumber, info.getData().get(position).getClassifyId() + "", false);
//                }
//            });
//            initProductinfo(businessNumber, info.getData().get(0).getClassifyId() + "", false);
//            refreshLayout.setRefreshing(true);
//        }
    }

    @Override
    public void ongetProductinfo(CxwyProductInfo info) {
        if (info.getStatus()==1) {
            mSwipRefresh.setRefreshing(false);
            adapter.loadMoreComplete();
            KLog.i(page);
            if (page == 1) {
                mDataBeanList.clear();
            }
            mDataBeanList.addAll(info.getData());
            total = info.getTotal();
            if (mDataBeanList.size() < total) {
                page++;
            }
            if (mDataBeanList.size() == 0) {
                adapter.setEmptyView(R.layout.layout_empty_data, (ViewGroup) mRecycleView.getParent());
            }
            adapter.setNewData(mDataBeanList);
        }else {
            onError(info.msg,info.status);
        }
    }


    @Override
    public void onRefresh() {
        page = 1;
        loadDataFromServers();
    }

    @Override
    public void onLoadMoreRequested() {
        if (mDataBeanList.size() < rows || mDataBeanList.size() >= total) {
            adapter.loadMoreEnd(true);
            return;
        }
        loadDataFromServers();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            itemClickListener = (RimGoodListFragment.OnItemClickListener) context;
            mOnDialogShowListener = (RimGoodListFragment.OnDialogShowListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnHeadlineSelectedListener");
        }
    }

    //详情弹框的接口
    public interface OnDialogShowListener {
        //在接口中定义函数
        void showDialog(CxwyProductInfo.DataBean data);
    }
    public interface OnItemClickListener {
        //在接口中定义函数
        void onProductClick(CxwyProductInfo.DataBean data,View view,String url);
    }
}