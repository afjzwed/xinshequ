package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.YwhFkyj;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerFkyj2Component;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.Fkyj2Contract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.Fkyj2Module;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.Fkyj2Presenter;
import com.yxld.yxchuangxin.ui.adapter.ywh.FkyjListAdapter;
import com.yxld.yxchuangxin.view.CustomLoadMoreView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/09 13:42:26
 */

public class Fkyj2Fragment extends BaseFragment implements Fkyj2Contract.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    Fkyj2Presenter mPresenter;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.swip_refresh)
    SwipeRefreshLayout swipRefresh;

    private int currentFlow;
    private FkyjListAdapter fkyjListAdapter;
    private int page;//分页数
    private int rows = 6;//每页加载数

    private List<YwhFkyj.DataBean> orderBeanList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fkyj2, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
//        currentFlow = mBundle.getInt("currentFlow");//根据这个值判断意见反馈的上级页面从而判断使用哪个接口

        initRv();
        initData();
        return view;
    }

    private void initData() {
        // TODO: 2018/11/12 判断掉哪个接口
        page = 1;
        swipRefresh.setRefreshing(true);//显示加载进度条.要在主线程中执行
        fkyjListAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载，设置为true就是自动加载更多
        getData(true);


    }

    private void initRv() {
        UIUtils.configSwipeRefreshLayoutColors(swipRefresh);
        swipRefresh.setOnRefreshListener(this);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        fkyjListAdapter = new FkyjListAdapter(new ArrayList<>());

        fkyjListAdapter.setLoadMoreView(new CustomLoadMoreView());
        fkyjListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getData(false);
            }
        }, rv);
        rv.setAdapter(fkyjListAdapter);
    }

    private void getData(boolean isRefresh) {
        FkyjActivity fkyjActivity = (FkyjActivity) getActivity();
        switch (fkyjActivity.getPosition()) {
            case 1: {//成立筹备组
                Log.e("wh", "11");
                Map<String, String> map = new HashMap<>();
                map.put("uuid", Contains.uuid);
                map.put("page", page + "");
                map.put("rows", rows + "");
                mPresenter.getData2(map);
                break;
            }
            case 2: {//筹备组工作
                Log.e("wh", "22");
                break;
            }
            case 3: {//候选人确认
                Log.e("wh", "33");
//                LinkedHashMap<String, String> map = new LinkedHashMap<>();
////                map.put("uuid", Contains.uuid);
////                map.put("page", page + "");
////                map.put("rows", rows + "");
//                mPresenter.getData3(map, isRefresh);//候选人确认的意见列表接口
                break;
            }
            case 4: {//业主大会
                Log.e("wh", "44");
                LinkedHashMap<String, String> map = new LinkedHashMap<>();
                map.put("uuid", Contains.uuid);
                map.put("page", page + "");
                map.put("rows", rows + "");
                map.put("resultType", "4");//反馈类型 （1:成立筹备组阶段 2:筹备组工作阶段 3:候选人确认阶段 4:业主大会阶段 5:备案阶段）
                mPresenter.getData1(map, isRefresh);//业主大会的意见列表接口
                break;
            }
        }
    }

    @Override
    public void setData(boolean isRefresh, YwhFkyj data) {
        page++;
        orderBeanList = data.getData();
        final int size = orderBeanList == null ? 0 : orderBeanList.size();

        if (isRefresh) {
            if (size > 0) {

            } else {
//                rimOrderListAdapter.setEmptyView(notDataView);
                fkyjListAdapter.setNewData(new ArrayList<>());
            }
            fkyjListAdapter.setNewData(orderBeanList);//将首次数据塞入适配器的方法
        } else {
            if (size > 0) {
                fkyjListAdapter.addData(orderBeanList);//加载更多时直接将更多数据塞入适配器
            }
        }

        if (size < rows) {
            //第一页如果不够一页就不显示没有更多数据布局
            fkyjListAdapter.loadMoreEnd(isRefresh);//不传参数默认false,表示数据全部加载完毕没有更多数据  加载结束
            // 这里设置为false可用来显示没有更多数据item
        } else {
            fkyjListAdapter.loadMoreComplete();//加载完成（注意不是加载结束，而是本次数据加载结束并且还有下页数据）
        }

        fkyjListAdapter.setEnableLoadMore(true);//自动加载更多
        swipRefresh.setRefreshing(false);//加载完成,不显示进度条
    }

    @Override
    public void setError() {
        fkyjListAdapter.setEnableLoadMore(true);//自动加载更多
        swipRefresh.setRefreshing(false);//加载完成,不显示进度条
//        fkyjListAdapter.setEmptyView(notDataView);
        fkyjListAdapter.setNewData(new ArrayList<YwhFkyj.DataBean>());
        ToastUtil.show(getActivity(), "加载失败");
    }

    @Override
    public void setData2(YwhFkyj baseEntity) {

    }

    @Override
    protected void setupFragmentComponent() {
        DaggerFkyj2Component
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .fkyj2Module(new Fkyj2Module(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(Fkyj2Contract.Fkyj2ContractPresenter presenter) {
        mPresenter = (Fkyj2Presenter) presenter;
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
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onRefresh() {
        initData();
    }
}