package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.YwhMember;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerYwhMemberShowComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.YwhMemberShowContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.YwhMemberShowModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.YwhMemberShowPresenter;
import com.yxld.yxchuangxin.ui.adapter.ywh.YwhMemberShowAdapter;
import com.yxld.yxchuangxin.view.CustomLoadMoreView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: 业主大会人员公示
 * @date 2018/11/07 20:37:02
 */

public class YwhMemberShowActivity extends BaseActivity implements YwhMemberShowContract.View, SwipeRefreshLayout
        .OnRefreshListener {

    @Inject
    YwhMemberShowPresenter mPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swip_refresh)
    SwipeRefreshLayout swipRefresh;

    private YwhMemberShowAdapter ywhMemberShowAdapter;
    private List<YwhMember.DataBean> memberList = new ArrayList<>();


    private int page;//分页数
    private int rows = 6;//每页加载数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ywh_membershow);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        UIUtils.configSwipeRefreshLayoutColors(swipRefresh);
        swipRefresh.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ywhMemberShowAdapter = new YwhMemberShowAdapter();
        ywhMemberShowAdapter.setLoadMoreView(new CustomLoadMoreView());
        ywhMemberShowAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getMemberData(false);
            }
        }, recyclerView);
        recyclerView.setAdapter(ywhMemberShowAdapter);//绑定适配器

        // TODO: 2018/11/10 人员名单确认以后意见反馈隐藏 根据上级页面阶段是否已完成来判断
        if (true) {
            tvMenu.setVisibility(View.VISIBLE);
        } else {
            tvMenu.setVisibility(View.GONE);
        }
        tvMenu.setText("意见反馈");
        tvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FkyjActivity.class);
            }
        });
    }

    @Override
    protected void initData() {
        page = 1;
        swipRefresh.setRefreshing(true);//显示加载进度条.要在主线程中执行
        ywhMemberShowAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载，设置为true就是自动加载更多
        getMemberData(true);
    }

    private void getMemberData(boolean isRefresh) {
        //网络请求 获得数据
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("uuid", Contains.uuid);
        map.put("page", page + "");
        map.put("rows", rows + "");
        mPresenter.getData(map,isRefresh);
    }

    @Override
    public void setData(boolean isRefresh,YwhMember data) {
        page++;
        memberList = data.getData();
        final int size = memberList == null ? 0 : memberList.size();

        if (isRefresh) {
            if (size > 0) {

            } else {
//                rimOrderListAdapter.setEmptyView(notDataView);
                ywhMemberShowAdapter.setNewData(new ArrayList<>());
            }
            ywhMemberShowAdapter.setNewData(memberList);//将首次数据塞入适配器的方法
        } else {
            if (size > 0) {
                ywhMemberShowAdapter.addData(memberList);//加载更多时直接将更多数据塞入适配器
            }
        }

        if (size < rows) {
            //第一页如果不够一页就不显示没有更多数据布局
            ywhMemberShowAdapter.loadMoreEnd(isRefresh);//不传参数默认false,表示数据全部加载完毕没有更多数据  加载结束
            // 这里设置为false可用来显示没有更多数据item
        } else {
            ywhMemberShowAdapter.loadMoreComplete();//加载完成（注意不是加载结束，而是本次数据加载结束并且还有下页数据）
        }

        ywhMemberShowAdapter.setEnableLoadMore(true);//自动加载更多
        swipRefresh.setRefreshing(false);//加载完成,不显示进度条
    }

    @Override
    public void setError() {
        ywhMemberShowAdapter.setEnableLoadMore(true);//自动加载更多
        swipRefresh.setRefreshing(false);//加载完成,不显示进度条
//        fkyjListAdapter.setEmptyView(notDataView);
        ywhMemberShowAdapter.setNewData(new ArrayList<>());
        ToastUtil.show(this, "加载失败");
    }

    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }

    @Override
    protected void setupActivityComponent() {
        DaggerYwhMemberShowComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .ywhMemberShowModule(new YwhMemberShowModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(YwhMemberShowContract.YwhMemberShowContractPresenter presenter) {
        mPresenter = (YwhMemberShowPresenter) presenter;
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
    public void onRefresh() {
        initData();
    }
}