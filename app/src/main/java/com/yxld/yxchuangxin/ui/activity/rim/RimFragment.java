package com.yxld.yxchuangxin.ui.activity.rim;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.CxwyBusiness;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerRimComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimPresenter;
import com.yxld.yxchuangxin.ui.adapter.rim.RimFragmentAdapter;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: 欣周边
 * @date 2017/06/12
 */

public class RimFragment extends BaseFragment implements RimContract.View {

    @Inject
    RimPresenter mPresenter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swip_refresh_layout)
    SmartRefreshLayout mSwipRefreshLayout;
    @BindView(R.id.toolbarBusiness)
    Toolbar toolbar;
    @BindView(R.id.tv_menu)
    TextView mTvMenu;
    /**
     * 隐藏待开发的新周边的功能 设置背景即可
     */
    @BindView(R.id.lll_layout)
    AutoLinearLayout mLllLayout;

    private RimFragmentAdapter mAdapter;
    private List<CxwyBusiness.DataBean> mDataBeen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_rim, null);
            ButterKnife.bind(this, view);
            initToolbarView();
            mSwipRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                    refreshlayout.finishRefresh();
                    initData();

                }
            });
            mDataBeen = new ArrayList<>();
            mAdapter = new RimFragmentAdapter(mDataBeen);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setNestedScrollingEnabled(false);
            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    Intent businessIntent = new Intent(getActivity(), BusinessActivity.class);
                    businessIntent.putExtra("flag", "nomodify");
                    businessIntent.putExtra("businessNumber", mAdapter.getData().get(i).getBusiness().getBusinessNumber());
                    businessIntent.putExtra("businessProduceType", mAdapter.getData().get(i).getBusiness().getBusinessProduceType());//1商家2服务
                    startActivity(businessIntent);

                }
            });
//            //隐藏待开发的新周边
//            mLllLayout.setBackground(getResources().getDrawable(R.mipmap.zhoubian_bg));
//            toolbar.setVisibility(View.GONE);
            initData();
        }
        return view;
    }

    private void initToolbarView() {
        toolbar.setTitle("欣周边");
        mTvMenu.setVisibility(View.VISIBLE);
        mTvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RimOrderListActivityActivity.class);
                getActivity().startActivity(intent);
            }
        });
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        AutoLinearLayout.LayoutParams lp = new AutoLinearLayout.LayoutParams(UIUtils.getDisplayWidth(getActivity()), (int) (UIUtils.getStatusBarHeight(getActivity()) * 3));
        toolbar.setLayoutParams(lp);
        toolbar.setPadding(0, (int) (UIUtils.getStatusBarHeight(getActivity()) * 0.8), 0, 0);
        toolbar.setTitleMarginTop((int) (UIUtils.getStatusBarHeight(getActivity()) * 0.55));
    }

    private void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("page", "1");
        map.put("rows", "20");
        map.put("type", "1");
        map.put("uuId", Contains.uuid);
        mPresenter.loadShangJiaList(map);
    }

    @Override
    public void setShangJiaList(CxwyBusiness business) {
        if (business.getStatus() == 1) {
            mDataBeen = business.getData();
            mAdapter.setNewData(mDataBeen);
        } else {
            onError(business.msg, business.status);
        }
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void closeProgressDialog() {

    }


    @Override
    protected void setupFragmentComponent() {
        DaggerRimComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .rimModule(new RimModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(RimContract.RimContractPresenter presenter) {
        mPresenter = (RimPresenter) presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void initDataFromLocal() {

    }


}